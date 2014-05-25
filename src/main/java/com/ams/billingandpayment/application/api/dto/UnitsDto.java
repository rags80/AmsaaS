package com.ams.billingandpayment.application.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.ams.sharedkernel.domain.model.measuresandunits.Currency;
import com.ams.sharedkernel.domain.model.measuresandunits.Frequency;
import com.ams.sharedkernel.domain.model.measuresandunits.TimeUnit;

public class UnitsDto
{
	private List<String>	currencies;
	private List<String>	units;
	private List<String>	frequencies;

	public UnitsDto()
	{
		this.currencies = new ArrayList<String>(10);
		for (Currency c : Currency.values())
		{
			this.currencies.add(c.toString());
		}

		this.units = new ArrayList<String>(10);
		for (TimeUnit c : TimeUnit.values())
		{
			this.units.add(c.toString());
		}

		this.frequencies = new ArrayList<String>(10);
		for (Frequency c : Frequency.values())
		{
			this.frequencies.add(c.toString());
		}

	}

	public List<String> getCurrencies()
	{
		return this.currencies;
	}

	public void setCurrencies(List<String> currencies)
	{
		this.currencies = currencies;
	}

	public List<String> getFrequencies()
	{
		return this.frequencies;
	}

	public void setFrequencies(List<String> frequencies)
	{
		this.frequencies = frequencies;
	}

	public List<String> getUnits()
	{
		return this.units;
	}

	public void setUnits(List<String> units)
	{
		this.units = units;
	}
}
