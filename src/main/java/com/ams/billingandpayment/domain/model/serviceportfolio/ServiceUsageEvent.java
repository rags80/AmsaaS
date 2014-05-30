package com.ams.billingandpayment.domain.model.serviceportfolio;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.users.domain.model.Person;

/**
 * 
 * @author Raghavendra Badiger
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "T_SERVICEUSAGE_EVENTS")
public class ServiceUsageEvent implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	@EmbeddedId
	ServiceUsageEventId			sueId;

	@ManyToOne
	@JoinColumn(name = "Service_Code")
	private Service			srvc;

	@ManyToOne
	@JoinColumn(name = "SrvcUser_Id")
	private Person				srvcUser;

	@Embedded
	private Period				srvcUsagePeriod;

	public ServiceUsageEvent(Person persn, Service srvc, Period duration)
	{
		this.sueId = new ServiceUsageEventId(srvc.getSrvcCode(), persn.getPersnId(), duration.getFromDate(), duration.getToDate());
		this.srvc = srvc;
		this.srvcUser = persn;
		this.srvcUsagePeriod = duration;

	}

	/*
	 * SERVICE USAGE EVENT ACCESSOR FUNCTIONS
	 */

	public Service getSrvc()
	{
		return this.srvc;
	}

	public Period getSrvcUsagePeriod()
	{
		return this.srvcUsagePeriod;
	}

	public Person getSrvcUser()
	{
		return this.srvcUser;
	}

}
