package com.ams.sharedkernel.domain.model.measuresandunits;

import java.io.Serializable;

public enum TimeUnit implements Serializable, Unit
{
	Hrs, Days, Weeks, FortNights, Months, QuarterYears, HalfYears, Years;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ams.sharedkernel.domain.model.measuresandunits.Unit#convertTo(com
	 * .ams.sharedkernel.domain.model.measuresandunits.Unit,
	 * com.ams.sharedkernel.domain.model.measuresandunits.Unit)
	 */
	public Unit convertTo(Unit toUnit, Unit fromUnit)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
