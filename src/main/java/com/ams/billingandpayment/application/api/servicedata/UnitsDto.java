package com.ams.billingandpayment.application.api.servicedata;

import java.util.ArrayList;
import java.util.List;

import com.ams.billingandpayment.domain.model.service.ServiceChargeType;
import com.ams.sharedkernel.domain.model.measureandunits.Currency;
import com.ams.sharedkernel.domain.model.measureandunits.Frequency;
import com.ams.sharedkernel.domain.model.measureandunits.Unit;

public class UnitsDto
{
	private List<String>	currencies;
	private List<String>	units;
	private List<String>	frequencies;
	private List<String>	chargeTypes;

	public UnitsDto()
	{
		this.currencies = new ArrayList<String>(10);
		for (Currency c : Currency.values())
		{
			this.currencies.add(c.toString());
		}

		this.units = new ArrayList<String>(10);
		for (Unit c : Unit.values())
		{
			this.units.add(c.toString());
		}

		this.frequencies = new ArrayList<String>(10);
		for (Frequency c : Frequency.values())
		{
			this.frequencies.add(c.toString());
		}

		this.chargeTypes = new ArrayList<String>(10);
		for (ServiceChargeType c : ServiceChargeType.values())
		{
			this.chargeTypes.add(c.toString());
		}

	}

	public List<String> getCurrencies()
	{
		return currencies;
	}

	public void setCurrencies(List<String> currencies)
	{
		this.currencies = currencies;
	}

	public List<String> getUnits()
	{
		return units;
	}

	public void setUnits(List<String> units)
	{
		this.units = units;
	}

	public List<String> getFrequencies()
	{
		return frequencies;
	}

	public void setFrequencies(List<String> frequencies)
	{
		this.frequencies = frequencies;
	}

	public List<String> getChargeTypes()
	{
		return chargeTypes;
	}

	public void setChargeTypes(List<String> chargeTypes)
	{
		this.chargeTypes = chargeTypes;
	}

}
