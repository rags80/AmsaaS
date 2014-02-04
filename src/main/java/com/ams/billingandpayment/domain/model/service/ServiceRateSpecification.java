package com.ams.billingandpayment.domain.model.service;

import com.ams.sharedkernel.domain.model.Specification;

public class ServiceRateSpecification implements Specification<ServiceRate>
{

	public boolean isApplicableFor(Object... srvcRateParams)
	{
		return false;

	}

	public boolean isSatisfiedBy(ServiceRate domainObject)
	{
		return false;
	}

}
