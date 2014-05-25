/**
 * 
 */
package com.ams.billingandpayment.domain.service;

import com.ams.billingandpayment.domain.model.bill.policy.DiscountPolicy;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePlan;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.users.domain.model.Person;

/**
 * @author Raghavendra Badiger
 * 
 */
public interface DiscountPolicyAdvisor
{

	DiscountPolicy adviseServiceDiscountPolicy(Person person, Period billPeriod, ServicePlan srvcPlan, ServicePrice nonUsageServicePrice);

	DiscountPolicy adviseBillDiscountPolicy(Person srvcSubscriber, Period billPeriod);

}
