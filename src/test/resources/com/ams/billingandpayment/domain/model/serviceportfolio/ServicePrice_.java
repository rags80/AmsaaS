package com.ams.billingandpayment.domain.model.serviceportfolio;

import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-04T20:09:07.949+0530")
@StaticMetamodel(ServicePrice.class)
public class ServicePrice_ {
	public static volatile SingularAttribute<ServicePrice, Object> srvcPlan;
	public static volatile SingularAttribute<ServicePrice, Object> service;
	public static volatile SingularAttribute<ServicePrice, Money> srvcPricePerUnit;
}
