package com.ams.users.domain.model;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.ams.billingandpayment.domain.model.bill.Bill;
import com.ams.billingandpayment.domain.model.bill.Payment;
import com.ams.billingandpayment.domain.model.services.ServiceSubscription;
import com.ams.finance.domain.model.Account;
import com.ams.finance.domain.model.Transaction;

@Entity
@Access(PROPERTY)
@Table(name = "T_PERSON")
public class Person implements Serializable
{
	private static final long		serialVersionUID	= 1L;
	private Long					persnId;
	private String					persnFirstName;
	private String					persnLastName;
	private PersonDetail			persnDetail;
	private Address				persnAddress;
	private ServiceSubscription		persnServiceProfile;
	private Collection<Account>		persnAccounts;
	private Collection<Bill>			persnBill;
	private Collection<Transaction>	persnTransactions;
	private Collection<Payment>		persnPayments;
	private Collection<UserRole>		persnRoles;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "acntHolder",targetEntity = Account.class,fetch = FetchType.LAZY)
	public Collection<Account> getPersnAccounts()
	{
		return this.persnAccounts;
	}

	/**
	 * @param accounts
	 *             the accounts to set
	 */
	public void setPersnAccounts(Collection<Account> accounts)
	{

		this.persnAccounts = accounts;
	}

	@Embedded
	public Address getPersnAddress()
	{
		return this.persnAddress;
	}

	public void setPersnAddress(Address address)
	{
		this.persnAddress = address;
	}

	@OneToMany(mappedBy = "billedPerson",fetch = FetchType.LAZY)
	@JsonIgnore
	public Collection<Bill> getPersnBill()
	{
		return this.persnBill;
	}

	public void setPersnBill(Collection<Bill> param)
	{
		this.persnBill = param;
	}

	@Embedded
	public PersonDetail getPersnDetail()
	{
		return this.persnDetail;
	}

	public void setPersnDetail(PersonDetail detail)
	{
		this.persnDetail = detail;
	}

	public String getPersnFirstName()
	{
		return this.persnFirstName;
	}

	public void setPersnFirstName(String firstName)
	{
		this.persnFirstName = firstName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getPersnId()
	{
		return this.persnId;
	}

	public void setPersnId(Long id)
	{
		this.persnId = id;
	}

	public String getPersnLastName()
	{
		return this.persnLastName;
	}

	public void setPersnLastName(String lastName)
	{
		this.persnLastName = lastName;
	}

	@OneToMany(mappedBy = "paymntPerson",targetEntity = Payment.class,cascade = CascadeType.ALL,
				orphanRemoval = false,fetch = FetchType.LAZY)
	public Collection<Payment> getPersnPayments()
	{
		return this.persnPayments;
	}

	public void setPersnPayments(Collection<Payment> param)
	{
		this.persnPayments = param;
	}

	@ElementCollection
	@Enumerated(EnumType.STRING)
	public Collection<UserRole> getPersnRoles()
	{
		return this.persnRoles;
	}

	public void setPersnRoles(Collection<UserRole> persnRoles)
	{
		this.persnRoles = persnRoles;
	}

	@OneToOne(mappedBy = "srvcSubcrptnOfPerson",fetch = FetchType.LAZY)
	@JsonIgnore
	public ServiceSubscription getPersnServiceProfile()
	{
		return this.persnServiceProfile;
	}

	public void setPersnServiceProfile(ServiceSubscription persnServiceProfile)
	{
		this.persnServiceProfile = persnServiceProfile;
	}

	@OneToMany(mappedBy = "transPerson",cascade = CascadeType.ALL,orphanRemoval = false,
				targetEntity = Transaction.class)
	public Collection<Transaction> getPersnTransactions()
	{
		return this.persnTransactions;
	}

	public void setPersnTransactions(Collection<Transaction> param)
	{
		this.persnTransactions = param;
	}

}
