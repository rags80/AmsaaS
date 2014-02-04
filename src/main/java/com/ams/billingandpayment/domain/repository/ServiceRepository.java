package com.ams.billingandpayment.domain.repository;

import java.util.Date;
import java.util.List;

import com.ams.billingandpayment.domain.model.service.Service;
import com.ams.billingandpayment.domain.model.service.ServiceCharge;
import com.ams.billingandpayment.domain.model.service.ServiceSubscription;
import com.ams.sharedkernel.domain.repository.Repository;

public interface ServiceRepository extends Repository<Service, String>
{

	public String createServiceCharge(ServiceCharge srvch);

	public Long createOrUpdateServiceSubscription(ServiceSubscription srvcsub);

	public List<ServiceSubscription> findServiceSubscription(final Date billGenerationDate);
}
