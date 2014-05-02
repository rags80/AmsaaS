/**
 * 
 */
package com.ams.booking.domain;

import com.ams.sharedkernel.domain.ExceptionCode;

/**
 * @author Raghavendra Badiger
 * 
 */
public enum BookingExceptionCode implements ExceptionCode
{

	NOT_POSSIBLE("Booking not possible @ this timeslot!"), CANCEL_FAILED("Booking can't be cancelled!");

	private String	exceptionDetail;

	BookingExceptionCode(String exptnDetail)
	{
		this.exceptionDetail = exptnDetail;
	}

	@Override
	public String getExceptionDetails()
	{
		return this.exceptionDetail;
	};

}
