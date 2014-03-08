package com.ams.sales.application.api.service.serviceportfolio;

import java.util.List;

import com.ams.sales.application.api.servicedata.ServiceRateDto;
import com.ams.sales.domain.model.servicecatalog.InvalidServiceRatePlanException;
import com.ams.sales.domain.model.servicecatalog.ServicePlan;
import com.ams.sales.domain.model.servicecatalog.ServiceRate;
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
