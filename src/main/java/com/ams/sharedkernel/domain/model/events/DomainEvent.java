/**
 *
 */
package com.ams.sharedkernel.domain.model.events;

import java.util.Date;

/**
 * 
 * @author Raghavendra Badiger
 */
public class DomainEvent
{
	protected long	eventId;

	protected Date	occuredOn;

	public Date getOccuredOn()
	{
		return this.occuredOn;
	}

	protected void setOccuredOn(Date occuredOn)
	{
		this.occuredOn = occuredOn;
	}
}
