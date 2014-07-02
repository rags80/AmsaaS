package com.ams.booking.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-16T13:18:42.527+0530")
@StaticMetamodel(Booking.class)
public class Booking_ {
	public static volatile SingularAttribute<Booking, Long> bookingId;
	public static volatile SingularAttribute<Booking, ResourceId> bookedResourceId;
	public static volatile SingularAttribute<Booking, PersonId> bookingForPersonId;
}
