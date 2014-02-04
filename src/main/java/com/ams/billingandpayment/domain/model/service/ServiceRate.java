package com.ams.billingandpayment.domain.model.service;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.ams.sharedkernel.domain.model.Specification;
import com.ams.sharedkernel.domain.model.measureandunits.Frequency;
import com.ams.sharedkernel.domain.model.measureandunits.Rate;

@Entity
@Access(PROPERTY)
@Table(name = "T_SERVICERATE")
public class ServiceRate implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public enum ServiceRateCategory
	{
		NON_USAGE_CHARGES, USAGE_CHARGES
	};

	private ServiceRateId					srvcRateId;
	private ServicePlan						srvcPlan;
	// private ServiceRateCategory srvcRateCategory;
	private Service						service;
	private String							srvcChargeName;
	private Rate							srvcChargeRate;
	private Specification<ServiceSubscription>	srvcSubscrptnSpecification;
	private int							srvcChargePercentile;
	private Frequency						srvcChargeFrequency;
	private boolean						percentileBased	= false;

	public ServiceRate()
	{

	}

	public ServiceRate(ServicePlan srvcPlan, ServiceRateCategory srvcRateCatgry, Service srvc,
					String chargeName,
					ServiceChargeType chargeType,
					Rate chargeRate, Frequency chargeFreq,
					boolean percentileBased)
	{

		this.setSrvcRateId(new ServiceRateId(srvcPlan.getSrvcPlanName(), srvc.getSrvcCode(), chargeType));
		// this.srvcRateCategory = srvcRateCatgry;
		this.srvcPlan = srvcPlan;
		this.service = srvc;
		this.srvcChargeName = chargeName;
		this.srvcChargeRate = chargeRate;
		this.srvcChargeFrequency = chargeFreq;
		this.percentileBased = percentileBased;

	}

	/*
	 * SERVICE RATE COMPONENT DOMAIN FUNCTIONS
	 */

	/*
	 * ACCESSOR & MUTATORS
	 */

	@EmbeddedId
	public ServiceRateId getSrvcRateId()
	{
		return srvcRateId;
	}

	public void setSrvcRateId(ServiceRateId srvcRateId)
	{
		this.srvcRateId = srvcRateId;
	}

	@MapsId("splName")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ServicePlan_Id",referencedColumnName = "srvcPlanName")
	public ServicePlan getSrvcPlan()
	{
		return srvcPlan;
	}

	@JsonIgnore
	private void setSrvcPlan(ServicePlan srvcPlan)
	{
		this.srvcPlan = srvcPlan;
	}

	/*
	 * public ServiceRateCategory getSrvcRateCategory() { return
	 * srvcRateCategory; }
	 * 
	 * public void setSrvcRateCategory(ServiceRateCategory srvcRateCategory) {
	 * this.srvcRateCategory = srvcRateCategory; }
	 */

	@MapsId("svcCode")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Service_Code",referencedColumnName = "srvcCode")
	public Service getService()
	{
		return service;
	}

	@SuppressWarnings("unused")
	private void setService(Service service)
	{
		this.service = service;
	}

	public String getSrvcChargeName()
	{
		return srvcChargeName;
	}

	public void setSrvcChargeName(String chargeName)
	{
		this.srvcChargeName = chargeName;
	}

	public Rate getSrvcChargeRate()
	{
		return srvcChargeRate;
	}

	public void setSrvcChargeRate(Rate chargeRate)
	{
		this.srvcChargeRate = chargeRate;
	}

	public int getSrvcChargePercentile()
	{
		return srvcChargePercentile;
	}

	public void setSrvcChargePercentile(int chargePercentile)
	{
		this.srvcChargePercentile = chargePercentile;
	}

	public Frequency getSrvcChargeFrequency()
	{
		return srvcChargeFrequency;
	}

	public void setSrvcChargeFrequency(Frequency chargeFrequency)
	{
		this.srvcChargeFrequency = chargeFrequency;
	}

	public boolean isPercentileBased()
	{
		return percentileBased;
	}

	public void setPercentileBased(boolean percentileBased)
	{
		this.percentileBased = percentileBased;
	}

	public Specification<ServiceSubscription> srvcSubscrptnSpecification()
	{
		return srvcSubscrptnSpecification;
	}

	public void setSrvcSubscrptnSpecification(Specification<ServiceSubscription> srvcSubscrptnSpecification)
	{
		this.srvcSubscrptnSpecification = srvcSubscrptnSpecification;
	}
}
