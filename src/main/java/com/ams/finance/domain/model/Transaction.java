package com.ams.finance.domain.model;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.ams.users.domain.model.Person;

@Entity
@Access(PROPERTY)
@Table(name = "T_TRANSACTION")
public class Transaction implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Long				transNumber;
	private float				transAmount;
	private Date				transDate;
	private String				transType;
	private String				transMode;
	private Account			transAccount;
	private Person				transPerson;
	protected Integer			version;

	public Transaction()
	{}

	@ManyToOne(optional = false)
	@JoinColumn(name = "Account_Number")
	public Account getTransAccount()
	{
		return transAccount;
	}

	public float getTransAmount()
	{
		return transAmount;
	}

	public Date getTransDate()
	{
		return transDate;
	}

	public String getTransMode()
	{
		return transMode;
	}

	@Id
	@GeneratedValue
	public Long getTransNumber()
	{
		return transNumber;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "Person_Id")
	public Person getTransPerson()
	{
		return transPerson;
	}

	public String getTransType()
	{
		return transType;
	}

	@Version
	public Integer getVersion()
	{
		return version;
	}

	public void setTransAccount(Account transAccount)
	{
		this.transAccount = transAccount;
	}

	public void setTransAmount(float transAmount)
	{
		this.transAmount = transAmount;
	}

	public void setTransDate(Date transDate)
	{
		this.transDate = transDate;
	}

	public void setTransMode(String transMode)
	{
		this.transMode = transMode;
	}

	public void setTransNumber(Long transNumber)
	{
		this.transNumber = transNumber;
	}

	public void setTransPerson(Person param)
	{
		this.transPerson = param;
	}

	public void setTransType(String transType)
	{
		this.transType = transType;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}

}
