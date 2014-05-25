/**
 *
 */
package com.ams.sharedkernel.domain.events;

/**
 * @author Raghavendra Badiger
 */
public interface DomainEventPublisher {

    void deRegisterEventListener(DomainEventListener del);

    <DE extends DomainEvent> void raiseEvent(DE domainEvent);

    void registerEventListener(DomainEventListener del);

}
