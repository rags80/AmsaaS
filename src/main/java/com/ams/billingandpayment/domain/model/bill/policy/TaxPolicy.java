package com.ams.billingandpayment.domain.model.bill.policy;

import com.ams.billingandpayment.domain.model.bill.Tax;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;

public interface TaxPolicy
{
	Tax calculateTax(Money amount);

}
