package com.ams.billingandpayment.domain.model.bill;

import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.sharedkernel.domain.model.measuresandunits.Quantity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-04T20:09:07.934+0530")
@StaticMetamodel(BillItem.class)
public class BillItem_ {
	public static volatile SingularAttribute<BillItem, Object> servicePrice;
	public static volatile SingularAttribute<BillItem, Money> grossAmount;
	public static volatile SingularAttribute<BillItem, Tax> itemTax;
	public static volatile SingularAttribute<BillItem, Discount> itemDiscount;
	public static volatile SingularAttribute<BillItem, Money> netAmount;
	public static volatile SingularAttribute<BillItem, Quantity> quantity;
}
