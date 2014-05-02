package com.ams.billingandpayment.domain.model.servicecatalog;

import java.io.Serializable;

import javax.persistence.Table;

import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.sharedkernel.domain.model.measuresandunits.Quantity;
import com.ams.sharedkernel.domain.model.measuresandunits.TimeUnit;
import com.ams.users.domain.model.Person;

/**
 * @author Raghavendra Badiger
 * 
 */
@Table(name = "T_SERVICEUSAGE_EVENTS")
public class ServiceUsageEvent implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Service			srvc;
	private Person				srvcUser;
	private Period				srvcUsagePeriod;

	public ServiceUsageEvent()
	{}

	public ServiceUsageEvent(Person persn, Service srvc, Period duration)
	{
		this.srvc = srvc;
		this.srvcUser = persn;
		this.srvcUsagePeriod = duration;

	}

	/*
	 * SERVICE CHARGE DOMAIN FUNCTIONS
	 */

	public Service getSrvc()
	{
		return this.srvc;
	}

	public Period getSrvcUsagePeriod()
	{
		return this.srvcUsagePeriod;
	}

	public Quantity srvcUsageQuantity(TimeUnit unitOfMeasure)
	{
		return Quantity.quantify(this.srvcUsagePeriod, unitOfMeasure);

	}

	/*
	 * SERVICE CHARGE ACCESSOR & MUTATOR FUNCTIONS
	 */

	public Quantity getSrvcUsageQty()
	{

		return null;
	}

	public Person getSrvcUser()
	{
		return this.srvcUser;
	}

	private void setSrvc(Service srvc)
	{
		this.srvc = srvc;
	}

	private void setSrvcUsagePeriod(Period srvcUsagePeriod)
	{
		this.srvcUsagePeriod = srvcUsagePeriod;
	}

	private void setSrvcUser(Person srvcUser)
	{
		this.srvcUser = srvcUser;
	}

}
