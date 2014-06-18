/**
 *
 */
package com.ams.booking.domain.repository;

import java.util.Date;
import java.util.List;

import com.ams.booking.domain.model.Booking;
import com.ams.booking.domain.model.ResourceId;

/**
 * @author Raghavendra Badiger
 */
public interface BookingRepository
{
	/**
	 * @param booking
	 * @return
	 */
	long createBooking(Booking booking);

	/**
	 * @param booking
	 * @return
	 */
	long updateBooking(Booking booking);

	/**
	 * @param bookingId
	 * @return
	 */
	long deleteBooking(long bookingId);

	/**
	 * @param bookingId
	 * @return
	 */
	Booking findById(long bookingId);

	/**
	 * @param resourceId
	 * @param startDateTime
	 * @param endDateTime
	 * @return
	 */
	List<Booking> findOverlappingBookingsForResource(ResourceId resourceId, Date startDateTime, Date endDateTime);

	/**
	 * @param resourceId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Booking> findAllBookingsForPeriod(long resourceId, Date startDate, Date endDate);

}
