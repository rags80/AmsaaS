package com.ams.billingandpayment.domain.model.bill;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ams.finance.domain.model.Transaction;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.users.domain.model.Person;

/**
 * @author Raghavendra Badiger
 * 
 */
@Entity
@Table(name = "T_PAYMENT")
@Access(AccessType.FIELD)
public class Payment implements Serializable
{

	private static final long	serialVersionUID	= 1L;
	@Id
	@GeneratedValue
	private Long				paymntId;
	private Money				paymntAmount;
	private String				paymntMethod;
	@Temporal(TemporalType.TIMESTAMP)
	private Date				paymntDate;
	@OneToOne(cascade = CascadeType.PERSIST,optional = false)
	// private Transaction paymntTransaction;
	@ManyToOne(optional = false)
	@JoinColumn(name = "Person_Id")
	private Person				paymntPerson;

	Payment()
	{}

	public Payment(Money payAmnt, String payMethod, Date payDate, Transaction payTransRef,
				Person payPerson)
	{

		this.paymntAmount = payAmnt;
		this.paymntMethod = payMethod;
		this.paymntDate = payDate;
		// this.paymntTransaction = payTransRef;
		this.paymntPerson = payPerson;
	}

	public Money getPaymntAmount()
	{
		return this.paymntAmount;
	}

	public Date getPaymntDate()
	{
		return this.paymntDate;
	}

	public Long getPaymntId()
	{
		return this.paymntId;
	}

	public String getPaymntMethod()
	{
		return this.paymntMethod;
	}

	public Person getPaymntPerson()
	{
		return this.paymntPerson;
	}

	/*
	 * public Transaction getPaymntTransaction() { return
	 * this.paymntTransaction; }
	 */

}
