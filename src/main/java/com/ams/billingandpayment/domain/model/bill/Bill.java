package com.ams.billingandpayment.domain.model.bill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ams.billingandpayment.domain.model.bill.exception.BillExceptionCode;
import com.ams.billingandpayment.domain.model.bill.policy.DiscountPolicy;
import com.ams.billingandpayment.domain.model.bill.policy.TaxPolicy;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice;
import com.ams.sharedkernel.domain.exception.DomainException;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.sharedkernel.domain.model.measuresandunits.Quantity;
import com.ams.users.domain.model.Person;

/**
 * @author Raghavendra Badiger
 */

@Entity
@Access(AccessType.FIELD)
@Table(name = "T_BILL")
public class Bill implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long				billNumber;
	@ManyToOne
	@JoinColumn(name = "BilledPerson_Id")
	private Person				billedPerson;
	@Temporal(TemporalType.DATE)
	private Date				billDate;
	@Temporal(TemporalType.DATE)
	private Date				billDueDate;
	@Embedded
	private Period				billPeriod;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "PrevBal_Amount")),
			@AttributeOverride(name = "currency",column = @Column(name = "PrevBal_Currency"))
	})
	private Money				billPreviousBalance;
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "Penalty_Amount")),
			@AttributeOverride(name = "currency",column = @Column(name = "Penalty_Currency"))
	})
	private Money				billPenaltyAmount;
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "GrossAmount_Amount")),
			@AttributeOverride(name = "currency",column = @Column(name = "GrossAmount_Currency"))
	})
	private Money				billGrossAmount;
	@Embedded
	private Tax				billTotalTax;
	@Embedded
	private Discount			billTotalDiscount;
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "NetAmount_Amount")),
			@AttributeOverride(name = "currency",column = @Column(name = "NetAmount_Currency"))
	})
	private Money				billNetAmount;
	@OneToOne
	@JoinColumn(name = "BillPayReg_Id")
	private BillPaymentRegister	billPaymentRegister;

	@ElementCollection
	@CollectionTable(name = "T_BILLITEM",joinColumns = @JoinColumn(name = "Bill_No"))
	private List<BillItem>		billItems;
	@ElementCollection
	@CollectionTable(name = "T_BILLPAYMENTS",joinColumns = @JoinColumn(name = "Bill_No"))
	private List<Payment>		billPayments;

	private Bill()
	{}

	/*
	 * 
	 * Bill Domain Logic Functions
	 */

	public static class BillBuilder
	{
		public final Bill	bill			= new Bill();
		private boolean	isHeaderSet	= false;

		public boolean isHeaderSet()
		{
			return this.isHeaderSet;
		}

		public BillBuilder header(Person billedPersn, Date billDate, Date billDueDate, Period billPeriod,
								BillPaymentRegister bpRegister)
		{

			if ((billedPersn != null) && (billDate != null) && (billDueDate != null) && (billPeriod != null) && (bpRegister != null))
			{
				if (billDueDate.after(billDate))
				{
					this.bill.billedPerson = billedPersn;
					this.bill.billDate = billDate;
					this.bill.billDueDate = billDueDate;
					this.bill.billPeriod = billPeriod;
					this.bill.billPaymentRegister = bpRegister;
					this.bill.billPreviousBalance = bpRegister.getBillCurrentBalance();
					this.bill.billPenaltyAmount = bpRegister.getBillCurrentBalance().compareTo(Money.ZERO) > 0 ? BillSpecification.getPenaltyAmount() : Money.ZERO;
					this.bill.billGrossAmount = Money.ZERO;
					this.bill.billTotalTax = Tax.ZERO_TAX;
					this.bill.billTotalDiscount = Discount.ZERO_DISCOUNT;
					this.bill.billNetAmount = Money.ZERO;
					this.bill.billItems = new ArrayList<BillItem>();
					this.bill.billPayments = new ArrayList<Payment>();
					this.isHeaderSet = true;
					return this;
				}
				else
				{
					throw new DomainException("BillDate can't be after or equal to BillDueDate!!");
				}
			}
			else
			{
				throw new DomainException(BillExceptionCode.HEADER_NULL_ARGUMENT.getExceptionDetails());
			}
		}

		public BillBuilder addLineItem(ServicePrice srvcPrice, Quantity qty, DiscountPolicy itemDscntPolicy, TaxPolicy itemTaxPolicy)
		{
			if (this.isHeaderSet)
			{
				this.bill.addBillItem(srvcPrice, qty, itemDscntPolicy, itemTaxPolicy);
				return this;
			}
			else
			{
				throw new DomainException(BillExceptionCode.HEADER_NOT_SET.getExceptionDetails());
			}

		}

		public Bill getBillInstance(DiscountPolicy billDiscntPolicy, TaxPolicy billTaxPolicy)
		{
			if (this.isHeaderSet)
			{
				this.bill.calculateBillNetAmount(billDiscntPolicy, billTaxPolicy);
				return this.bill;
			}
			else
			{
				throw new DomainException(BillExceptionCode.HEADER_NOT_SET.getExceptionDetails());
			}

		}

	}

	private void addBillItem(ServicePrice srvcPrice, Quantity qty, DiscountPolicy itemDscntPolicy, TaxPolicy itemTaxPolicy)
	{

		BillItem item = this.find(srvcPrice);

		if (item == null)
		{
			this.billItems.add(new BillItem(srvcPrice, qty, itemTaxPolicy, itemDscntPolicy));
		}
		else
		{
			item.increaseQuantity(qty, itemTaxPolicy, itemDscntPolicy);
		}

		this.recalculateBillGrossAmount();

	}

	private BillItem find(ServicePrice sp)
	{
		for (BillItem item : this.billItems)
		{
			if (sp.equals(item.getServicePrice()))
			{
				return item;
			}
		}

		return null;
	}

	private void recalculateBillGrossAmount()
	{
		this.billGrossAmount = Money.ZERO;
		for (BillItem item : this.billItems)
		{
			System.out.println("Item net amount in Bill:" + item.getNetAmount());
			this.billGrossAmount = this.billGrossAmount.add(item.getNetAmount());
		}
		System.out.println("Bill Gross amount:" + this.billGrossAmount);
	}

	private void calculateBillNetAmount(DiscountPolicy billDiscntPolicy, TaxPolicy billTaxPolicy)
	{
		this.billTotalDiscount = billDiscntPolicy.calculateDiscount(this.billGrossAmount);
		System.out.println("Bill Discount amount:" + this.billTotalDiscount.getDiscntAmount());
		Money grossAfterDiscount = this.billGrossAmount.subtract(this.billTotalDiscount.getDiscntAmount());
		System.out.println("Bill gross after discount amount:" + grossAfterDiscount);
		this.billTotalTax = billTaxPolicy.calculateTax(grossAfterDiscount);
		System.out.println("Bill Tax amount:" + this.billTotalTax.getTaxAmount());

		this.billNetAmount = grossAfterDiscount.add(this.billTotalTax.getTaxAmount());
		System.out.println("Bill Net amount:" + this.billNetAmount);

		this.billPaymentRegister.updateCurrentBalance(this);
		System.out.println("Bill Current Balance:" + this.billPaymentRegister.getBillCurrentBalance());
		System.out.println("Bill Status:" + this.billPaymentRegister.getBillPaymentStatus());
	}

	public void makePayment(Payment pymnt)
	{

	}

	/*
	 * Bill Accessor Functions
	 */
	public long getBillNumber()
	{
		return this.billNumber;
	}

	public Person getBilledPerson()
	{
		return this.billedPerson;
	}

	public Date getBillDate()
	{
		return this.billDate;
	}

	public Date getBillDueDate()
	{
		return this.billDueDate;
	}

	public Period getBillPeriod()
	{
		return this.billPeriod;
	}

	public Money getBillPreviousBalance()
	{
		return this.billPreviousBalance;
	}

	public Money getBillPenaltyAmount()
	{
		return this.billPenaltyAmount;
	}

	public Collection<BillItem> getBillItems()
	{
		return this.billItems;
	}

	public Money getBillNetAmount()
	{
		return this.billNetAmount;
	}

	public BillPaymentRegister getBillPaymentRegister()
	{
		return this.billPaymentRegister;
	}

	public Tax getBillTotalTax()
	{
		return this.billTotalTax;
	}

	public Discount getBillTotalDiscount()
	{
		return this.billTotalDiscount;
	}

	public Money getBillGrossAmount()
	{
		return this.billGrossAmount;
	}

	public List<Payment> getBillPayments()
	{
		return this.billPayments;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
