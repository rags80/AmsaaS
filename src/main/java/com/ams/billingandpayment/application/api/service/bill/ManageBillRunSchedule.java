package com.ams.billingandpayment.application.api.service.bill;

import com.ams.billingandpayment.domain.model.schedule.Schedule;
import com.ams.users.domain.model.Person;

import java.util.List;

public interface ManageBillRunSchedule {

    boolean newBillRunSchedule(List<Person> billableUserList, Schedule schedule);
}
