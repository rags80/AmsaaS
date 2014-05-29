/**
 *
 */
package com.ams.booking.application.api;

import java.util.Date;

import com.ams.booking.domain.PersonId;
import com.ams.booking.domain.ResourceId;

/**
 * @author Raghavendra Badiger
 */
public interface ManageBooking
{

	long cancelBooking(long bookingId);

	long newBooking(PersonId persnId, ResourceId resourceId, Date startDateTime, Date endDateTime);

}
