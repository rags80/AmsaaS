package com.ams.billingandpayment.application.api.service.serviceportfolio;

import com.ams.billingandpayment.application.api.dto.ServicePriceCommand;
import com.ams.billingandpayment.domain.model.services.ServicePlan;
import com.ams.billingandpayment.domain.model.services.ServicePrice;
import com.ams.sharedkernel.domain.repository.Page;

import java.util.List;

public interface ManageServicePlan {
    /*
	 * Service Plan command/write functions
	 */

    List<ServicePlan> getAllServicePlans();

    Page<ServicePrice> getNextPageOfServicePricesForPlan(String srvcPlanName, int startIndex, int offset);

    /*
     * Service Plan query/read functions
     */
    ServicePlan getServicePlan(String planName);

    Page<ServicePlan> getServicePlansNextPage(int index, int offset);

    List<ServicePrice> getServicePricesForPlan(String srvcPlanName);

    String registerServicePlan(ServicePlan svcPlan);

	/*
	 * Plan-Service Price command/write functions
	 */

    String registerServicePriceToPlan(ServicePriceCommand spc);

    void removeServicePlan(String srvcPlanName);

    void removeServicePrice(ServicePriceCommand spc) throws InvalidServicePricePlanException;

    void removeServicePrice(String srvcPlanName, String srvcCode) throws InvalidServicePricePlanException;

	/*
	 * Plan-Service Price query/read functions
	 */

    String updateServicePlanDetails(ServicePlan srvcPlan);

    void updateServicePriceDetails(ServicePriceCommand spc);

}
