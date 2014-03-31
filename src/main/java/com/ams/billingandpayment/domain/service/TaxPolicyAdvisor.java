package com.ams.billingandpayment.domain.service;

import com.ams.billingandpayment.domain.model.bill.TaxPolicy;
import com.ams.billingandpayment.domain.model.servicecatalog.ServiceUsage;
import com.ams.users.domain.model.Person;

public interface TaxPolicyAdvisor
{
	TaxPolicy adviseTaxPolicy(ServiceUsage srvcUsage, Person customer);

}
