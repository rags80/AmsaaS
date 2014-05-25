/**
 * 
 */
package com.ams.billingandpayment.domain.service;

import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePriceSpecification;

/**
 * @author Raghavendra Badiger
 * 
 */
public interface ServicePriceSpecAdvisor
{

	ServicePriceSpecification adviseSpec(ServicePrice servicePrice);
}
