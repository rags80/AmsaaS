package com.ams.billingandpayment.domain.repository;

import java.util.List;

import com.ams.billingandpayment.domain.model.servicecatalog.ServicePlan;
import com.ams.billingandpayment.domain.model.servicecatalog.ServicePrice;
import com.ams.sharedkernel.domain.repository.Page;
import com.ams.sharedkernel.domain.repository.Repository;

public interface ServicePlanRepository extends Repository<ServicePlan, String>
{

	String saveOrUpdateServicePriceToPlan(ServicePrice srvcPrice);

	void deleteServicePriceOfPlan(String srvcPlanName, String srvcCode);

	List<ServicePrice> findAllServicePriceByPlanName(String srvcPlanName);

	ServicePrice findServicePriceByCriteria(String srvcPlanName, String srvcCode);

	Page<ServicePrice> findNextPageOfServicePrices(String srvcPlanName, int startIndex, int offset);

}
