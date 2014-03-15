package testarchive.domain.model.measureandunits;

import java.math.BigDecimal;

import com.ams.sharedkernel.domain.model.measuresandunits.Currency;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.sharedkernel.domain.model.measuresandunits.Rate;
import com.ams.sharedkernel.domain.model.measuresandunits.TimeUnit;

public class RateTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		Rate rate = new Rate(new Money(new BigDecimal(1000), Currency.GBP), TimeUnit.Months);
		System.out.println(rate);
	}
}
