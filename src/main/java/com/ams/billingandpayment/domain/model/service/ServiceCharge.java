package com.ams.billingandpayment.domain.model.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ams.sharedkernel.domain.model.measureandunits.Currency;
import com.ams.sharedkernel.domain.model.measureandunits.Money;
import com.ams.sharedkernel.domain.model.measureandunits.Period;
import com.ams.sharedkernel.domain.model.measureandunits.Quantity;
import com.ams.sharedkernel.domain.model.measureandunits.Unit;
import com.ams.usermanagement.domain.model.Person;

/**
 * @author Raghavendra Badiger
 * 
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "T_SERVICECHARGE")
public class ServiceCharge implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private Long				srvcChargeId;
	private String				srvcChargeName;
	private Date				srvcChargeDate;
	private Quantity			chargeQuantity;

	private Money				chargedAmount;

	private Money				discountAmount;

	private Money				taxOnAmount;

	private Money				netAmount;
	private Service			chargeForService;
	private Person				chargedPerson;
	private ServiceUsage		srvcUsage;
	private boolean			billed;

	/*
	 * SERVICE CHARGE DOMAIN FUNCTIONS
	 */

	public Money forUsage(ServiceUsage srvcUsage, ServiceRate
						serviceRate, Date chargedDate)
	{

		this.srvcUsage = srvcUsage;
		this.chargedPerson = srvcUsage.getPersn();
		this.chargeForService = srvcUsage.getSrvc();
		if (chargedDate != null)
		{
			this.setSrvcChargeDate(chargedDate);
		}
		else
		{
			this.setSrvcChargeDate(new Date());
		}

		Unit usageUnit = serviceRate.getSrvcChargeRate().getPerUnit();
		srvcUsage.calculateQuantity(usageUnit);
		this.calculateCharge(srvcUsage.getSrvcUsageQty(), serviceRate.getSrvcChargeRate().getPrice().getAmount(), serviceRate.getSrvcChargeRate().getPrice().getCurrency());
		return this.chargedAmount;
	}

	public Money nonUsage(Person persnCharged, ServiceRate srvcRate, Period billPeriod, Date chargeDate)
	{

		this.chargedPerson = persnCharged;

		return chargedAmount;

	}

	private void calculateCharge(Quantity qty, BigDecimal amount, Currency currency)
	{
		this.chargeQuantity = qty;
		this.chargedAmount = new Money((this.chargeQuantity.getValue().multiply(amount)).setScale(2, RoundingMode.FLOOR), currency);
	}

	/*
	 * SERVICE CHARGE ACCESSOR N MUTATOR FUNCTIONS
	 */
	@Id
	public Long getSrvcChargeId()
	{
		return srvcChargeId;
	}

	public void setSrvcChargeId(Long srvcChargeId)
	{
		this.srvcChargeId = srvcChargeId;
	}

	public String getSrvcChargeName()
	{
		return srvcChargeName;
	}

	public void setSrvcChargeName(String srvcChargeName)
	{
		this.srvcChargeName = srvcChargeName;
	}

	@Temporal(TemporalType.DATE)
	public Date getSrvcChargeDate()
	{
		return srvcChargeDate;
	}

	public void setSrvcChargeDate(Date srvcChargeDate)
	{
		this.srvcChargeDate = srvcChargeDate;
	}

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "chargedAmount")),
			@AttributeOverride(name = "currency",column = @Column(name = "chargedAmount_currency")) })
	public Money getChargedAmount()
	{
		return chargedAmount;
	}

	public void setChargedAmount(Money chargedAmount)
	{
		this.chargedAmount = chargedAmount;
	}

	@Embedded
	public Quantity getChargeQuantity()
	{
		return chargeQuantity;
	}

	public void setChargeQuantity(Quantity chargeQuantity)
	{
		this.chargeQuantity = chargeQuantity;
	}

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "discountAmount")),
			@AttributeOverride(name = "currency",column = @Column(name = "discountAmount_currency")) })
	public Money getDiscountAmount()
	{
		return discountAmount;
	}

	public void setDiscountAmount(Money discountAmount)
	{
		this.discountAmount = discountAmount;
	}

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "taxOnAmount")),
			@AttributeOverride(name = "currency",column = @Column(name = "taxOnAmount_currency")) })
	public Money getTaxOnAmount()
	{
		return taxOnAmount;
	}

	public void setTaxOnAmount(Money taxOnAmount)
	{
		this.taxOnAmount = taxOnAmount;
	}

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "amount",column = @Column(name = "netAmount")),
			@AttributeOverride(name = "currency",column = @Column(name = "netAmount_currency")) })
	public Money getNetAmount()
	{
		return netAmount;
	}

	public void setNetAmount(Money netAmount)
	{
		this.netAmount = netAmount;
	}

	public Service getChargeForService()
	{
		return chargeForService;
	}

	public void setChargeForService(Service chargeForService)
	{
		this.chargeForService = chargeForService;
	}

	public Person getChargedPerson()
	{
		return chargedPerson;
	}

	public void setChargedPerson(Person chargedPerson)
	{
		this.chargedPerson = chargedPerson;
	}

	public ServiceUsage getSrvcUsage()
	{
		return srvcUsage;
	}

	public void setSrvcUsage(ServiceUsage srvcUsage)
	{
		this.srvcUsage = srvcUsage;
	}

	@Enumerated(EnumType.STRING)
	public boolean isBilled()
	{
		return billed;
	}

	public void setBilled(boolean billed)
	{
		this.billed = billed;
	}

}
