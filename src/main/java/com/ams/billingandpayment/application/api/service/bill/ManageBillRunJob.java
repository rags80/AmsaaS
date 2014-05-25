package com.ams.billingandpayment.application.api.service.bill;

import java.util.List;

import com.ams.billingandpayment.domain.model.schedule.BillSchedule;
import com.ams.users.domain.model.Person;

public interface ManageBillRunJob
{

	boolean scheduleNewBillRunJob(BillSchedule schedule);

	boolean scheduleNewBillRunJob(List<Person> billableUserList, BillSchedule schedule);

}
