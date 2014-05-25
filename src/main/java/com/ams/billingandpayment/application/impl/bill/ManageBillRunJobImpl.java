package com.ams.billingandpayment.application.impl.bill;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ams.billingandpayment.application.api.service.bill.ManageBillRunJob;
import com.ams.billingandpayment.domain.model.schedule.BillSchedule;
import com.ams.users.domain.model.Person;

/**
 * @author Raghavendra Badiger
 */
@Transactional
@org.springframework.stereotype.Service("ManageBillRunSchedule")
public class ManageBillRunJobImpl implements ManageBillRunJob
{

	@Override
	public boolean scheduleNewBillRunJob(List<Person> billableUserList, BillSchedule schedule)
	{
		return false;
	}

	@Override
	public boolean scheduleNewBillRunJob(BillSchedule schedule)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
