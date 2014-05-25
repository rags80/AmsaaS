package com.ams.users.domain.model.events;

import com.ams.sharedkernel.domain.events.DomainEvent;
import com.ams.users.domain.model.Person;

import java.util.Date;

/**
 * @author Raghavendra Badiger
 */
public class UserCreatedEvent extends DomainEvent {

    private Person createdUser;

    /**
     * @param user
     */

    public UserCreatedEvent(Person user) {
        this.setCreatedUser(user);
        this.setOccuredOn(new Date());

    }

    public Person getCreatedUser() {
        return this.createdUser;
    }

    private void setCreatedUser(Person createdUser) {
        this.createdUser = createdUser;
    }

}
