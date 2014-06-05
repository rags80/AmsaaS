package com.ams.users.domain.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.ams.billingandpayment.domain.model.serviceportfolio.ServiceSubscription;

@Entity
@Access(AccessType.FIELD)
@Table(name = "T_PERSON")
public class Person implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long				persnId;

	private String				persnFirstName;

	private String				persnLastName;

	@Embedded
	private PersonDetail		persnDetail;

	@Embedded
	private Address			persnAddress;

	@OneToOne
	private ServiceSubscription	persnServiceProfile;

	@Embedded
	private LoginDetails		persnLoginDetails;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Collection<UserRole>	persnRoles;

	Person()
	{}

	public Person(String firstName, String lastName)
	{
		this.persnFirstName = firstName;
		this.persnLastName = lastName;
	}

	public void setPersnDetails(PersonDetail detail)
	{
		this.persnDetail = detail;
	}

	public void setAddress(Address address)
	{
		this.persnAddress = address;
	}

	public void updateLoginDetails(String userName, String passwd)
	{
		this.persnLoginDetails = new LoginDetails(userName, passwd);
	}

	public Address getPersnAddress()
	{
		return this.persnAddress;
	}

	public PersonDetail getPersnDetail()
	{
		return this.persnDetail;
	}

	public String getPersnFirstName()
	{
		return this.persnFirstName;
	}

	public Long getPersnId()
	{
		return this.persnId;
	}

	public String getPersnLastName()
	{
		return this.persnLastName;
	}

	public Collection<UserRole> getPersnRoles()
	{
		return this.persnRoles;
	}

	@JsonIgnore
	public ServiceSubscription getPersnServiceProfile()
	{
		return this.persnServiceProfile;
	}

	public LoginDetails getPersnLoginDetails()
	{
		return this.persnLoginDetails;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
