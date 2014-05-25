package com.ams.sharedkernel.domain.events;

/**
 * @author Raghavendra Badiger
 */
public interface DomainEventListener {

    <E extends DomainEvent> void handleEvent(E event);
}
