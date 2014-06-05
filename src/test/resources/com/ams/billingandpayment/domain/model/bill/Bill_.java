package com.ams.billingandpayment.domain.model.bill;

import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-04T20:09:07.931+0530")
@StaticMetamodel(Bill.class)
public class Bill_ {
	public static volatile SingularAttribute<Bill, Long> billNumber;
	public static volatile SingularAttribute<Bill, Object> billedPerson;
	public static volatile SingularAttribute<Bill, Period> billPeriod;
	public static volatile SingularAttribute<Bill, Money> billPreviousBalance;
	public static volatile SingularAttribute<Bill, Money> billPenaltyAmount;
	public static volatile SingularAttribute<Bill, Money> billGrossAmount;
	public static volatile SingularAttribute<Bill, Tax> billTotalTax;
	public static volatile SingularAttribute<Bill, Discount> billTotalDiscount;
	public static volatile SingularAttribute<Bill, Money> billNetAmount;
	public static volatile SingularAttribute<Bill, Object> billPaymentRegister;
}
