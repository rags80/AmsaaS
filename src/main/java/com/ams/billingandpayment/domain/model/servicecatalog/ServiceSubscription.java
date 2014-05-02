package com.ams.billingandpayment.domain.model.servicecatalog;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.ams.users.domain.model.Person;

/**
 * Entity implementation class for Entity: ServiceProfile
 * 
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "T_SERVICESUBSCRIPTION")
public class ServiceSubscription implements Serializable
{

	private static final long	serialVersionUID	= 1L;

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	private Long		srvcSubcrptnId;
	private String		srvcSubcrptnName;
	private Person		srvcSubcrptnOfPerson;
	private Date		srvcSubcrptnStartDate;
	private Date		srvcSubcrptnEndDate;
	private Status		srvcSubcrptnStatus;

	private ServicePlan	subscribedSrvcsPlan;

	/*
	 * PERSON SERVICE PROFILE DOMAIN LOGIC
	 */

	public ServiceSubscription()
	{
		super();
	}

	@Column(name = "SUBSCRPTN_ENDDATE")
	public Date getSrvcSubcrptnEndDate()
	{
		return this.srvcSubcrptnEndDate;
	}

	/*
	 * ACCESSOR & MUTATORS
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUBSCRPTN_ID")
	public Long getSrvcSubcrptnId()
	{
		return this.srvcSubcrptnId;
	}

	@Column(name = "SUBSCRPTN_NAME")
	public String getSrvcSubcrptnName()
	{
		return this.srvcSubcrptnName;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID")
	@JsonIgnore
	public Person getSrvcSubcrptnOfPerson()
	{
		return this.srvcSubcrptnOfPerson;
	}

	@Column(name = "SUBSCRPTN_STARTDATE")
	public Date getSrvcSubcrptnStartDate()
	{
		return this.srvcSubcrptnStartDate;
	}

	@Column(name = "SUBSCRPTN_STATUS")
	public Status getSrvcSubcrptnStatus()
	{
		return this.srvcSubcrptnStatus;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SERVICEPLAN_ID")
	public ServicePlan getSubscribedSrvcsPlan()
	{
		return this.subscribedSrvcsPlan;
	}

	public boolean isSrvcSubcrptnStatus(Status status)
	{
		if (this.srvcSubcrptnStatus.equals(status))
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	public void setSrvcSubcrptnEndDate(Date srvcSubcrptnendDate)
	{
		this.srvcSubcrptnEndDate = srvcSubcrptnendDate;
	}

	public void setSrvcSubcrptnId(Long srvcSubcrptnId)
	{
		this.srvcSubcrptnId = srvcSubcrptnId;
	}

	public void setSrvcSubcrptnName(String srvcSubcrptnName)
	{
		this.srvcSubcrptnName = srvcSubcrptnName;
	}

	public void setSrvcSubcrptnOfPerson(Person srvcSubcrptnOfPerson)
	{
		this.srvcSubcrptnOfPerson = srvcSubcrptnOfPerson;
	}

	public void setSrvcSubcrptnStartDate(Date srvcSubcrptnStartDate)
	{
		this.srvcSubcrptnStartDate = srvcSubcrptnStartDate;
	}

	public void setSrvcSubcrptnStatus(Status srvcSubcrptnStatus)
	{
		this.srvcSubcrptnStatus = srvcSubcrptnStatus;
	}

	public void setSubscribedSrvcsPlan(ServicePlan srvcPlan)
	{
		this.subscribedSrvcsPlan = srvcPlan;
	}

}
