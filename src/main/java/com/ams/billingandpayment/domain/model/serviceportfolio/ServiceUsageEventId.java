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

	ServiceUsageEventId()
	{}

	public ServiceUsageEventId(String srvc, long usr, Period usage)
	{
		this.srvc = srvc;
		this.srvcUser = usr;
		this.srvcUsagePeriod = usage;

	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public String getSrvc()
	{
		return this.srvc;
	}

	public long getSrvcUser()
	{
		return this.srvcUser;
	}

	public Period getSrvcUsagePeriod()
	{
		return this.srvcUsagePeriod;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.srvc == null) ? 0 : this.srvc.hashCode());
		result = (prime * result) + ((this.srvcUsagePeriod == null) ? 0 : this.srvcUsagePeriod.hashCode());
		result = (prime * result) + (int) (this.srvcUser ^ (this.srvcUser >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof ServiceUsageEventId))
		{
			return false;
		}
		ServiceUsageEventId other = (ServiceUsageEventId) obj;
		if (this.srvc == null)
		{
			if (other.srvc != null)
			{
				return false;
			}
		}
		else if (!this.srvc.equals(other.srvc))
		{
			return false;
		}
		if (this.srvcUsagePeriod == null)
		{
			if (other.srvcUsagePeriod != null)
			{
				return false;
			}
		}
		else if (!this.srvcUsagePeriod.equals(other.srvcUsagePeriod))
		{
			return false;
		}
		if (this.srvcUser != other.srvcUser)
		{
			return false;
		}
		return true;
	}

}
