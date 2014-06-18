/**
 *
 */
package com.ams.booking.application.api;

import java.util.Date;
import java.util.List;

import com.ams.booking.domain.model.Booking;
import com.ams.booking.domain.model.PersonId;
import com.ams.booking.domain.model.ResourceId;

/**
 * @author Raghavendra Badiger
 */
public interface ManageBooking
{
	long newBooking(PersonId persnId, ResourceId resourceId, String details, Date startDateTime, Date endDateTime);

	long updateBooking(long bookingId, String details, Date startDateTime, Date endDateTimeDate);

	long cancelBooking(long bookingId);

	long bookingOver(long bookingId);

	long removeBooking(long bookingId);

	Booking getBookingById(long bookingId);

	List<Booking> getAllBookingsForPeriod(ResourceId id, Date startDate, Date endDate);

}
