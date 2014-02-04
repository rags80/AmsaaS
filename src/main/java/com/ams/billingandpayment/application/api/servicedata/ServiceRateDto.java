package com.ams.billingandpayment.application.api.servicedata;

import com.ams.billingandpayment.domain.model.service.Service;

public class ServiceRateDto
{
	private String		srvcPlanName;
	private String		srvcRateCategory;
	private Service	service;
	private String		chargeName;
	private String		chargeType;
	private Double		chargeAmount;
	private String		chargeCurrency;
	private String		chargeUnit;
	private String		chargeFrequency;
	private boolean	percentBased;

	public String getSrvcPlanName()
	{
		return srvcPlanName;
	}

	public void setSrvcPlanName(String srvcPlanName)
	{
		this.srvcPlanName = srvcPlanName;
	}

	public String getSrvcRateCategory()
	{
		return srvcRateCategory;
	}

	public void setSrvcRateCategory(String srvcRateCategory)
	{
		this.srvcRateCategory = srvcRateCategory;
	}

	public Service getService()
	{
		return service;
	}

	public void setService(Service service)
	{
		this.service = service;
	}

	public String getChargeName()
	{
		return chargeName;
	}

	public void setChargeName(String chargeName)
	{
		this.chargeName = chargeName;
	}

	public String getChargeType()
	{
		return chargeType;
	}

	public void setChargeType(String chargeType)
	{
		this.chargeType = chargeType;
	}

	public Double getChargeAmount()
	{
		return chargeAmount;
	}

	public void setChargeAmount(Double chargeAmount)
	{
		this.chargeAmount = chargeAmount;
	}

	public String getChargeCurrency()
	{
		return chargeCurrency;
	}

	public void setChargeCurrency(String chargeCurrency)
	{
		this.chargeCurrency = chargeCurrency;
	}

	public String getChargeUnit()
	{
		return chargeUnit;
	}

	public void setChargeUnit(String chargeUnit)
	{
		this.chargeUnit = chargeUnit;
	}

	public String getChargeFrequency()
	{
		return chargeFrequency;
	}

	public void setChargeFrequency(String chargeFrequency)
	{
		this.chargeFrequency = chargeFrequency;
	}

	public boolean isPercentBased()
	{
		return percentBased;
	}

	public void setPercentBased(boolean percentBased)
	{
		this.percentBased = percentBased;
	}

}
