package com.ams.sales.domain.repository;

import java.util.Date;
import java.util.List;

import com.ams.sales.domain.model.servicecatalog.Service;
import com.ams.sales.domain.model.servicecatalog.ServiceCharge;
import com.ams.sales.domain.model.servicecatalog.ServiceSubscription;
import com.ams.sharedkernel.domain.repository.Repository;

public interface ServiceRepository extends Repository<Service, String>
{

	public String createServiceCharge(ServiceCharge srvch);

	public Long createOrUpdateServiceSubscription(ServiceSubscription srvcsub);

	public List<ServiceSubscription> findServiceSubscription(final Date billGenerationDate);
}
