package com.ams.billingandpayment.application.api.service.billableservice;

import com.ams.sharedkernel.application.common.ServiceException;
import com.ams.sharedkernel.domain.model.measureandunits.Period;

public interface ManageServiceUsage
{

	void registerServiceUsage(long userId, String srvcCode, Period duration) throws ServiceException;

	void calculateRecurringServiceCharges(long userId, Period billPeriod) throws ServiceException;

}
