package com.ams.billingandpayment.domain.model.serviceportfolio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
	@ManyToOne
	@JoinColumn(name = "ServicePlan_Name")
	private ServicePlan				srvcPlan;

	@Id
	@ManyToOne
	@JoinColumn(name = "Service_Code")
	private Service				service;

	@Column(nullable = false,name = "SrvcPriceCategory")
	@Enumerated(EnumType.STRING)
	private ServicePriceCategory		srvcPriceCategory;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "SrvcPricePerUnit_Amount")),
			@AttributeOverride(name = "currency",column = @Column(name = "SrvcPricePerUnit_Currency"))
	})
	private Money					srvcPricePerUnit;

	@Enumerated(EnumType.STRING)
	@Column(name = "SrvcUnitOfMeasure")
	private TimeUnit				srvcUnitOfMeasure;

	@Transient
	private ServicePriceSpecification	srvcPriceSpec;

	public ServicePrice(ServicePlan srvcPlan, Service srvc, String srvcPriceCategory,
					BigDecimal pricePerUnit, String currencyCode,
					String unitOfMeasure)
	{
		this.srvcPlan = srvcPlan;
		this.service = srvc;
		this.srvcPriceCategory = ServicePriceCategory.valueOf(srvcPriceCategory);
		this.srvcPricePerUnit = new Money(pricePerUnit, Currency.getInstance(currencyCode));
		this.srvcUnitOfMeasure = TimeUnit.valueOf(unitOfMeasure);

	}

	public ServicePrice(ServicePlan servicePlan, Service srvc, String srvcPriceCategory,
					Money srvcPrice,
					String unitOfMeasure)
	{
		this.srvcPlan = servicePlan;
		this.service = srvc;
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
		this.srvcPriceSpec = spsAdvsr.adviseSpec(this);
		return this.srvcPriceSpec;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.service == null) ? 0 : this.service.hashCode());
		result = (prime * result) + ((this.srvcPlan == null) ? 0 : this.srvcPlan.hashCode());
		result = (prime * result) + ((this.srvcPriceCategory == null) ? 0 : this.srvcPriceCategory.hashCode());
		result = (prime * result) + ((this.srvcPricePerUnit == null) ? 0 : this.srvcPricePerUnit.hashCode());
		result = (prime * result) + ((this.srvcUnitOfMeasure == null) ? 0 : this.srvcUnitOfMeasure.hashCode());
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
		if (!(obj instanceof ServicePrice))
		{
			return false;
		}
		ServicePrice other = (ServicePrice) obj;
		if (this.service == null)
		{
			if (other.service != null)
			{
				return false;
			}
		}
		else if (!this.service.equals(other.service))
		{
			return false;
		}
		if (this.srvcPlan == null)
		{
			if (other.srvcPlan != null)
			{
				return false;
			}
		}
		else if (!this.srvcPlan.equals(other.srvcPlan))
		{
			return false;
		}
		if (this.srvcPriceCategory != other.srvcPriceCategory)
		{
			return false;
		}
		if (this.srvcPricePerUnit == null)
		{
			if (other.srvcPricePerUnit != null)
			{
				return false;
			}
		}
		else if (!this.srvcPricePerUnit.equals(other.srvcPricePerUnit))
		{
			return false;
		}
		if (this.srvcUnitOfMeasure != other.srvcUnitOfMeasure)
		{
			return false;
		}
		return true;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
