package com.ams.booking.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-16T13:30:16.360+0530")
@StaticMetamodel(Booking.class)
public class Booking_ {
	public static volatile SingularAttribute<Booking, Long> bookingId;
	public static volatile SingularAttribute<Booking, ResourceId> bookedResourceId;
	public static volatile SingularAttribute<Booking, PersonId> bookingForPersonId;
}
