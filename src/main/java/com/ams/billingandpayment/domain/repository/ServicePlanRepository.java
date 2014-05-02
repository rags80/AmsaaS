package com.ams.billingandpayment.domain.repository;

import java.util.List;
import java.util.Map;

import com.ams.billingandpayment.domain.model.servicecatalog.ServicePlan;
import com.ams.billingandpayment.domain.model.servicecatalog.ServicePrice;
import com.ams.sharedkernel.domain.repository.Page;
import com.ams.sharedkernel.domain.repository.Repository;

public interface ServicePlanRepository extends Repository<ServicePlan, String>
{

	void deleteServicePriceOfPlan(String srvcPlanName, String srvcCode);

	List<ServicePrice> findAllServicePriceByPlanName(String srvcPlanName);

	List<ServicePrice> findAllServicePricesByCriteria(Map criteriaMap);

	List<ServicePrice> findAllServicePricesByCriteria(String srvcPlanName, String string);

	Page<ServicePrice> findNextPageOfServicePrices(String srvcPlanName, int startIndex, int offset);

	ServicePrice findServicePriceByCriteria(String srvcPlanName, String srvcCode);

	String saveOrUpdateServicePriceToPlan(ServicePrice srvcPrice);

}
