package com.ams.billingandpayment.domain.model.serviceportfolio;

import java.util.Date;

import com.ams.sharedkernel.domain.model.specification.Specification;

/**
 * @author Raghavendra Badiger
 * 
 */
public class SubscriptionSpecification implements Specification<ServiceSubscription>
{

	@Override
	public boolean isSatisfiedBy(ServiceSubscription srvcSubscription)
	{
		if (srvcSubscription.getSrvcSubcrptnEndDate().equals(new Date()) || srvcSubscription.getSrvcSubcrptnEndDate().after(new Date()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
