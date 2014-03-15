/**
 * 
 */
package com.ams.sharedkernel.domain.model.measuresandunits;

/**
 * @author Raghavendra Badiger
 * 
 */
public interface Unit
{
	Unit convertTo(Unit toUnit, Unit fromUnit);
}
