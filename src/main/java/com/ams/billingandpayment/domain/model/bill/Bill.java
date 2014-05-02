package com.ams.billingandpayment.domain.model.bill;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.ams.billingandpayment.domain.model.servicecatalog.Service;
import com.ams.sharedkernel.domain.DomainException;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.sharedkernel.domain.model.measuresandunits.Quantity;
import com.ams.users.domain.model.Person;

@Entity
@Access(PROPERTY)
@Table(name = "T_BILL")
public class Bill implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Long				billNumber;
	private Person				billedPerson;
	private Date				billDate;
	private Date				billDueDate;
	private Period				billPeriod;
	private Collection<BillItem>	billItems;
	private BigDecimal			billTotalTax;
	private BigDecimal			billTotalAmount;
	private Collection<Payment>	billPayments;
	private BillPaymentRegister	billPaymentRegister;
	private String				billNote;

	public Bill()
	{
		this.billItems = new ArrayList<BillItem>();
		this.billTotalTax = new BigDecimal(0);
		this.billTotalAmount = new BigDecimal(0);
		this.billPeriod = new Period();
		this.billPayments = new ArrayList<Payment>();
		this.billPaymentRegister = new BillPaymentRegister();
	}

	public Bill(Person billedPersn, Date billDate, Date billDueDate, Period billPeriod)
	{
		this.billedPerson = billedPersn;
		this.billDate = billDate;
		this.billDueDate = billDueDate;
		this.billPeriod = billPeriod;
		this.billTotalAmount = BigDecimal.ZERO;
		this.billItems = new ArrayList<BillItem>();
		this.billPaymentRegister = new BillPaymentRegister();
	}

	/*
	 * Bill Domain Logic Functions
	 */

	public void addBillItem(long itemUsage, Service service) throws DomainException
	{
		BillItem billItem = new BillItem(itemUsage, service);
		this.updateBillTotalAmount(billItem.getBillItemAmount());
		billItem.setBill(this);
		this.billItems.add(billItem);
	}

	public void addBillItem(Service srvc, Quantity srvcUsageQty, Money calculateServiceCharge, Tax tax)
	{

	}

	public void addBillItems(List<BillItem> list) throws DomainException
	{
		for (BillItem item : list)
		{
			if (item != null)
			{
				this.billItems.add(item);
				this.updateBillTotalAmount(item.getBillItemAmount());
			}
		}
		this.calculateTotalAmount();
		this.getBillPaymentRegister().setBillRemainingAmount(this.getBillTotalAmount());
		this.getBillPaymentRegister().setBillPaymentStatus(BillSpecification.status.UNPAID);
	}

	public BigDecimal calculateTotalAmount() throws DomainException
	{
		this.calculateTotalTax();
		this.billTotalAmount = this.billTotalAmount.add(this.billTotalTax);
		return this.billTotalAmount;
	}

	private BigDecimal calculateTotalTax() throws DomainException
	{

		if (this.billTotalAmount == null)
		{
			throw new DomainException("The bill total amout is null");
		}

		this.billTotalTax = this.billTotalAmount.multiply(BigDecimal.valueOf(0.1));

		return this.billTotalTax;
	}

	public Date getBillDate()
	{
		return this.billDate;
	}

	public Date getBillDueDate()
	{
		return this.billDueDate;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "Person_Id")
	@JsonIgnore
	public Person getBilledPerson()
	{
		return this.billedPerson;
	}

	@OneToMany(mappedBy = "bill",targetEntity = BillItem.class,orphanRemoval = true,
				cascade = CascadeType.ALL)
	@OrderBy(value = "")
	public Collection<BillItem> getBillItems()
	{
		return this.billItems;
	}

	public String getBillNote()
	{
		return this.billNote;
	}

	/*
	 * Accessor & Mutator Functions
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator = "BillNoSeqGen")
	@TableGenerator(name = "BillNoSeqGen",table = "T_IdSeqGen",pkColumnName = "idSeq_name",
					pkColumnValue = "idSeq_value",initialValue = 1000)
	public Long getBillNumber()
	{
		return this.billNumber;
	}

	@Embedded
	public BillPaymentRegister getBillPaymentRegister()
	{
		return this.billPaymentRegister;
	}

	@OneToMany(mappedBy = "paymntForBill",targetEntity = Payment.class,orphanRemoval = true,
				cascade = CascadeType.ALL)
	public Collection<Payment> getBillPayments()
	{
		return this.billPayments;
	}

	public Period getBillPeriod()
	{
		return this.billPeriod;
	}

	public BigDecimal getBillTotalAmount()
	{
		return this.billTotalAmount;
	}

	public BigDecimal getBillTotalTax()
	{
		return this.billTotalTax;
	}

	public void makePayment(Payment payment)
	{
		this.billPaymentRegister.updateBillPayment(this, payment);
	}

	public void setBillDate(Date billDate)
	{
		this.billDate = billDate;
	}

	public void setBillDueDate(Date billDueDate)
	{
		this.billDueDate = billDueDate;
	}

	public void setBilledPerson(Person billedPerson)
	{
		this.billedPerson = billedPerson;
	}

	public void setBillItems(Collection<BillItem> billItems)
	{
		this.billItems = billItems;
	}

	void setBillNote(String billNote)
	{
		this.billNote = billNote;
	}

	public void setBillNumber(Long billNumber)
	{
		this.billNumber = billNumber;
	}

	public void setBillPaymentRegister(BillPaymentRegister paymentRegister)
	{
		this.billPaymentRegister = paymentRegister;
	}

	public void setBillPayments(Collection<Payment> payments)
	{
		this.billPayments = payments;
	}

	public void setBillPeriod(Period period)
	{
		this.billPeriod = period;
	}

	public void setBillTotalAmount(BigDecimal amount)
	{
		this.billTotalAmount = amount;
	}

	public void setBillTotalTax(BigDecimal tax)
	{
		this.billTotalTax = tax;
	}

	private void updateBillTotalAmount(BigDecimal updtByAmount) throws DomainException
	{
		this.billTotalAmount = this.billTotalAmount.add(updtByAmount);
	}

}
