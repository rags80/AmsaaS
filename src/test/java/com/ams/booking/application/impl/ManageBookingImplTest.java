/**
 * 
 */
package com.ams.booking.application.impl;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ams.booking.application.api.ManageBooking;
import com.ams.booking.domain.model.Booking;
import com.ams.booking.domain.model.PersonId;
import com.ams.booking.domain.model.ResourceId;

/**
 * @author Raghavendra Badiger
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:context-application.xml", "classpath:context-infrastructure-persistance.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true,transactionManager = "txManager_Jpa")
public class ManageBookingImplTest
{

	@Autowired
	private ManageBooking	mngBooking;
	private Booking		booking;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{

	}

	/**
	 * Test method for
	 * {@link com.ams.booking.application.impl.ManageBookingImpl#newBooking(com.ams.booking.domain.model.PersonId, com.ams.booking.domain.model.ResourceId, java.lang.String, java.util.Date, java.util.Date)}
	 * .
	 * 
	 * @throws ParseException
	 */
	@Test
	public final void testNewBooking() throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date S = sdf.parse("17-06-2014 13:00:00");
		Date E = sdf.parse("17-06-2014 13:00:00");
		assertEquals(0, this.mngBooking.getAllBookingsForPeriod(new ResourceId(1L), S, E).size());
		this.mngBooking.newBooking(new PersonId(2L), new ResourceId(1L), "New Birthday Booking", S, E);
		assertEquals(1, this.mngBooking.getAllBookingsForPeriod(new ResourceId(1L), S, E).size());
	}

	/**
	 * Test method for
	 * {@link com.ams.booking.application.impl.ManageBookingImpl#updateBooking(long, java.lang.String, java.util.Date, java.util.Date, java.lang.String)}
	 * .
	 * 
	 * @throws ParseException
	 */
	@Test
	public final void testUpdateBooking() throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date S = sdf.parse("17-06-2014 13:00:00");
		Date E = sdf.parse("18-06-2014 13:00:00");

		assertEquals(0, this.mngBooking.getAllBookingsForPeriod(new ResourceId(1L), S, E).size());
		long bid = this.mngBooking.newBooking(new PersonId(2L), new ResourceId(1L), "New Birthday Booking", S, E);
		assertEquals(1, this.mngBooking.getAllBookingsForPeriod(new ResourceId(1L), S, E).size());

		System.out.println("BEFORE UPDATE:\n");
		String details = this.mngBooking.getBookingById(bid).getBookingDetails();
		assertEquals("New Birthday Booking", details);
		System.out.println("Booking Details:" + details);

		String status = this.mngBooking.getBookingById(bid).getBookingStatus().toString();
		assertEquals("ACTIVE", status);
		System.out.println("Booking Status:" + status);

		this.mngBooking.updateBooking(bid, "Anniversary!!", null, null);

		System.out.println("AFTER UPDATE:\n");

		details = this.mngBooking.getBookingById(bid).getBookingDetails();
		assertEquals("Anniversary!!", details);
		System.out.println("Booking Details:" + details);

	}

	/**
	 * Test method for
	 * {@link com.ams.booking.application.impl.ManageBookingImpl#cancelBooking(long)}
	 * .
	 * 
	 * @throws ParseException
	 */
	@Test
	public final void testCancelBooking() throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date S = sdf.parse("17-07-2014 13:00:00");
		Date E = sdf.parse("18-07-2014 13:00:00");
		long bid = this.mngBooking.newBooking(new PersonId(2L), new ResourceId(1L), "New Birthday Booking", S, E);

		System.out.println("BEFORE  CANCELLATION:\n");
		String status = this.mngBooking.getBookingById(bid).getBookingStatus().toString();
		assertEquals("ACTIVE", status);
		System.out.println("Booking Status:" + status);

		this.mngBooking.cancelBooking(bid);

		System.out.println("AFTER  CANCELLATION:\n");
		status = this.mngBooking.getBookingById(bid).getBookingStatus().toString();
		assertEquals("CANCELLED", status);
		System.out.println("Booking Status:" + status);
	}

	/**
	 * Test method for
	 * {@link com.ams.booking.application.impl.ManageBookingImpl#findAllBookingsForPeriod(com.ams.booking.domain.model.ResourceId, java.util.Date, java.util.Date)}
	 * .
	 * 
	 * @throws ParseException
	 */
	@Test
	public final void testFindAllBookingsForPeriod() throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date S = sdf.parse("17-06-2014 13:00:00");
		Date E = sdf.parse("18-07-2014 13:00:00");
		ResourceId rid = new ResourceId(1L);
		PersonId pid = new PersonId(1L);
		int size = this.mngBooking.getAllBookingsForPeriod(rid, S, E).size();
		System.out.println("BEFORE: \n # of bookings:" + size);
		assertEquals(0, size);

		E = sdf.parse("18-06-2014 13:00:00");
		this.mngBooking.newBooking(pid, rid, "Birthday Booking", S, E);
		S = sdf.parse("18-06-2014 15:00:00");
		E = sdf.parse("18-06-2014 18:00:00");
		this.mngBooking.newBooking(pid, rid, "Marriage Booking", S, E);

		S = sdf.parse("17-06-2014 13:00:00");
		E = sdf.parse("18-07-2014 13:00:00");
		size = this.mngBooking.getAllBookingsForPeriod(rid, S, E).size();
		System.out.println("AFTER: \n # of bookings:" + size);
		assertEquals(2, size);

	}
}
