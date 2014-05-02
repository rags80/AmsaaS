package com.ams.finance.application.api.servicedata;

import java.util.Date;

public class AccountServiceData
{

	private long	number;
	private String	name;
	private String	type;
	private String	currency;
	private float	balance;
	private String	detail;
	private Date	creationDate;
	private long	personId;

	public float getBalance()
	{
		return balance;
	}

	public Date getCreationDate()
	{
		return creationDate;
	}

	public String getCurrency()
	{
		return currency;
	}

	public String getDetail()
	{
		return detail;
	}

	public String getName()
	{
		return name;
	}

	public long getNumber()
	{
		return number;
	}

	public long getPersonId()
	{
		return personId;
	}

	public String getType()
	{
		return type;
	}

	public void setBalance(float balance)
	{
		this.balance = balance;
	}

	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}

	public void setCurrency(String currency)
	{
		this.currency = currency;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setNumber(long l)
	{
		this.number = l;
	}

	public void setPersonId(long l)
	{
		this.personId = l;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
