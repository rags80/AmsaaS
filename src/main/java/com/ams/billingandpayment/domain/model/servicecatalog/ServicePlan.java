package com.ams.billingandpayment.domain.model.servicecatalog;

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

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonSerialize
	public Date getSrvcPlanCreatDate()
	{
		return this.srvcPlanCreatDate;
	}

	public String getSrvcPlanDescription()
	{
		return this.srvcPlanDescription;
	}

	/*
	 * public ServiceRate addServiceRateToPlan(String srvcRateCatgry, Service
	 * srvc, String chargeName, String chargeType, Double rateAmount, String
	 * rateCurrency, String ratePerUnit, String chargeFreq, boolean
	 * percentBased) { Rate chargeRate = new Rate(new Money(new
	 * BigDecimal(rateAmount), Currency.valueOf(rateCurrency)),
	 * Unit.valueOf(ratePerUnit)); ServiceRate srvcRate = new ServiceRate(this,
	 * ServiceCategory.valueOf(srvcRateCatgry), srvc, chargeName,
	 * ServiceChargeType.valueOf(chargeType), chargeRate,
	 * Frequency.valueOf(chargeFreq), percentBased); return srvcRate; }
	 * 
	 * public ServiceRate getServiceRateFromPlan(Service srvc) { for
	 * (ServiceRate srate : this.serviceRateSet) { if
	 * (srate.getService().getSrvcCode().equals(srvc.getSrvcCode())) { return
	 * srate; }
	 * 
	 * }
	 * 
	 * return null; }
	 */

	/*
	 * ACCESSOR & MUTATORS
	 */

	@Id
	public String getSrvcPlanName()
	{
		return this.srvcPlanName;
	}

	@Enumerated(EnumType.STRING)
	public Status getSrvcPlanStatus()
	{
		return this.srvcPlanStatus;
	}

	@OneToMany(mappedBy = "srvcPlan",targetEntity = ServicePrice.class)
	@JsonIgnore
	public Set<ServicePrice> getSrvcPriceSet()
	{
		return this.srvcPriceSet;
	}

	/**
	 * @param srvcRateCategory
	 * @param chargeName
	 * @param chargeAmount
	 * @param chargeCurrency
	 * @param chargeUnit
	 * @return
	 */
	public ServicePrice servicePriceToPlan(String srvcPriceCategory, Service srvc, Double chargeAmount, String chargeCurrency, String chargeUnit)
	{
		ServicePrice psp = new ServicePrice(this, srvc, srvcPriceCategory, BigDecimal.valueOf(chargeAmount), chargeCurrency, chargeUnit);
		this.srvcPriceSet.add(psp);
		return psp;
	}

	@SuppressWarnings("unused")
	@JsonDeserialize
	private void setSrvcPlanCreatDate(Date srvcPlanCreatDate)
	{
		this.srvcPlanCreatDate = srvcPlanCreatDate;
	}

	public void setSrvcPlanDescription(String srvcPlanDescription)
	{
		this.srvcPlanDescription = srvcPlanDescription;
	}

	@SuppressWarnings("unused")
	private void setSrvcPlanName(String srvcPlanName)
	{
		this.srvcPlanName = srvcPlanName;
	}

	public void setSrvcPlanStatus(Status srvcPlanStatus)
	{
		this.srvcPlanStatus = srvcPlanStatus;
	}

	private void setSrvcPriceSet(Set<ServicePrice> srvcPriceSet)
	{
		this.srvcPriceSet = srvcPriceSet;
	}

	public void updateServicePlanDetails(String description, Date planCreatDate, String status)
	{
		this.srvcPlanDescription = description;
		this.srvcPlanCreatDate = planCreatDate;
		this.srvcPlanStatus = Status.valueOf(status);
	}

}
