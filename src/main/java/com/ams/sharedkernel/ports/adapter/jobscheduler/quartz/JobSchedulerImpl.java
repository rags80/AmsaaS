/**
 * 
 */
package com.ams.sharedkernel.ports.adapter.jobscheduler.quartz;

import java.text.ParseException;
import java.util.Properties;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Component;

import com.ams.sharedkernel.domain.model.jobscheduler.Job;
import com.ams.sharedkernel.domain.service.jobscheduler.JobScheduler;

/**
 * @author Raghavendra Badiger
 * 
 *         Description: This Quartz Scheduler adapter provides job scheduling
 *         capability using Quartz scheduler internally.
 * 
 */
@Component("JobScheduler")
public class JobSchedulerImpl implements JobScheduler
{

	@Override
	public <T extends Job> void scheduleJob(T job, String schedule)
	{
		CronTrigger trigger = new CronTriggerImpl();
		QuartzJob qj = new QuartzJob(job);
		try
		{
			((CronTriggerImpl) trigger).setCronExpression(schedule);

			Properties prop = new Properties();
			prop.setProperty("org.quartz.threadPool.threadCount", "1");

			StdSchedulerFactory sf = new StdSchedulerFactory();
			sf.initialize(prop);

			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			JobDetailImpl jd = new JobDetailImpl();
			scheduler.scheduleJob(jd, trigger);
		} catch (ParseException e)
		{
			e.printStackTrace();
		} catch (SchedulerException e)
		{

			e.printStackTrace();
		}

	}

}
