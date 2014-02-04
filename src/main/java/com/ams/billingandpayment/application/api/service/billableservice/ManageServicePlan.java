package com.ams.billingandpayment.application.api.service.billableservice;

import java.util.List;

import com.ams.billingandpayment.application.api.servicedata.ServiceRateDto;
import com.ams.billingandpayment.domain.model.service.InvalidServiceRatePlanException;
import com.ams.billingandpayment.domain.model.service.ServicePlan;
import com.ams.billingandpayment.domain.model.service.ServiceRate;
import com.ams.sharedkernel.domain.repository.Page;

public interface ManageServicePlan
{
	String registerServicePlan(ServicePlan svcPlan);

	String updateServicePlanDetails(ServicePlan srvcPlan);

	void removeServicePlan(String srvcPlanName);

	List<ServicePlan> getAllServicePlans();

	ServicePlan getServicePlanDetails(String srvcPlanName);

	String addServiceRateComponent(ServiceRateDto srd);

	void updateServiceRateComponentDetails(ServiceRateDto srd);

	void removeServiceRateComponent(String srvcPlanName, String srvcCode) throws InvalidServiceRatePlanException;

	void removeServiceRateComponent(ServiceRateDto srvcRateDTO) throws InvalidServiceRatePlanException;

	List<ServiceRate> getServiceRatesForPlanList(String srvcPlanName);

	Page<ServiceRate> getServiceRatesForPlanNextPage(String srvcPlanName, int startIndex, int offset);

}
