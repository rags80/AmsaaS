/**
 *
 */
package com.ams.booking.application.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.booking.application.api.ManageBooking;
import com.ams.booking.domain.model.Booking;
import com.ams.booking.domain.model.BookingExceptionCode;
import com.ams.booking.domain.model.PersonId;
import com.ams.booking.domain.model.ResourceId;
import com.ams.booking.domain.repository.BookingRepository;
import com.ams.sharedkernel.domain.service.exception.ServiceException;

/**
 * @author Raghavendra Badiger
 */
@Service("ManageBooking")
public class ManageBookingImpl implements ManageBooking
{

	@Autowired
	private BookingRepository	bookingRepository;

	@Override
	public long newBooking(PersonId persnId, ResourceId resourceId, String details, Date startDateTime, Date endDateTime)
	{

		int noOfOverlappingBookings = this.bookingRepository.findOverlappingBookingsForResource(resourceId, startDateTime, endDateTime).size();
		System.out.println("# of Overlapping bookings:" + noOfOverlappingBookings);

		if (noOfOverlappingBookings > 0)
		{
			throw new ServiceException(BookingExceptionCode.NOT_POSSIBLE.getExceptionDetails());
		}
		else
		{
			return this.bookingRepository.createBooking(new Booking(persnId, resourceId, details, startDateTime, endDateTime));
		}

	}

	@Override
	public long updateBooking(long bookingId, String details, Date startDateTime, Date endDateTime)
	{
		Booking booking = this.bookingRepository.findById(bookingId);
		int noOfOverlappingBookings = this.bookingRepository.findOverlappingBookingsForResource(booking.getBookedResourceId(), startDateTime, endDateTime).size();
		System.out.println("# of Overlapping bookings:" + noOfOverlappingBookings);
		if (noOfOverlappingBookings > 0)
		{
			throw new ServiceException(BookingExceptionCode.NOT_POSSIBLE.getExceptionDetails());
		}
		else
		{
			booking.updateDetails(details, startDateTime, endDateTime);
			return this.bookingRepository.updateBooking(booking);
		}

	}

	@Override
	public long cancelBooking(long bookingId)
	{
		Booking booking = this.bookingRepository.findById(bookingId);
		booking.cancel();
		return this.bookingRepository.updateBooking(booking);

	}

	@Override
	public long bookingOver(long bookingId)
	{
		Booking booking = this.bookingRepository.findById(bookingId);
		booking.over();
		return this.bookingRepository.updateBooking(booking);

	}

	@Override
	public long removeBooking(long bookingId)
	{
		return this.bookingRepository.deleteBooking(bookingId);

	}

	@Override
	public List<Booking> getAllBookingsForPeriod(ResourceId id, Date startDate, Date endDate)
	{
		List<Booking> bookingForPeriod = this.bookingRepository.findAllBookingsForPeriod(id.getResourceId(), startDate, endDate);
		System.out.println("# of bookings in given period:" + bookingForPeriod.size());
		return bookingForPeriod;

	}

	@Override
	public Booking getBookingById(long bookingId)
	{
		return this.bookingRepository.findById(bookingId);
	}

}
