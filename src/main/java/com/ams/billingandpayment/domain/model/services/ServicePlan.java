package com.ams.billingandpayment.domain.model.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.ams.billingandpayment.domain.model.services.ServiceSubscription.Status;
import com.ams.billingandpayment.domain.service.ServicePriceSpecAdvisor;

/**
 * @author Raghavendra Badiger
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "T_SERVICEPLAN")
public class ServicePlan implements Serializable
{
	/**
     *
     */
	private static final long	serialVersionUID	= 1L;

	private String				srvcPlanName;
	private String				srvcPlanDescription;
	private Date				srvcPlanCreatDate;
	private Status				srvcPlanStatus;
	private Set<ServicePrice>	srvcPriceSet;

	public ServicePlan()
	{}

	public ServicePlan(String planName, String description, Date planCreatDate, String status)
	{
		this.srvcPlanName = planName;
		this.srvcPlanDescription = description;
		this.srvcPlanCreatDate = planCreatDate;
		this.srvcPlanStatus = Status.valueOf(status);
		this.srvcPriceSet = new HashSet<ServicePrice>();
	}

	/*
	 * 
	 * SERVICE PLAN DOMAIN FUNCTIONS
	 */

	public ServicePrice servicePriceToPlan(String srvcPriceCategory, Service srvc, Double chargeAmount, String chargeCurrency, String chargeUnit, ServicePriceSpecAdvisor spsAdvisr)
	{
		ServicePrice psp = new ServicePrice(this, srvc, srvcPriceCategory, BigDecimal.valueOf(chargeAmount), chargeCurrency, chargeUnit, spsAdvisr);
		this.srvcPriceSet.add(psp);
		return psp;
	}

	public void updateServicePlanDetails(String description, Date planCreatDate, String status)
	{
		this.srvcPlanDescription = description;
		this.srvcPlanCreatDate = planCreatDate;
		this.srvcPlanStatus = Status.valueOf(status);
	}

	/*
	 * 
	 * ACCESSOR & MUTATOR FUNCTIONS
	 */

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonSerialize
	public Date getSrvcPlanCreatDate()
	{
		return this.srvcPlanCreatDate;
	}

	@SuppressWarnings("unused")
	@JsonDeserialize
	private void setSrvcPlanCreatDate(Date srvcPlanCreatDate)
	{
		this.srvcPlanCreatDate = srvcPlanCreatDate;
	}

	public String getSrvcPlanDescription()
	{
		return this.srvcPlanDescription;
	}

	public void setSrvcPlanDescription(String srvcPlanDescription)
	{
		this.srvcPlanDescription = srvcPlanDescription;
	}

	@Id
	public String getSrvcPlanName()
	{
		return this.srvcPlanName;
	}

	@SuppressWarnings("unused")
	private void setSrvcPlanName(String srvcPlanName)
	{
		this.srvcPlanName = srvcPlanName;
	}

	@Enumerated(EnumType.STRING)
	public Status getSrvcPlanStatus()
	{
		return this.srvcPlanStatus;
	}

	public void setSrvcPlanStatus(Status srvcPlanStatus)
	{
		this.srvcPlanStatus = srvcPlanStatus;
	}

	@OneToMany(mappedBy = "srvcPlan",targetEntity = ServicePrice.class)
	@JsonIgnore
	public Set<ServicePrice> getSrvcPriceSet()
	{
		return this.srvcPriceSet;
	}

	private void setSrvcPriceSet(Set<ServicePrice> srvcPriceSet)
	{
		this.srvcPriceSet = srvcPriceSet;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.srvcPlanCreatDate == null) ? 0 : this.srvcPlanCreatDate.hashCode());
		result = (prime * result) + ((this.srvcPlanDescription == null) ? 0 : this.srvcPlanDescription.hashCode());
		result = (prime * result) + ((this.srvcPlanName == null) ? 0 : this.srvcPlanName.hashCode());
		result = (prime * result) + ((this.srvcPlanStatus == null) ? 0 : this.srvcPlanStatus.hashCode());
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
		if (!(obj instanceof ServicePlan))
		{
			return false;
		}
		ServicePlan other = (ServicePlan) obj;
		if (this.srvcPlanCreatDate == null)
		{
			if (other.srvcPlanCreatDate != null)
			{
				return false;
			}
		}
		else if (!this.srvcPlanCreatDate.equals(other.srvcPlanCreatDate))
		{
			return false;
		}
		if (this.srvcPlanDescription == null)
		{
			if (other.srvcPlanDescription != null)
			{
				return false;
			}
		}
		else if (!this.srvcPlanDescription.equals(other.srvcPlanDescription))
		{
			return false;
		}
		if (this.srvcPlanName == null)
		{
			if (other.srvcPlanName != null)
			{
				return false;
			}
		}
		else if (!this.srvcPlanName.equals(other.srvcPlanName))
		{
			return false;
		}
		if (this.srvcPlanStatus != other.srvcPlanStatus)
		{
			return false;
		}
		return true;
	}

}
