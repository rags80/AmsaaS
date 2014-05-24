package com.ams.billingandpayment.domain.repository;

import java.util.List;

import com.ams.billingandpayment.domain.model.serviceportfolio.ServiceUsageEvent;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;

public interface ServiceUsageEventRepository
{

	List<ServiceUsageEvent> findAllForCustomerWithinPeriod(long persnId, Period billPeriod);

}
