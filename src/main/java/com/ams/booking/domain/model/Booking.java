/**
 *
 */
package com.ams.booking.domain.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ams.sharedkernel.domain.model.exception.DomainException;

/**
 * 
 * @author Raghavendra Badiger
 */

@Entity
@Access(AccessType.FIELD)
@Table(name = "T_BOOKING")
public class Booking
{
	public enum BookingStatus
	{
		ACTIVE, CANCELLED, OVER
	};

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long			bookingId;
	private String			bookingDetails;
	@Embedded
	private ResourceId		bookedResourceId;
	@Embedded
	private PersonId		bookingForPersonId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date			bookingStartDateTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date			bookingEndDateTime;
	@Enumerated(EnumType.STRING)
	private BookingStatus	bookingStatus;

	Booking()
	{}

	public Booking(PersonId persnId, ResourceId resourceId, String details, Date startDateTime,
				Date endDateTime)
	{
		if (endDateTime.after(startDateTime))
		{
			this.bookingForPersonId = persnId;
			this.bookedResourceId = resourceId;
			this.bookingDetails = details;
			this.bookingStartDateTime = startDateTime;
			this.bookingEndDateTime = endDateTime;
			this.bookingStatus = BookingStatus.ACTIVE;
		}
		else
		{
			throw new DomainException(BookingExceptionCode.ENDDATETIME_CONFLICT.getExceptionDetails());
		}
	}

	public void cancel()
	{
		if (this.canBeCancelled())
		{
			this.bookingStatus = BookingStatus.CANCELLED;
		}
	}

	public void over()
	{
		Date today = new Date();
		if (today.after(this.bookingEndDateTime))
		{
			this.bookingStatus = BookingStatus.OVER;
		}
		else
		{
			throw new DomainException(BookingExceptionCode.ISNT_OVER.getExceptionDetails());
		}
	}

	public void updateDetails(String details, Date startDateTime, Date endDateTime)
	{
		if (details != null)
		{
			this.bookingDetails = details;
		}
		if (startDateTime != null)
		{
			this.bookingStartDateTime = startDateTime;
		}
		if (endDateTime != null)
		{
			this.bookingEndDateTime = endDateTime;
		}

	}

	private boolean canBeCancelled()
	{
		Calendar startDatePrevDay = Calendar.getInstance();
		startDatePrevDay.setTime(this.bookingStartDateTime);
		startDatePrevDay.add(startDatePrevDay.DATE, -1);

		if ((this.bookingStatus == (BookingStatus.ACTIVE)) && startDatePrevDay.getTime().after(new Date()))
		{
			return true;
		}
		else
		{
			throw new DomainException(BookingExceptionCode.CANCEL_FAILED.getExceptionDetails());
		}

	}

	/**
	 * ACCESSORS
	 */

	public long getBookingId()
	{
		return this.bookingId;
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

	public ResourceId getBookedResourceId()
	{
		return this.bookedResourceId;
	}

	public Date getBookingStartDateTime()
	{
		return this.bookingStartDateTime;
	}

	public BookingStatus getBookingStatus()
	{
		return this.bookingStatus;
	}

}
