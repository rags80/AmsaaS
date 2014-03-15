package com.ams.billing.domain.repository;

import java.util.List;

import com.ams.billing.domain.model.servicecatalog.ServicePlan;
import com.ams.billing.domain.model.servicecatalog.ServicePrice;
import com.ams.sharedkernel.domain.repository.Page;
import com.ams.sharedkernel.domain.repository.Repository;

public interface ServicePlanRepository extends Repository<ServicePlan, String>
{

	String saveOrUpdateServicePriceToPlan(ServicePrice srvcPrice);

	void deleteServicePriceOfPlan(String srvcPlanName, String srvcCode);

	List<ServicePrice> findAllServicePriceByPlanName(String srvcPlanName);

	List<ServicePrice> findAllServicePricesByCriteria(String srvcPlanName, String srvcCode);

	Page<ServicePrice> findNextPageOfServicePrices(String srvcPlanName, int startIndex, int offset);

}
