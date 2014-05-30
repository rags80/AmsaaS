/**
 * 
 */
package com.ams.billingandpayment.domain.service.impl;

import org.springframework.stereotype.Service;

import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePriceSpecification;
import com.ams.billingandpayment.domain.service.ServicePriceSpecAdvisor;

/**
 * @author Raghavendra Badiger
 * 
 * 
 */
@Service("ServicePriceSpecAdvisor")
public class ServicePriceSpecAdvisorImpl implements ServicePriceSpecAdvisor
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ams.billingandpayment.domain.service.ServicePriceSpecAdvisor#adviseSpec
	 * (com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice)
	 */
	@Override
	public ServicePriceSpecification adviseSpec(ServicePrice servicePrice)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
