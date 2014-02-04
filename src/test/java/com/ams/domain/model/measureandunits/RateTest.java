package com.ams.domain.model.measureandunits;

import java.math.BigDecimal;

import com.ams.sharedkernel.domain.model.measureandunits.Currency;
import com.ams.sharedkernel.domain.model.measureandunits.Money;
import com.ams.sharedkernel.domain.model.measureandunits.Rate;
import com.ams.sharedkernel.domain.model.measureandunits.Unit;

public class RateTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		Rate rate = new Rate(new Money(new BigDecimal(1000), Currency.GBP), Unit.Months);
		System.out.println(rate);
	}
}
