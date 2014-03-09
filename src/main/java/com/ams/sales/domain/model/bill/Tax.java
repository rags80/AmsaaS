package com.ams.sales.domain.model.bill;

import com.ams.sharedkernel.domain.model.measuresandunits.Money;

public class Tax
{

	private Money	taxAmount;
	private String	taxDescription;

	public Tax(Money amount, String description)
	{
		this.taxAmount = amount;
		this.taxDescription = description;
	}

	public Money getTaxAmount()
	{
		return taxAmount;
	}

	@SuppressWarnings("unused")
	private void setTaxAmount(Money taxAmount)
	{
		this.taxAmount = taxAmount;
	}

	public String getTaxDescription()
	{
		return taxDescription;
	}

	@SuppressWarnings("unused")
	private void setTaxDescription(String taxDescription)
	{
		this.taxDescription = taxDescription;
	}

}
