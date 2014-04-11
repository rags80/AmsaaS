/**
 * 
 */
package com.ams.booking.application.impl;

/**
 * @author Raghavendra Badiger
 * 
 */
public enum BookingException
{

	NOT_POSSIBLE("Booking not possible @ this timeslot!"), CANCEL_FAILED("Booking can't be cancelled!");

	private String	exceptionDetail;

	BookingException(String exptnDetail)
	{
		this.exceptionDetail = exptnDetail;
	}

	public String getExceptionDetails()
	{
		return this.exceptionDetail;
	};

}
