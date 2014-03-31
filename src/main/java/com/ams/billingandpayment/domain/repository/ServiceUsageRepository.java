package com.ams.billingandpayment.domain.repository;

import java.util.List;

import com.ams.billingandpayment.domain.model.servicecatalog.ServiceUsage;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.users.domain.model.Person;

public interface ServiceUsageRepository
{

	List<ServiceUsage> findAllForCustomerWithinPeriod(Person billedPerson, Period billPeriod);

}
