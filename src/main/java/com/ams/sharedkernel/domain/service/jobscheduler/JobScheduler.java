package com.ams.sharedkernel.domain.service.jobscheduler;

import com.ams.sharedkernel.domain.model.jobscheduler.Job;

/**
 * 
 * @author Raghavendra Badiger 
 * Description: Generic job scheduler interface used
 *         across different modules to schedule module specific jobs
 */

public interface JobScheduler
{

	<T extends Job> void scheduleJob(T job, String schedule);
}
