/**
 * 
 */
package com.ams.billingandpayment.domain.model.serviceportfolio;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.ams.sharedkernel.domain.model.measuresandunits.Period;

/**
 * @author Raghavendra Badiger
 * 
 */
@Embeddable
public class ServiceUsageEventId implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private String				srvc;
	private long				srvcUser;
	private Period				srvcUsagePeriod;

	public String getSrvc()
	{
		return this.srvc;
	}

	public void setSrvc(String srvc)
	{
		this.srvc = srvc;
	}

	public long getSrvcUser()
	{
		return this.srvcUser;
	}

	public void setSrvcUser(long srvcUser)
	{
		this.srvcUser = srvcUser;
	}

	public Period getSrvcUsagePeriod()
	{
		return this.srvcUsagePeriod;
	}

	public void setSrvcUsagePeriod(Period srvcUsagePeriod)
	{
		this.srvcUsagePeriod = srvcUsagePeriod;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
