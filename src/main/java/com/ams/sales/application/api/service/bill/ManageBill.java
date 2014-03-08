package com.ams.sales.application.api.service.bill;

import java.util.Date;

import com.ams.sales.application.api.servicedata.BillDto;
import com.ams.sales.domain.model.bill.Payment;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.usermanagement.domain.model.Person;

public interface ManageBill
{
	BillDto createNewBill(BillDto billSrvcData);

	BillDto addBillItems(BillDto billSrvcData);

	void billSubscriberForPeriod(Person srvcSubscriber, Period billPeriod, Date billDate, Date billDueDate);

	void deleteBill(long billNumber);

	void payBill(Payment paymnt);

}
