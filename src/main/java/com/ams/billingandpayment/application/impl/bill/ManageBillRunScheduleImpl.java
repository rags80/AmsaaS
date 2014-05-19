package com.ams.billingandpayment.application.impl.bill;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ams.billingandpayment.application.api.service.bill.ManageBillRunSchedule;
import com.ams.billingandpayment.domain.model.schedule.BillSchedule;
import com.ams.users.domain.model.Person;

/**
 * @author Raghavendra Badiger
 */
@Transactional
@org.springframework.stereotype.Service("ManageBillRunSchedule")
public class ManageBillRunScheduleImpl implements ManageBillRunSchedule
{

	@Override
	public boolean scheduleNewBillRunJob(List<Person> billableUserList, BillSchedule schedule)
	{
		return false;
	}

}
