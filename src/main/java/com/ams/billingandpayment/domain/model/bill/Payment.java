package com.ams.billingandpayment.domain.model.bill;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ams.finance.domain.model.Transaction;
import com.ams.users.domain.model.Person;

@Entity
@Table(name = "T_PAYMENT")
@Access(PROPERTY)
public class Payment implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Long				paymntId;
	private BigDecimal			paymntAmount;
	private BigDecimal			paymntBalance;
	private String				paymntMethod;
	private Date				paymntDate;
	private Transaction			paymntTransaction;
	private Bill				paymntForBill;
	private Person				paymntPerson;

	public BigDecimal getPaymntAmount()
	{
		return this.paymntAmount;
	}

	public BigDecimal getPaymntBalance()
	{
		return paymntBalance;
	}

	public Date getPaymntDate()
	{
		return this.paymntDate;
	}

	@ManyToOne(optional = false,cascade = CascadeType.ALL)
	@JoinColumn(name = "Bill_Number")
	public Bill getPaymntForBill()
	{
		return this.paymntForBill;
	}

	@Id
	@GeneratedValue
	public Long getPaymntId()
	{
		return paymntId;
	}

	public String getPaymntMethod()
	{
		return this.paymntMethod;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "Person_Id")
	public Person getPaymntPerson()
	{
		return this.paymntPerson;
	}

	@OneToOne(cascade = CascadeType.PERSIST,optional = false)
	public Transaction getPaymntTransaction()
	{
		return this.paymntTransaction;
	}

	public void setPaymntAmount(BigDecimal paymntAmount)
	{
		this.paymntAmount = paymntAmount;
	}

	public void setPaymntBalance(BigDecimal paymntBalance)
	{
		this.paymntBalance = paymntBalance;
	}

	public void setPaymntDate(Date paymntDate)
	{
		this.paymntDate = paymntDate;
	}

	public void setPaymntForBill(Bill paymntForBill)
	{
		this.paymntForBill = paymntForBill;
	}

	public void setPaymntId(Long paymntId)
	{
		this.paymntId = paymntId;
	}

	public void setPaymntMethod(String paymntMethod)
	{
		this.paymntMethod = paymntMethod;
	}

	public void setPaymntPerson(Person paymntPerson)
	{
		this.paymntPerson = paymntPerson;
	}

	public void setPaymntTransaction(Transaction paymntTransaction)
	{
		this.paymntTransaction = paymntTransaction;
	}

}
