package com.ams.billingandpayment.application.api.dto;

public class ServicePriceCommand
{
	private String	srvcPlanName;
	private String	srvcCode;
	private String	srvcPriceCategory;
	private Double	srvcPriceAmountValue;
	private String	srvcPriceAmountCurrency;
	private String	srvcPriceUnitOfMeasure;

	public ServicePriceCommand(String planName, String srvcCode, String catgry,
							Double pricePerUnit, String currency, String unit)
	{
		this.srvcPlanName = planName;
		this.srvcCode = srvcCode;
		this.srvcPriceCategory = catgry;
		this.srvcPriceAmountValue = pricePerUnit;
		this.srvcPriceAmountCurrency = currency;
		this.srvcPriceUnitOfMeasure = unit;
	}

	public String getSrvcCode()
	{
		return this.srvcCode;
	}

	public String getSrvcPlanName()
	{
		return this.srvcPlanName;
	}

	public String getSrvcPriceAmountCurrency()
	{
		return this.srvcPriceAmountCurrency;
	}

	public Double getSrvcPriceAmountValue()
	{
		return this.srvcPriceAmountValue;
	}

	public String getSrvcPriceCategory()
	{
		return this.srvcPriceCategory;
	}

	public String getSrvcPriceUnitOfMeasure()
	{
		return this.srvcPriceUnitOfMeasure;
	}

}
