package com.ams.billingandpayment.domain.model.bill.policy;

import com.ams.billingandpayment.domain.model.bill.Discount;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;

/**
 * 
 * @author Raghavendra Badiger
 */
public interface DiscountPolicy
{

	Discount calculateDiscount(Money amount);

}
