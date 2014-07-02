package com.ams.billingandpayment.domain.model.schedule;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ams.billingandpayment.application.api.service.bill.ManageBill;
import com.ams.users.domain.model.Person;
import com.ams.users.domain.repository.PersonRepository;

/**
 * 
 * @author Raghavendra Badiger
 */

public class BillRunJob extends com.ams.sharedkernel.domain.model.jobscheduler.Job
{
	private PersonRepository	persnRepo;
	private ManageBill		manageBill;
	private String			billSchedule;

	public BillRunJob(PersonRepository persnRepo, ManageBill mngBill, String schedule)
	{
		this.persnRepo = persnRepo;
		this.manageBill = mngBill;
		this.billSchedule = schedule;
	}

	@Override
	public void execute()
	{
		List<Person> persnList = this.persnRepo.findAll();
		ExecutorService billJobExecutor = Executors.newFixedThreadPool(5);
		for (Person subscrbr : persnList)
		{
			billJobExecutor.execute(new BillRunJobWorker(subscrbr));
		}
		billJobExecutor.shutdown();

	}

	class BillRunJobWorker implements Runnable
	{
		private Person	subscrbr;

		BillRunJobWorker(Person p)
		{
			this.subscrbr = p;
		}

		@Override
		public void run()
		{
			// BillRunJob.this.manageBill.billSubscriberForPeriod(this.subscrbr,
			// BillRunJob.this.billSchedule.getBillPeriod(),
			// BillRunJob.this.billSchedule.getBillDate(),
			// BillRunJob.this.billSchedule.getBillDueDate());

		}

	}

}
