package com.ams.sharedkernel.ports.adapter.messaging;

import java.util.Map;

import com.ams.sharedkernel.domain.events.DomainEvent;
import com.ams.sharedkernel.domain.events.DomainEventListener;
import com.ams.sharedkernel.domain.events.DomainEventPublisher;

/**
 * @author Raghavendra Badiger
 */
public class DomainEventPublisherImpl implements DomainEventPublisher
{
	private Map<Class<? extends DomainEvent>, Class<DomainEventListener>>	eventListenerMap;

	public DomainEventPublisherImpl()
	{}

	public DomainEventPublisherImpl(	Map<Class<? extends DomainEvent>, Class<DomainEventListener>> eventListenerMap)
	{
		this.eventListenerMap = eventListenerMap;
	}

	@Override
	public void deRegisterEventListener(DomainEventListener del)
	{

	}

	@Override
	public <DE extends DomainEvent> void raiseEvent(DE domainEvent)
	{
		try
		{
			this.eventListenerMap.get(domainEvent.getClass()).newInstance().handleEvent(domainEvent);

		} catch (InstantiationException e)
		{
			e.printStackTrace();

		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		};
	}

	@Override
	public void registerEventListener(DomainEventListener del)
	{

	}
}
