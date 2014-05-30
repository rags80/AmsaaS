/**
 *
 */
package com.ams.sharedkernel.domain.service.events;

import com.ams.sharedkernel.domain.model.events.DomainEvent;

/**
 * @author Raghavendra Badiger
 */
public interface DomainEventPublisher {

    void deRegisterEventListener(DomainEventListener del);

    <DE extends DomainEvent> void raiseEvent(DE domainEvent);

    void registerEventListener(DomainEventListener del);

}
