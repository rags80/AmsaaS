package com.ams.sales.domain.model.servicecatalog;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class ServiceRateId implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String				splName;
	private String				svcCode;
	private ServiceChargeType	svcChargeType;

	public ServiceRateId()
	{}

	public ServiceRateId(String srvcPlanName, String srvcCode, ServiceChargeType srvcChargeType)
	{
		this.splName = srvcPlanName;
		this.svcCode = srvcCode;
		this.svcChargeType = srvcChargeType;
	}

	@Column(name = "ServicePlan_Id")
	public String getSplName()
	{
		return splName;
	}

	@SuppressWarnings("unused")
	private void setSplName(String splName)
	{
		this.splName = splName;
	}

	@Column(name = "Service_Code")
	public String getSvcCode()
	{
		return svcCode;
	}

	@SuppressWarnings("unused")
	private void setSvcCode(String svcCode)
	{
		this.svcCode = svcCode;
	}

	@Column(name = "ServiceCharge_Type")
	@Enumerated(EnumType.STRING)
	public ServiceChargeType getSvcChargeType()
	{
		return svcChargeType;
	}

	@SuppressWarnings("unused")
	private void setSvcChargeType(ServiceChargeType svcChargeType)
	{
		this.svcChargeType = svcChargeType;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((splName == null) ? 0 : splName.hashCode());
		result = (prime * result) + ((svcChargeType == null) ? 0 : svcChargeType.hashCode());
		result = (prime * result) + ((svcCode == null) ? 0 : svcCode.hashCode());
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
		if (!(obj instanceof ServiceRateId))
		{
			return false;
		}
		ServiceRateId other = (ServiceRateId) obj;
		if (splName == null)
		{
			if (other.splName != null)
			{
				return false;
			}
		}
		else if (!splName.equals(other.splName))
		{
			return false;
		}
		if (svcChargeType != other.svcChargeType)
		{
			return false;
		}
		if (svcCode == null)
		{
			if (other.svcCode != null)
			{
				return false;
			}
		}
		else if (!svcCode.equals(other.svcCode))
		{
			return false;
		}
		return true;
	}

}
