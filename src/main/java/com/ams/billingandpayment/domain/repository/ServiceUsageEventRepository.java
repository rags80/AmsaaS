package com.ams.billingandpayment.domain.repository;

import java.util.List;

import com.ams.billingandpayment.domain.model.servicecatalog.ServiceUsageEvent;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.users.domain.model.Person;

public interface ServiceUsageEventRepository
{

	List<ServiceUsageEvent> findAllForCustomerWithinPeriod(Person billedPerson, Period billPeriod);

}
