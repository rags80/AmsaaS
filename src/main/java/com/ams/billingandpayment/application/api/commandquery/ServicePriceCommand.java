package com.ams.billingandpayment.application.api.commandquery;

public class ServicePriceCommand
{
	private String	srvcPlanName;
	private String	srvcCode;
	private String	srvcPriceCategory;
	private Double	srvcPriceAmountValue;
	private String	srvcPriceAmountCurrency;
	private String	srvcPriceUnitOfMeasure;

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

	public void setSrvcCode(String srvcCode)
	{
		this.srvcCode = srvcCode;
	}

	public void setSrvcPlanName(String srvcPlanName)
	{
		this.srvcPlanName = srvcPlanName;
	}

	public void setSrvcPriceAmountCurrency(String srvcPriceAmountCurrency)
	{
		this.srvcPriceAmountCurrency = srvcPriceAmountCurrency;
	}

	public void setSrvcPriceAmountValue(Double srvcPriceAmountValue)
	{
		this.srvcPriceAmountValue = srvcPriceAmountValue;
	}

	public void setSrvcPriceCategory(String srvcPriceCategory)
	{
		this.srvcPriceCategory = srvcPriceCategory;
	}

	public void setSrvcPriceUnitOfMeasure(String srvcPriceUnitOfMeasure)
	{
		this.srvcPriceUnitOfMeasure = srvcPriceUnitOfMeasure;
	}

}
