/**
 * 
 */
package com.ams.booking.ports.adapter.persistance.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ams.booking.domain.model.Booking;
import com.ams.booking.domain.model.Booking.BookingStatus;
import com.ams.booking.domain.model.ResourceId;
import com.ams.booking.domain.repository.BookingRepository;

/**
 * 
 * @author Raghavendra Badiger
 */
@Repository("BookingRepository")
public class BookingRepositoryImpl implements BookingRepository
{

	@Autowired
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private EntityManager	entityMgr;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ams.booking.domain.repository.BookingRepository#createBooking(com
	 * .ams.booking.domain.Booking)
	 */
	@Override
	public long createBooking(Booking booking)
	{
		this.entityMgr.persist(booking);
		return booking.getBookingId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ams.booking.domain.repository.BookingRepository#updateBooking(com
	 * .ams.booking.domain.Booking)
	 */
	@Override
	public long updateBooking(Booking booking)
	{
		this.entityMgr.merge(booking);
		return booking.getBookingId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ams.booking.domain.repository.BookingRepository#deleteBooking(long)
	 */
	@Override
	public long deleteBooking(long bookingId)
	{
		Query deleteQuery = this.entityMgr.createNamedQuery("DELETE b FROM booking AS b WHERE b.bookingId=:bookingId");
		deleteQuery.setParameter("bookingId", bookingId);
		deleteQuery.getFirstResult();
		return bookingId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ams.booking.domain.repository.BookingRepository#findById(long)
	 */
	@Override
	public Booking findById(long bookingId)
	{
		return this.entityMgr.find(Booking.class, bookingId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ams.booking.domain.repository.BookingRepository#
	 * findOverlappingBookingsForResource(com.ams.booking.domain.ResourceId,
	 * java.util.Date, java.util.Date)
	 */
	@Override
	public List<Booking> findOverlappingBookingsForResource(ResourceId resourceId, Date startDateTime, Date endDateTime)
	{
		TypedQuery<Booking> overlapBookingsQuery = this.entityMgr.createQuery("SELECT b FROM Booking b WHERE b.bookedResourceId.resourceId=:id AND b.bookingStatus=:status AND((b.bookingStartDateTime BETWEEN :startDateTime AND :endDateTime) OR (:startDateTime BETWEEN b.bookingStartDateTime AND b.bookingEndDateTime)OR (:endDateTime BETWEEN b.bookingStartDateTime AND b.bookingEndDateTime))", Booking.class);
		overlapBookingsQuery.setParameter("id", resourceId.getResourceId());
		overlapBookingsQuery.setParameter("status", BookingStatus.ACTIVE);
		overlapBookingsQuery.setParameter("startDateTime", startDateTime);
		overlapBookingsQuery.setParameter("endDateTime", endDateTime);
		return overlapBookingsQuery.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ams.booking.domain.repository.BookingRepository#findAllBookingsForPeriod
	 * (long, java.util.Date, java.util.Date)
	 */
	@Override
	public List<Booking> findAllBookingsForPeriod(long resourceId, Date startDate, Date endDate)
	{
		TypedQuery<Booking> bookingsQuery = this.entityMgr.createQuery("SELECT b FROM Booking b WHERE b.bookedResourceId.resourceId=:id AND b.bookingStatus=:status AND (b.bookingStartDateTime BETWEEN :startDateTime AND :endDateTime)", Booking.class);
		bookingsQuery.setParameter("id", resourceId);
		bookingsQuery.setParameter("status", BookingStatus.ACTIVE);
		bookingsQuery.setParameter("startDateTime", startDate);
		bookingsQuery.setParameter("endDateTime", endDate);
		return bookingsQuery.getResultList();
	}

}
