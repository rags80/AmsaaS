package com.ams.users.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-04T20:09:07.974+0530")
@StaticMetamodel(Person.class)
public class Person_ {
	public static volatile SingularAttribute<Person, Long> persnId;
	public static volatile SingularAttribute<Person, PersonDetail> persnDetail;
	public static volatile SingularAttribute<Person, Address> persnAddress;
	public static volatile SingularAttribute<Person, Object> persnServiceProfile;
	public static volatile SingularAttribute<Person, LoginDetails> persnLoginDetails;
}
