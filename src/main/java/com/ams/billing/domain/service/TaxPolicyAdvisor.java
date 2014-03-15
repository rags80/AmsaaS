package com.ams.billing.domain.service;

import com.ams.billing.domain.model.bill.TaxPolicy;
import com.ams.billing.domain.model.servicecatalog.ServiceUsage;
import com.ams.usermanagement.domain.model.Person;

public interface TaxPolicyAdvisor
{
	TaxPolicy adviseTaxPolicy(ServiceUsage srvcUsage, Person customer);

}
