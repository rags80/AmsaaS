/**
 *
 */
package com.ams.booking.application.api;

import com.ams.booking.domain.PersonId;
import com.ams.booking.domain.ResourceId;

import java.util.Date;

/**
 * @author Raghavendra Badiger
 */
public interface ManageBooking {

    long cancelBooking(long bookingId);

    /**
     * @param persnId
     * @param resourceId
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    long newBooking(PersonId persnId, ResourceId resourceId, Date startDateTime, Date endDateTime);

}
