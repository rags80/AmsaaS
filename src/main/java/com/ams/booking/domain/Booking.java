/**
 * 
 */
package com.ams.booking.domain;

import java.util.Date;

import org.joda.time.DateTime;

import com.ams.sharedkernel.domain.DomainException;

/**
 * @author Raghavendra Badiger
 * 
 */

public class Booking
{
	private long		bookingId;
	private String		bookingDetails;
	private ResourceId	bookedResourceId;
	private PersonId	bookingForPersonId;
	private Date		bookingStartDateTime;
	private Date		bookingEndDateTime;

	/**
	 * @param persnId
	 * @param resourceId
	 * @param startDateTime
	 * @param endDateTime
	 */
	public Booking(PersonId persnId, ResourceId resourceId, Date startDateTime, Date endDateTime)
	{
		this.bookingForPersonId = persnId;
		this.setBookedResourceId(resourceId);
		this.bookingStartDateTime = startDateTime;
		this.bookingEndDateTime = endDateTime;
	}

	/**
	 * @return
	 */
	public boolean canBeCancelled()
	{

		if (new DateTime(this.bookingStartDateTime).minusDays(1).isAfterNow())
		{
			return true;
		}
		else
		{
			throw new DomainException(BookingExceptionCode.CANCEL_FAILED.getExceptionDetails());
		}

	}

	public ResourceId getBookedResourceId()
	{
		return this.bookedResourceId;
	}

	public String getBookingDetails()
	{
		return this.bookingDetails;
	}

	public Date getBookingEndDateTime()
	{
		return this.bookingEndDateTime;
	}

	public PersonId getBookingForPersonId()
	{
		return this.bookingForPersonId;
	}

	/**
	 * 
	 * ACCESSOR AND MUTATORS
	 */

	public long getBookingId()
	{
		return this.bookingId;
	}

	public Date getBookingStartDateTime()
	{
		return this.bookingStartDateTime;
	}

	public void setBookedResourceId(ResourceId bookedResourceId)
	{
		this.bookedResourceId = bookedResourceId;
	}

	public void setBookingDetails(String bookingDetails)
	{
		this.bookingDetails = bookingDetails;
	}

	public void setBookingEndDateTime(Date bookingEndDateTime)
	{
		this.bookingEndDateTime = bookingEndDateTime;
	}

	public void setBookingForPersonId(PersonId bookingForPersonId)
	{
		this.bookingForPersonId = bookingForPersonId;
	}

	public void setBookingId(long bookingId)
	{
		this.bookingId = bookingId;
	}

	public void setBookingStartDateTime(Date bookingStartDateTime)
	{
		this.bookingStartDateTime = bookingStartDateTime;
	}

}
