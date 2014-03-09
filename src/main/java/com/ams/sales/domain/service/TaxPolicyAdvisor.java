package com.ams.sales.domain.service;

import com.ams.sales.domain.model.bill.TaxPolicy;
import com.ams.sales.domain.model.servicecatalog.ServiceUsage;
import com.ams.usermanagement.domain.model.Person;

public interface TaxPolicyAdvisor
{
	TaxPolicy adviseTaxPolicy(ServiceUsage srvcUsage, Person customer);

}
