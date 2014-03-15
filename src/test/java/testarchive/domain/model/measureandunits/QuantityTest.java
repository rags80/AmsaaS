package testarchive.domain.model.measureandunits;

import java.math.BigDecimal;

import com.ams.sharedkernel.domain.model.measuresandunits.Quantity;
import com.ams.sharedkernel.domain.model.measuresandunits.TimeUnit;

public class QuantityTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Quantity qty = new Quantity(new BigDecimal(100), TimeUnit.valueOf("Hrs"));
		System.out.println(qty);

	}

}
