package com.ams.billingandpayment.domain.model.bill;

import java.math.BigDecimal;
import java.util.Currency;

import com.ams.sharedkernel.domain.model.measuresandunits.Money;

/**
 * @author Raghavendra Badiger
 * 
 */
public class BillSpecification
{

	public static final String	sourceEmailId	= "ams@xyz.com";

	public static boolean isPaymentWithinDueDate(Bill bill, Payment payment)
	{
		return payment.getPaymntDate().before(bill.getBillDueDate());
	}

	public static Money getPenaltyAmount()
	{
		return new Money(BigDecimal.valueOf(50), Currency.getInstance("INR"));
	}

}
