package com.ams.sales.domain.repository;

import java.util.List;

import com.ams.sales.domain.model.servicecatalog.ServicePlan;
import com.ams.sales.domain.model.servicecatalog.ServiceRate;
import com.ams.sales.domain.model.servicecatalog.ServiceRateId;
import com.ams.sharedkernel.domain.repository.Page;
import com.ams.sharedkernel.domain.repository.Repository;

public interface ServicePlanRepository extends Repository<ServicePlan, String>
{

	String saveOrUpdateServiceRate(ServiceRate srvcRate);

	List<ServiceRate> findAllServiceRateByPlanName(String srvcPlanName);

	List<ServiceRate> findAllServiceRateByCriteria(String srvcPlanName, String srvcCode);

	Page<ServiceRate> findServiceRateNextPageData(String srvcPlanName, int startIndex, int offset);

	void deleteServiceRate(ServiceRateId srvcRateId);

	void deleteServiceRateByCriteria(String srvcPlanName, String srvcCode);

	void deleteServiceRateByCriteria(String srvcPlanName, String srvcCode, String chargeType);

}
