package com.ams.finance.domain.model;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.ams.users.domain.model.Person;

// @Entity
@Access(PROPERTY)
@Table(name = "T_ACCOUNT")
public class Account implements Serializable
{
	/**
     *
     */
	private static final long	serialVersionUID	= 1L;
	@Version
	protected Integer			version;
	private Long				acntNumber;
	private String				acntName;
	private String				acntType;
	private String				acntCurrency;
	private float				acntBalance;
	private AccountDetail		acntDetail;
	private Date				acntCreationDate;
	private Set<Transaction>		acntTransactions;
	private Person				acntHolder;

	public Account()
	{}

	public void addTransaction(Transaction tran)
	{
		if (tran.getTransType().equalsIgnoreCase("credit"))
		{
			this.creditAmount(tran.getTransAmount());
		}
		else if (tran.getTransType().equalsIgnoreCase("debit"))
		{
			this.debitAmount(tran.getTransAmount());
		}

		this.getAcntTransactions().add(tran);
	}

	private void creditAmount(float amount)
	{
		this.acntBalance += amount;
	}

	private void debitAmount(float amount)
	{
		this.acntBalance -= amount;
	}

	public float getAcntBalance()
	{
		return this.acntBalance;
	}

	public void setAcntBalance(float balance)
	{
		this.acntBalance = balance;
	}

	public Date getAcntCreationDate()
	{
		return this.acntCreationDate;
	}

	public void setAcntCreationDate(Date acntCreationDate)
	{
		this.acntCreationDate = acntCreationDate;
	}

	public String getAcntCurrency()
	{
		return this.acntCurrency;
	}

	public void setAcntCurrency(String acntCurrency)
	{
		this.acntCurrency = acntCurrency;
	}

	@Embedded
	public AccountDetail getAcntDetail()
	{
		return this.acntDetail;
	}

	public void setAcntDetail(AccountDetail detail)
	{
		this.acntDetail = detail;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "Person_Id")
	public Person getAcntHolder()
	{
		return this.acntHolder;
	}

	public void setAcntHolder(Person person)
	{
		this.acntHolder = person;
	}

	public String getAcntName()
	{
		return this.acntName;
	}

	public void setAcntName(String acntName)
	{
		this.acntName = acntName;
	}

	@Id
	public Long getAcntNumber()
	{
		return this.acntNumber;
	}

	public void setAcntNumber(Long acntNumber)
	{
		this.acntNumber = acntNumber;
	}

	@OneToMany(cascade = CascadeType.ALL,targetEntity = Transaction.class,
				mappedBy = "transAccount")
	public Set<Transaction> getAcntTransactions()
	{
		return this.acntTransactions;
	}

	public void setAcntTransactions(Set<Transaction> transactions)
	{
		Iterator<Transaction> i = transactions.iterator();
		while (i.hasNext())
		{
			Transaction t = i.next();
			if (t.getTransType().equals("credit"))
			{
				this.creditAmount(t.getTransAmount());
			}
			else if (t.getTransType().equals("debit"))
			{
				this.debitAmount(t.getTransAmount());
			}
		}
		this.acntTransactions = transactions;
	}

	public String getAcntType()
	{
		return this.acntType;
	}

	public void setAcntType(String acntType)
	{
		this.acntType = acntType;
	}

	public Integer getVersion()
	{
		return this.version;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}

}
