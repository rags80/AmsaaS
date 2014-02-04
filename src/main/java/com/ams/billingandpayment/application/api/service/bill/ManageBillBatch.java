package com.ams.billingandpayment.application.api.service.bill;

import java.util.Collection;

import com.ams.billingandpayment.domain.model.bill.Bill;

public interface ManageBillBatch
{
	public Collection<Bill> fetchBills();

	public void fetchSubs();
}
