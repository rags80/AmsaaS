package com.ams.billingandpayment.domain.model.servicecatalog;

import com.ams.sharedkernel.domain.specification.Specification;

import java.util.Date;

public class SubscriptionSpecification implements Specification<ServiceSubscription> {

    @Override
    public boolean isSatisfiedBy(ServiceSubscription srvcSubscription) {
        if (srvcSubscription.getSrvcSubcrptnEndDate().equals(new Date()) || srvcSubscription.getSrvcSubcrptnEndDate().after(new Date())) {
            return true;
        } else {
            return false;
        }
    }

}
