/**
 *
 */
package com.ams.sharedkernel.domain.events;

import java.util.Date;

/**
 * @author Raghavendra Badiger
 */
public class DomainEvent {

    protected Date occuredOn;

    public  Date getOccuredOn() {
        return this.occuredOn;
    }

    protected void setOccuredOn(Date occuredOn) {
        this.occuredOn = occuredOn;
    }
}
