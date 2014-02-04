package com.ams.billingandpayment.domain.model.service;

import java.util.Date;

import com.ams.sharedkernel.domain.model.Specification;

public class SubscriptionSpecification implements Specification<ServiceSubscription>
{

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
