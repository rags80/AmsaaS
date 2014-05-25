/**
 * 
 */
package com.ams.billingandpayment.domain.model.serviceportfolio;

import com.ams.sharedkernel.domain.model.measuresandunits.Period;

/**
 * @author Raghavendra Badiger
 * 
 */
public interface ServicePriceSpecification
{
	public boolean isApplicableFor(Period billPeriod);
}
