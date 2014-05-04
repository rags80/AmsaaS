/**
 *
 */
package com.ams.booking.domain;

/**
 * @author Raghavendra Badiger
 */
public class PersonId {
    private long personId;

    public PersonId() {
    }

    public PersonId(long id) {
        this.personId = id;
    }

    public long getPersonId() {
        return this.personId;
    }

    private void setPersonId(long personId) {
        this.personId = personId;
    }

}
