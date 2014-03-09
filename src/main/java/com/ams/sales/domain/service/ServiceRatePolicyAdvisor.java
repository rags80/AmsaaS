package com.ams.sales.domain.service;

import java.util.List;

import com.ams.sales.domain.model.servicecatalog.ServicePlan;
import com.ams.sales.domain.model.servicecatalog.ServiceRatePolicy;
import com.ams.sales.domain.model.servicecatalog.ServiceUsage;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.usermanagement.domain.model.Person;

public interface ServiceRatePolicyAdvisor
{

	ServiceRatePolicy adviseSrvcRatePolicyForUsage(Person srvcSubscriber, Period billPeriod, ServiceUsage srvcUsage, ServicePlan subscbrSrvcPlan);

	List<ServiceRatePolicy> adviseRatePolicyForNonUsage(Person srvcSubscrbr, Period billPeriod, ServicePlan subscbrSrvcPlan);
}
