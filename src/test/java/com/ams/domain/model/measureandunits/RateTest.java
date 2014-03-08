package com.ams.domain.model.measureandunits;

import java.math.BigDecimal;

import com.ams.sharedkernel.domain.model.measuresandunits.Currency;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.sharedkernel.domain.model.measuresandunits.Rate;
import com.ams.sharedkernel.domain.model.measuresandunits.Unit;

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
