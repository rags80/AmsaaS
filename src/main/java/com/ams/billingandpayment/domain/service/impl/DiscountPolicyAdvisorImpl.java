/**
 * 
 */
package com.ams.billingandpayment.domain.service.impl;

import org.springframework.stereotype.Service;

import com.ams.billingandpayment.domain.model.bill.policy.DiscountPolicy;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePlan;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice;
import com.ams.billingandpayment.domain.service.DiscountPolicyAdvisor;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.users.domain.model.Person;

/**
 * @author Raghavendra Badiger
 * 
 */
@Service("DiscountPolicyAdvisor")
public class DiscountPolicyAdvisorImpl implements DiscountPolicyAdvisor
{

	@Override
	public DiscountPolicy adviseServiceDiscountPolicy(Person person, Period billPeriod, ServicePlan srvcPlan, ServicePrice nonUsageServicePrice)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiscountPolicy adviseBillDiscountPolicy(Person srvcSubscriber, Period billPeriod)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
