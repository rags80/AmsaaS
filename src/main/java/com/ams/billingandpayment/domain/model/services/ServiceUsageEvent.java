package com.ams.billingandpayment.domain.model.services;

import java.io.Serializable;

import javax.persistence.Table;

import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.users.domain.model.Person;

/**
 * @author Raghavendra Badiger
 */
@Table(name = "T_SERVICEUSAGE_EVENTS")
public class ServiceUsageEvent implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private Service			srvc;
	private Person				srvcUser;
	private Period				srvcUsagePeriod;

	public ServiceUsageEvent(Person persn, Service srvc, Period duration)
	{
		this.srvc = srvc;
		this.srvcUser = persn;
		this.srvcUsagePeriod = duration;

	}

	/*
	 * SERVICE CHARGE ACCESSOR FUNCTIONS
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
