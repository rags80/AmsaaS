package com.ams.billingandpayment.application.impl.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ams.billingandpayment.application.api.service.bill.ManageBill;
import com.ams.billingandpayment.application.api.service.bill.ManageBillRunJob;
import com.ams.billingandpayment.domain.model.schedule.BillSchedule;
import com.ams.users.domain.repository.PersonRepository;

/**
 * @author Raghavendra Badiger
 */
@Transactional
@org.springframework.stereotype.Service("ManageBillRunSchedule")
public class ManageBillRunJobImpl implements ManageBillRunJob
{
	@Autowired
	private PersonRepository	persnRepo;

	@Autowired
	private ManageBill		manageBill;

	@Override
	public boolean scheduleNewBillRunJob(BillSchedule schedule)
	{

		return false;
	}

}
