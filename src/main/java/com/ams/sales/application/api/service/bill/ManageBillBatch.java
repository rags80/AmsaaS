package com.ams.sales.application.api.service.bill;

import java.util.Collection;

import com.ams.sales.domain.model.bill.Bill;

public interface ManageBillBatch
{
	public Collection<Bill> fetchBills();

	public void fetchSubs();
}
