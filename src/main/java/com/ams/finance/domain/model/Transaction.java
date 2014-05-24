package com.ams.finance.domain.model;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.ams.users.domain.model.Person;

// @Entity
@Access(PROPERTY)
@Table(name = "T_TRANSACTION")
public class Transaction implements Serializable
{
	/**
     *
     */
	private static final long	serialVersionUID	= 1L;
	protected Integer			version;
	private Long				transNumber;
	private float				transAmount;
	private Date				transDate;
	private String				transType;
	private String				transMode;
	private Account			transAccount;
	private Person				transPerson;

	public Transaction()
	{}

	@ManyToOne(optional = false)
	@JoinColumn(name = "Account_Number")
	public Account getTransAccount()
	{
		return this.transAccount;
	}

	public void setTransAccount(Account transAccount)
	{
		this.transAccount = transAccount;
	}

	public float getTransAmount()
	{
		return this.transAmount;
	}

	public void setTransAmount(float transAmount)
	{
		this.transAmount = transAmount;
	}

	public Date getTransDate()
	{
		return this.transDate;
	}

	public void setTransDate(Date transDate)
	{
		this.transDate = transDate;
	}

	public String getTransMode()
	{
		return this.transMode;
	}

	public void setTransMode(String transMode)
	{
		this.transMode = transMode;
	}

	@Id
	@GeneratedValue
	public Long getTransNumber()
	{
		return this.transNumber;
	}

	public void setTransNumber(Long transNumber)
	{
		this.transNumber = transNumber;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "Person_Id")
	public Person getTransPerson()
	{
		return this.transPerson;
	}

	public void setTransPerson(Person param)
	{
		this.transPerson = param;
	}

	public String getTransType()
	{
		return this.transType;
	}

	public void setTransType(String transType)
	{
		this.transType = transType;
	}

	@Version
	public Integer getVersion()
	{
		return this.version;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}

}
