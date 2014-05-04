package com.ams.billingandpayment.application.api.service.serviceportfolio;

import com.ams.sharedkernel.application.api.exception.ServiceException;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;

public interface ManageServiceUsage {

    void registerServiceUsage(long userId, String srvcCode, Period duration) throws ServiceException;

}
