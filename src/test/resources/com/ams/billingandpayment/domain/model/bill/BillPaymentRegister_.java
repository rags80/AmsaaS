package com.ams.billingandpayment.domain.model.bill;

import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-04T20:09:07.937+0530")
@StaticMetamodel(BillPaymentRegister.class)
public class BillPaymentRegister_ {
	public static volatile SingularAttribute<BillPaymentRegister, Long> personId;
	public static volatile SingularAttribute<BillPaymentRegister, Money> billCurrentBalance;
	public static volatile SingularAttribute<BillPaymentRegister, Money> billAmountPaid;
}
