package com.ams.billingandpayment.domain.service;

import com.ams.billingandpayment.domain.model.bill.policy.TaxPolicy;
import com.ams.billingandpayment.domain.model.services.Service;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.users.domain.model.Person;

public interface TaxPolicyAdvisor
{
	TaxPolicy adviseServiceTaxPolicy(Service service);

	TaxPolicy adviseBillTaxPolicy(Person billedPerson, Period billPeriod);

}
