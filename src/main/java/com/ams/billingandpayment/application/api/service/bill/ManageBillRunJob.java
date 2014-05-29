package com.ams.billingandpayment.application.api.service.bill;

import com.ams.billingandpayment.domain.model.schedule.BillSchedule;

public interface ManageBillRunJob
{

	boolean scheduleNewBillRunJob(BillSchedule schedule);

}
