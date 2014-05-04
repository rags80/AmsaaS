package com.ams.billingandpayment.domain.service;

import com.ams.billingandpayment.domain.model.bill.policy.TaxPolicy;
import com.ams.billingandpayment.domain.model.servicecatalog.Service;
import com.ams.billingandpayment.domain.model.servicecatalog.ServiceUsageEvent;
import com.ams.users.domain.model.Person;

public interface TaxPolicyAdvisor {
    TaxPolicy adviseTaxPolicy(Service service, Person billedPerson);

    TaxPolicy adviseTaxPolicy(ServiceUsageEvent srvcUsage, Person customer);

}
