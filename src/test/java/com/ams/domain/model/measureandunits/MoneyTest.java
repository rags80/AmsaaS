package com.ams.domain.model.measureandunits;

import java.math.BigDecimal;

import com.ams.sharedkernel.domain.model.DomainException;
import com.ams.sharedkernel.domain.model.measureandunits.Currency;
import com.ams.sharedkernel.domain.model.measureandunits.Money;

public class MoneyTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Money money = new Money(new BigDecimal(1000), Currency.EUR);
		System.out.println(money);
		Money m1 = new Money(new BigDecimal(5000), Currency.INR);
		try
		{
			System.out.println(m1.divideBy(money));

		} catch (DomainException e)
		{
			e.printStackTrace();
		}
	}

}
