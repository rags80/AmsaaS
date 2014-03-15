package com.ams.billing.application.api.commandquery;

public class ServicePlanDto
{
	private String	srvcPlanName;
	private String	srvcCode;
	private String	chargeName;
	private String	chargeType;
	private Double	rateAmount;
	private String	rateCurrency;
	private String	ratePerUnit;
	private String	chargeFreq;

	public String getSrvcPlanName()
	{
		return srvcPlanName;
	}

	public void setSrvcPlanName(String srvcPlanName)
	{
		this.srvcPlanName = srvcPlanName;
	}

	public String getSrvcCode()
	{
		return srvcCode;
	}

	public void setSrvcCode(String srvcCode)
	{
		this.srvcCode = srvcCode;
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

	public Double getRateAmount()
	{
		return rateAmount;
	}

	public void setRateAmount(Double rateAmount)
	{
		this.rateAmount = rateAmount;
	}

	public String getRateCurrency()
	{
		return rateCurrency;
	}

	public void setRateCurrency(String rateCurrency)
	{
		this.rateCurrency = rateCurrency;
	}

	public String getRatePerUnit()
	{
		return ratePerUnit;
	}

	public void setRatePerUnit(String ratePerUnit)
	{
		this.ratePerUnit = ratePerUnit;
	}

	public String getChargeFreq()
	{
		return chargeFreq;
	}

	public void setChargeFreq(String chargeFreq)
	{
		this.chargeFreq = chargeFreq;
	}

}
