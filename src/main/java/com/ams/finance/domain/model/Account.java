package com.ams.finance.domain.model;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.ams.users.domain.model.Person;

@Entity
@Access(PROPERTY)
@Table(name = "T_ACCOUNT")
public class Account implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Long				acntNumber;
	private String				acntName;
	private String				acntType;
	private String				acntCurrency;
	private float				acntBalance;
	private AccountDetail		acntDetail;
	private Date				acntCreationDate;
	private Set<Transaction>		acntTransactions;
	private Person				acntHolder;

	@Version
	protected Integer			version;

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
		return acntBalance;
	}

	public Date getAcntCreationDate()
	{
		return acntCreationDate;
	}

	public String getAcntCurrency()
	{
		return acntCurrency;
	}

	@Embedded
	public AccountDetail getAcntDetail()
	{
		return acntDetail;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "Person_Id")
	public Person getAcntHolder()
	{
		return acntHolder;
	}

	public String getAcntName()
	{
		return acntName;
	}

	@Id
	public Long getAcntNumber()
	{
		return acntNumber;
	}

	@OneToMany(cascade = CascadeType.ALL,targetEntity = Transaction.class,
				mappedBy = "transAccount")
	public Set<Transaction> getAcntTransactions()
	{
		return acntTransactions;
	}

	public String getAcntType()
	{
		return acntType;
	}

	public Integer getVersion()
	{
		return version;
	}

	public void setAcntBalance(float balance)
	{
		this.acntBalance = balance;
	}

	public void setAcntCreationDate(Date acntCreationDate)
	{
		this.acntCreationDate = acntCreationDate;
	}

	public void setAcntCurrency(String acntCurrency)
	{
		this.acntCurrency = acntCurrency;
	}

	public void setAcntDetail(AccountDetail detail)
	{
		this.acntDetail = detail;
	}

	public void setAcntHolder(Person person)
	{
		this.acntHolder = person;
	}

	public void setAcntName(String acntName)
	{
		this.acntName = acntName;
	}

	public void setAcntNumber(Long acntNumber)
	{
		this.acntNumber = acntNumber;
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

	public void setAcntType(String acntType)
	{
		this.acntType = acntType;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}

}
