/**
 *
 */
package com.ams.booking.domain.model;

import com.ams.sharedkernel.domain.model.exception.ExceptionCode;

/**
 * @author Raghavendra Badiger
 */
public enum BookingExceptionCode implements ExceptionCode
{

	CANCEL_FAILED("Booking can't be cancelled!"),
	ENDDATETIME_CONFLICT("Booking Start Date-time conflicts with End Date-time"),
	ISNT_OVER("The Booking isn't over yet!!"),
	NO_BOOKINGS("No Bookings exist!"),
	NOT_POSSIBLE("Booking is not possible @ this timeslot!");

	private String	exceptionDetail;

	BookingExceptionCode(String exptnDetail)
	{
		this.exceptionDetail = exptnDetail;
	}

	@Override
	public String getExceptionDetails()
	{
		return this.exceptionDetail;
	}

}
