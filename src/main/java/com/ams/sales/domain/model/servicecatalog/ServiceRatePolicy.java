package com.ams.sales.domain.model.servicecatalog;

import java.util.Date;

import com.ams.sharedkernel.domain.model.measuresandunits.Money;

public interface ServiceRatePolicy
{
	ServiceRate serviceRate();

	Money calculateServiceCharge(ServicePlan srvcPlan, ServiceUsage srvcUsage, Date chargedDate);

}
