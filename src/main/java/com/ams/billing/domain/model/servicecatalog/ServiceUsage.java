package com.ams.billing.domain.model.servicecatalog;

import java.io.Serializable;

import javax.persistence.Table;

import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.sharedkernel.domain.model.measuresandunits.Quantity;
import com.ams.sharedkernel.domain.model.measuresandunits.TimeUnit;
import com.ams.usermanagement.domain.model.Person;

@Table(name = "T_SERVICEUSAGE")
public class ServiceUsage implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Service			srvc;
	private Person				srvcUser;
	private Period				srvcUsagePeriod;

	public ServiceUsage()
	{}

	public ServiceUsage(Person persn, Service srvc, Period duration)
	{
		this.srvc = srvc;
		this.srvcUser = persn;
		this.srvcUsagePeriod = duration;

	}

	/*
	 * SERVICE CHARGE DOMAIN FUNCTIONS
	 */

	Quantity srvcUsageQuantity(TimeUnit unitOfMeasure)
	{
		return Quantity.quantify(this.srvcUsagePeriod, unitOfMeasure);

	}

	/*
	 * SERVICE CHARGE ACCESSOR & MUTATOR FUNCTIONS
	 */

	public Service getSrvc()
	{
		return this.srvc;
	}

	private void setSrvc(Service srvc)
	{
		this.srvc = srvc;
	}

	public Person getSrvcUser()
	{
		return this.srvcUser;
	}

	private void setSrvcUser(Person srvcUser)
	{
		this.srvcUser = srvcUser;
	}

	public Period getSrvcUsagePeriod()
	{
		return this.srvcUsagePeriod;
	}

	void setSrvcUsagePeriod(Period srvcUsagePeriod)
	{
		this.srvcUsagePeriod = srvcUsagePeriod;
	}
}
