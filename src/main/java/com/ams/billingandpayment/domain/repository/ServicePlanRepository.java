package com.ams.billingandpayment.domain.repository;

import java.util.List;

import com.ams.billingandpayment.domain.model.service.ServicePlan;
import com.ams.billingandpayment.domain.model.service.ServiceRate;
import com.ams.billingandpayment.domain.model.service.ServiceRateId;
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
