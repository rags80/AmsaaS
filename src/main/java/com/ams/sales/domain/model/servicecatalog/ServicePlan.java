package com.ams.sales.domain.model.servicecatalog;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.ams.sharedkernel.domain.model.measuresandunits.Currency;
import com.ams.sharedkernel.domain.model.measuresandunits.Frequency;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.sharedkernel.domain.model.measuresandunits.Rate;
import com.ams.sharedkernel.domain.model.measuresandunits.Unit;

/**
 * @author Raghavendra Badiger
 * 
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
	private Date				srvcPlanCreatDate;
	private Set<ServiceRate>		serviceRateSet;

	public ServicePlan()
	{}

	public ServicePlan(String srvcName, Date srvcCreatDate)
	{
		this.srvcPlanName = srvcName;
		this.srvcPlanCreatDate = srvcCreatDate;
	}

	/*
	 * 
	 * SERVICE PLAN DOMAIN FUNCTIONS
	 */

	public void updateServicePlanDetails(String srvcPlanName, Date srvcPlanCreationDate)
	{
		if ((srvcPlanName != null) && (this.srvcPlanName != srvcPlanName))
		{
			this.srvcPlanName = srvcPlanName;
		}
		if ((srvcPlanCreationDate != null) && (this.srvcPlanCreatDate != srvcPlanCreationDate))
		{
			this.srvcPlanCreatDate = srvcPlanCreationDate;
		}
	}

	public ServiceRate addServiceRateToPlan(String srvcRateCatgry, Service srvc, String chargeName,
									String chargeType, Double rateAmount,
									String rateCurrency,
									String ratePerUnit, String chargeFreq, boolean percentBased)
	{
		Rate chargeRate = new Rate(new Money(new BigDecimal(rateAmount),
										Currency.valueOf(rateCurrency)), Unit.valueOf(ratePerUnit));
		ServiceRate srvcRate = new ServiceRate(this, ServiceRateCategory.valueOf(srvcRateCatgry), srvc, chargeName, ServiceChargeType.valueOf(chargeType),
										chargeRate, Frequency.valueOf(chargeFreq), percentBased);
		return srvcRate;
	}

	public ServiceRate getServiceRateFromPlan(Service srvc)
	{
		for (ServiceRate srate : this.serviceRateSet)
		{
			if (srate.getService().getSrvcCode().equals(srvc.getSrvcCode()))
			{
				return srate;
			}

		}

		return null;
	}

	/*
	 * ACCESSOR & MUTATORS
	 */

	@Id
	public String getSrvcPlanName()
	{
		return srvcPlanName;
	}

	@SuppressWarnings("unused")
	private void setSrvcPlanName(String srvcPlanName)
	{
		this.srvcPlanName = srvcPlanName;
	}

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonSerialize
	public Date getSrvcPlanCreatDate()
	{
		return srvcPlanCreatDate;
	}

	@SuppressWarnings("unused")
	@JsonDeserialize
	private void setSrvcPlanCreatDate(Date srvcPlanCreatDate)
	{
		this.srvcPlanCreatDate = srvcPlanCreatDate;
	}

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "srvcPlan",targetEntity = ServiceRate.class,fetch = FetchType.EAGER)
	public Set<ServiceRate> getServiceRateSet()
	{
		return serviceRateSet;
	}

	@SuppressWarnings("unused")
	private void setServiceRateSet(Set<ServiceRate> serviceRateSet)
	{
		this.serviceRateSet = serviceRateSet;
	}

}
