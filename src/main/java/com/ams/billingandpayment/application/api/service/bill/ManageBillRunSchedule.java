package com.ams.billingandpayment.application.api.service.bill;

import java.util.List;

import com.ams.billingandpayment.domain.model.schedule.Schedule;
import com.ams.users.domain.model.Person;

public interface ManageBillRunSchedule
{

	boolean newBillRunSchedule(List<Person> billableUserList, Schedule schedule);
}
