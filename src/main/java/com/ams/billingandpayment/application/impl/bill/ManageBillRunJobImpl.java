package com.ams.billingandpayment.application.impl.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ams.billingandpayment.application.api.service.bill.ManageBill;
import com.ams.billingandpayment.application.api.service.bill.ManageBillRunJob;
import com.ams.billingandpayment.domain.model.schedule.BillRunJob;
import com.ams.sharedkernel.domain.service.jobscheduler.JobScheduler;
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

	@Autowired
	private JobScheduler	jobScheduler;

	@Override
	public boolean scheduleNewBillRunJob(String schedExprsn)
	{

		BillRunJob brJob = new BillRunJob(this.persnRepo, this.manageBill, schedExprsn);
		this.jobScheduler.scheduleJob(brJob, schedExprsn);
		return false;
	}

}
