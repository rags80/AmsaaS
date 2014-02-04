package com.ams.billingandpayment.application.api.service.bill;

import com.ams.billingandpayment.application.api.servicedata.BillDto;
import com.ams.billingandpayment.domain.model.bill.Payment;

public interface ManageBill
{
	BillDto addBillItems(BillDto billSrvcData);

	BillDto createNewBill(BillDto billSrvcData);

	void deleteBill(long billNumber);

	void payBill(Payment paymnt);

}
