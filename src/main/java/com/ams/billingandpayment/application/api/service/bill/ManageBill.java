package com.ams.billingandpayment.application.api.service.bill;

import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.users.domain.model.Person;

import java.util.Date;

/**
 * @author Raghavendra Badiger
 */
public interface ManageBill {

    /**
     * Periodic Billing operations
     */
    void billSubscriberForPeriod(Person srvcSubscriber, Period billPeriod, Date billDate, Date billDueDate);

    /**
     * Ad-hoc Billing operations
     */
    /*
	 * BillDto createNewBill(BillDto billSrvcData);
	 * 
	 * BillDto addBillItems(BillDto billSrvcData);
	 * 
	 * void deleteBill(long billNumber);
	 * 
	 * List<BillDto> getAllBills();
	 * 
	 * /** Payement for Bill operations
	 */
    // void payBill(Payment paymnt);

}
