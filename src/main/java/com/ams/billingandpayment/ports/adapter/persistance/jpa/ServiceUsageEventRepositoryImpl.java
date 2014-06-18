/**
 * 
 */
package com.ams.billingandpayment.ports.adapter.persistance.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ams.billingandpayment.domain.model.serviceportfolio.ServiceUsageEvent;
import com.ams.billingandpayment.domain.repository.ServiceUsageEventRepository;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;

/**
 * @author Raghavendra Badiger
 * 
 */

@Repository("ServiceUsageEventRepository")
public class ServiceUsageEventRepositoryImpl implements ServiceUsageEventRepository
{

	@Override
	public List<ServiceUsageEvent> findAllForCustomerWithinPeriod(long persnId, Period billPeriod)
	{
		return null;
	}

}
