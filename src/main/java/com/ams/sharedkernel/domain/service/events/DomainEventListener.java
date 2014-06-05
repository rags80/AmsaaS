package com.ams.sharedkernel.domain.service.events;

import com.ams.sharedkernel.domain.model.events.DomainEvent;

/**
 * @author Raghavendra Badiger
 */
public interface DomainEventListener
{

	<E extends DomainEvent> void handleEvent(E event);
}
