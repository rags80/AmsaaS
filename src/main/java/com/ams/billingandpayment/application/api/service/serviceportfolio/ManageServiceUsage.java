package com.ams.billingandpayment.application.api.service.serviceportfolio;

import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.sharedkernel.exception.ServiceException;

public interface ManageServiceUsage
{

	void registerServiceUsage(long userId, String srvcCode, Period duration) throws ServiceException;

}
