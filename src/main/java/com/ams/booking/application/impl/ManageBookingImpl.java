/**
 *
 */
package com.ams.booking.application.impl;

import com.ams.booking.application.api.ManageBooking;
import com.ams.booking.domain.Booking;
import com.ams.booking.domain.BookingExceptionCode;
import com.ams.booking.domain.PersonId;
import com.ams.booking.domain.ResourceId;
import com.ams.booking.domain.repository.BookingRepository;
import com.ams.sharedkernel.application.api.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author Raghavendra Badiger
 */
public class ManageBookingImpl implements ManageBooking
{

	@Autowired
	private BookingRepository	bookingRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ams.booking.application.api.ManageBooking#cancelBooking(long)
	 */
	@Override
	public long cancelBooking(long bookingId)
	{
		Booking booking = this.bookingRepo.findById(bookingId);

		if (booking.canBeCancelled())
		{
			return this.bookingRepo.deleteBooking(bookingId);
		}
		else
		{
			throw new ServiceException(BookingExceptionCode.CANCEL_FAILED.getExceptionDetails());
		}

	}

	@Override
	public long newBooking(PersonId persnId, ResourceId resourceId, Date startDateTime, Date endDateTime)
	{

		List<Booking> bookings = this.bookingRepo.findAllActiveBookingsForResource(resourceId, startDateTime, endDateTime);
		if (bookings.size() > 0)
		{
			throw new ServiceException(BookingExceptionCode.NOT_POSSIBLE.getExceptionDetails());
		}
		else
		{
			return this.bookingRepo.createBooking(new Booking(persnId, resourceId, startDateTime, endDateTime));
		}

	}

}
