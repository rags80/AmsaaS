package com.ams.billing.application.api.service.bill;

import java.util.Collection;

import com.ams.billing.domain.model.bill.Bill;

public interface ManageBillBatch
{
	public Collection<Bill> fetchBills();

	public void fetchSubs();
}
