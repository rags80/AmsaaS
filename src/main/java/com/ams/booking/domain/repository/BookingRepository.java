/**
 *
 */
package com.ams.booking.domain.repository;

import com.ams.booking.domain.Booking;
import com.ams.booking.domain.ResourceId;

import java.util.Date;
import java.util.List;

/**
 * @author Raghavendra Badiger
 */
public interface BookingRepository {

    long createBooking(Booking booking);

    /**
     * @param bookingId
     * @return
     */
    long deleteBooking(long bookingId);

    /**
     * @param resourceId
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    List<Booking> findAllActiveBookingsForResource(ResourceId resourceId, Date startDateTime, Date endDateTime);

    /**
     * @param bookingId
     * @return
     */
    Booking findById(long bookingId);
}
