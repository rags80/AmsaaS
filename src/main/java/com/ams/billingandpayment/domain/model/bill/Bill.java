package com.ams.billingandpayment.domain.model.bill;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.ams.billingandpayment.domain.model.service.Service;
import com.ams.sharedkernel.domain.model.DomainException;
import com.ams.sharedkernel.domain.model.measureandunits.Period;
import com.ams.usermanagement.domain.model.Person;

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
	private Collection<BillItem>	billItems			= Collections.EMPTY_LIST;
	private BigDecimal			billTotalTax;
	private BigDecimal			billTotalAmount;
	private Date				billDate;
	private Date				billDueDate;
	private Period				billPeriod;
	private Collection<Payment>	billPayments;
	private BillPaymentRegister	billPaymentRegister;
	private String				billNote;

	public Bill()
	{
		billItems = new ArrayList<BillItem>();
		billTotalTax = new BigDecimal(0);
		billTotalAmount = new BigDecimal(0);
		billPeriod = new Period();
		billPayments = new ArrayList<Payment>();
		billPaymentRegister = new BillPaymentRegister();
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

	public BigDecimal calculateTotalAmount() throws DomainException
	{
		this.calculateTotalTax();
		this.billTotalAmount = this.billTotalAmount.add(this.billTotalTax);
		return billTotalAmount;
	}

	private BigDecimal calculateTotalTax() throws DomainException
	{

		if (this.billTotalAmount == null)
		{
			throw new DomainException("The bill total amout is null");
		}

		this.billTotalTax = this.billTotalAmount.multiply(new BigDecimal(0.1));

		return this.billTotalTax;
	}

	private void updateBillTotalAmount(BigDecimal updtByAmount) throws DomainException
	{
		this.billTotalAmount = this.billTotalAmount.add(updtByAmount);
	}

	public void addBillItem(long itemUsage, Service service) throws DomainException
	{
		BillItem billItem = new BillItem(itemUsage, service);
		this.updateBillTotalAmount(billItem.getBillItemAmount());
		billItem.setBill(this);
		this.billItems.add(billItem);
	}

	public void addBillItems(List<BillItem> list) throws DomainException
	{
		BillItem billItem = new BillItem();
		Long quantity;
		Service service;
		for (BillItem item : list)
		{
			quantity = item.getBillItemQuantity();
			service = item.getBillItemService();
			if ((item != null) && (quantity != null) && (service != null))
			{
				billItem.setBillItemQuantity(quantity);
				billItem.setBillItemService(service);
				System.out.println(" item.getBillItemAmount() " + item.getBillItemAmount());
				this.updateBillTotalAmount(item.getBillItemAmount());
				billItem.setBill(this);
				this.billItems.add(billItem);
			}
		}
		this.calculateTotalAmount();
		this.getBillPaymentRegister().setBillRemainingAmount(this.getBillTotalAmount());
		this.getBillPaymentRegister().setBillPaymentStatus(BillSpecification.status.UNPAID);
	}

	public void makePayment(Payment payment)
	{
		this.billPaymentRegister.updateBillPayment(this, payment);
	}

	/*
	 * Accessor N Mutator Functions
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator = "BillNoSeqGen")
	@TableGenerator(name = "BillNoSeqGen",table = "T_IdSeqGen",pkColumnName = "idSeq_name",
					pkColumnValue = "idSeq_value",initialValue = 1000)
	public Long getBillNumber()
	{
		return billNumber;
	}

	public void setBillNumber(Long billNumber)
	{
		this.billNumber = billNumber;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "Person_Id")
	@JsonIgnore
	public Person getBilledPerson()
	{
		return billedPerson;
	}

	public void setBilledPerson(Person billedPerson)
	{
		this.billedPerson = billedPerson;
	}

	@OneToMany(mappedBy = "bill",targetEntity = BillItem.class,orphanRemoval = true,
				cascade = CascadeType.ALL)
	public Collection<BillItem> getBillItems()
	{
		return billItems;
	}

	public void setBillItems(Collection<BillItem> billItems)
	{
		this.billItems = billItems;
	}

	public BigDecimal getBillTotalTax()
	{
		return this.billTotalTax;
	}

	public void setBillTotalTax(BigDecimal tax)
	{
		this.billTotalTax = tax;
	}

	public BigDecimal getBillTotalAmount()
	{
		return this.billTotalAmount;
	}

	public void setBillTotalAmount(BigDecimal amount)
	{
		this.billTotalAmount = amount;
	}

	public Date getBillDate()
	{
		return billDate;
	}

	public void setBillDate(Date billDate)
	{
		this.billDate = billDate;
	}

	public Date getBillDueDate()
	{
		return billDueDate;
	}

	public void setBillDueDate(Date billDueDate)
	{
		this.billDueDate = billDueDate;
	}

	public Period getBillPeriod()
	{
		return billPeriod;
	}

	public void setBillPeriod(Period period)
	{
		this.billPeriod = period;
	}

	@OneToMany(mappedBy = "paymntForBill",targetEntity = Payment.class,orphanRemoval = true,
				cascade = CascadeType.ALL)
	public Collection<Payment> getBillPayments()
	{
		return billPayments;
	}

	public void setBillPayments(Collection<Payment> payments)
	{
		this.billPayments = payments;
	}

	public String getBillNote()
	{
		return billNote;
	}

	void setBillNote(String billNote)
	{
		this.billNote = billNote;
	}

	@Embedded
	public BillPaymentRegister getBillPaymentRegister()
	{
		return billPaymentRegister;
	}

	public void setBillPaymentRegister(BillPaymentRegister paymentRegister)
	{
		this.billPaymentRegister = paymentRegister;
	}
}
