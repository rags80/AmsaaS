/**
 * 
 */
package com.ams.users.ports.adapter.messaging;

import org.springframework.stereotype.Service;

import com.ams.sharedkernel.domain.model.events.DomainEvent;
import com.ams.sharedkernel.domain.service.events.DomainEventListener;
import com.ams.sharedkernel.domain.service.events.DomainEventPublisher;

/**
 * @author Raghavendra Badiger
 * 
 */
@Service("UserEventPublisher")
public class UserEventPublisher implements DomainEventPublisher
{

	@Override
	public void deRegisterEventListener(DomainEventListener del)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ams.sharedkernel.domain.service.events.DomainEventPublisher#raiseEvent
	 * (com.ams.sharedkernel.domain.model.events.DomainEvent)
	 */
	@Override
	public <DE extends DomainEvent> void raiseEvent(DE domainEvent)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ams.sharedkernel.domain.service.events.DomainEventPublisher#
	 * registerEventListener
	 * (com.ams.sharedkernel.domain.service.events.DomainEventListener)
	 */
	@Override
	public void registerEventListener(DomainEventListener del)
	{
		// TODO Auto-generated method stub

	}

}
