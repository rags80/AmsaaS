package com.ams.sales.domain.repository;

import java.util.List;

import com.ams.sales.domain.model.servicecatalog.ServiceUsage;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.usermanagement.domain.model.Person;

public interface ServiceUsageRepository
{

	List<ServiceUsage> findAllForCustomerWithinPeriod(Person billedPerson, Period billPeriod);

}
