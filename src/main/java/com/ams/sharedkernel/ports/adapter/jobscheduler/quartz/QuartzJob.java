/**
 * 
 */
package com.ams.sharedkernel.ports.adapter.jobscheduler.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ams.sharedkernel.domain.model.jobscheduler.Job;

/**
 * @author Raghavendra Badiger
 * 
 */
public class QuartzJob implements org.quartz.Job
{
	private Job	job;

	public QuartzJob(Job jobToExec)
	{
		this.job = jobToExec;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		this.job.execute();
	}

}
