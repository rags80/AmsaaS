package com.ams.sharedkernel.domain.model.measuresandunits;

import java.io.Serializable;

/**
 * @author Raghavendra Badiger
 */
public enum TimeUnit implements Serializable, Unit {
    Hrs, Days, Weeks, FortNights, Months, QuarterYears, HalfYears, Years;

    @Override
    public Unit convertTo(Unit toUnit, Unit fromUnit) {
        return null;
    }

}
