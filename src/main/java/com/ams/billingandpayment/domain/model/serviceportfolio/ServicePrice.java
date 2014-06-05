package com.ams.billingandpayment.domain.model.serviceportfolio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ams.billingandpayment.domain.service.ServicePriceSpecAdvisor;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.sharedkernel.domain.model.measuresandunits.TimeUnit;

/**
 * 
 * @author Raghavendra Badiger
 */

@Entity
@Access(AccessType.FIELD)
@Table(name = "T_SERVICEPRICE")
@IdClass(ServicePriceId.class)
public class ServicePrice implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	public enum ServicePriceCategory
	{
		NON_USAGE, USAGE
	}

	@Id
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "ServicePlan_Name",referencedColumnName = "srvcPlanName")
	private ServicePlan				srvcPlan;

	@Id
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "Service_Code",referencedColumnName = "srvcCode")
	private Service				service;

	@Column(nullable = false,name = "SrvcPriceCategory")
	@Enumerated(EnumType.STRING)
	private ServicePriceCategory		srvcPriceCategory;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "SrvcPricePerUnit_Amount")),
			@AttributeOverride(name = "currency",column = @Column(name = "SrvcPricePerUnit_Currency"))
	})
	@Column(nullable = false)
	private Money					srvcPricePerUnit;

	@Enumerated(EnumType.STRING)
	@Column(name = "SrvcUnitOfMeasure",nullable = false)
	private TimeUnit				srvcUnitOfMeasure;

	@Transient
	private ServicePriceSpecification	srvcPriceSpec;

	ServicePrice()
	{
		super();
	}

	public ServicePrice(ServicePlan srvcPlan, Service srvc, String srvcPriceCategory,
					BigDecimal pricePerUnit, String currencyCode,
					String unitOfMeasure)
	{

		this.service = srvc;
		this.srvcPlan = srvcPlan;
		this.srvcPriceCategory = ServicePriceCategory.valueOf(srvcPriceCategory);
		this.srvcPricePerUnit = new Money(pricePerUnit, Currency.getInstance(currencyCode));
		this.srvcUnitOfMeasure = TimeUnit.valueOf(unitOfMeasure);

	}

	public ServicePrice(ServicePlan servicePlan, Service srvc, String srvcPriceCategory,
					Money srvcPrice,
					String unitOfMeasure)
	{

		this.service = srvc;
		this.srvcPlan = servicePlan;
		this.srvcPriceCategory = ServicePriceCategory.valueOf(srvcPriceCategory);
		this.srvcPricePerUnit = srvcPrice;
		this.srvcUnitOfMeasure = TimeUnit.valueOf(unitOfMeasure);

	}

	/*
	 * 
	 * Domain functions
	 */

	public void updateDetails(String srvcPriceCategory, BigDecimal srvcPriceAmountValue, String srvcPriceAmountCurrency, String srvcPriceUnitOfMeasure)
	{
		this.srvcPriceCategory = ServicePriceCategory.valueOf(srvcPriceCategory);
		this.srvcPricePerUnit = new Money(srvcPriceAmountValue, Currency.getInstance(srvcPriceAmountCurrency));
		this.srvcUnitOfMeasure = TimeUnit.valueOf(srvcPriceUnitOfMeasure);
	}

	/*
	 * Accessor & Mutators
	 */
	public Service getService()
	{
		return this.service;
	}

	public ServicePlan getSrvcPlan()
	{
		return this.srvcPlan;
	}

	public ServicePriceCategory getSrvcPriceCategory()
	{
		return this.srvcPriceCategory;
	}

	public Money getSrvcPricePerUnit()
	{
		return this.srvcPricePerUnit;
	}

	public TimeUnit getSrvcUnitOfMeasure()
	{
		return this.srvcUnitOfMeasure;
	}

	public ServicePriceSpecification getSrvcPriceSpec(ServicePriceSpecAdvisor spsAdvsr)
	{
		if (this.srvcPriceSpec != null)
		{
			return this.srvcPriceSpec;
		}
		else
		{
			return this.srvcPriceSpec = spsAdvsr.adviseSpec(this);
		}

	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
