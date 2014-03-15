package com.ams.billing.application.api.service.serviceportfolio;

import java.util.List;

import com.ams.billing.application.api.commandquery.ServicePriceCommand;
import com.ams.billing.domain.model.servicecatalog.ServicePlan;
import com.ams.billing.domain.model.servicecatalog.ServicePrice;
import com.ams.sharedkernel.domain.repository.Page;

public interface ManageServicePlan
{
	/*
	 * Service Plan command/write functions
	 */

	String registerServicePlan(ServicePlan svcPlan);

	String updateServicePlanDetails(ServicePlan srvcPlan);

	void removeServicePlan(String srvcPlanName);

	/*
	 * Service Plan query/read functions
	 */
	List<ServicePlan> getAllServicePlans();

	ServicePlan getServicePlanDetails(String srvcPlanName);

	/*
	 * Plan-Service Price command/write functions
	 */

	String registerServicePriceToPlan(ServicePriceCommand spc);

	void updateServicePriceDetails(ServicePriceCommand spc);

	void removeServicePrice(String srvcPlanName, String srvcCode) throws InvalidServicePricePlanException;

	void removeServicePrice(ServicePriceCommand spc) throws InvalidServicePricePlanException;

	/*
	 * Plan-Service Price query/read functions
	 */

	List<ServicePrice> getServicePricesForPlan(String srvcPlanName);

	Page<ServicePrice> getNextPageOfServicePricesForPlan(String srvcPlanName, int startIndex, int offset);

}
