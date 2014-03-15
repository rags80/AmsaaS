package com.ams.billing.application.api.service.serviceportfolio;

import com.ams.sharedkernel.application.common.ServiceException;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;

public interface ManageServiceUsage
{

	void registerServiceUsage(long userId, String srvcCode, Period duration) throws ServiceException;

	void calculateRecurringServiceCharges(long userId, Period billPeriod) throws ServiceException;

}
