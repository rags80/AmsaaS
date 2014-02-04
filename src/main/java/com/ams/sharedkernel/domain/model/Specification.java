package com.ams.sharedkernel.domain.model;

public interface Specification<T>
{
	boolean isSatisfiedBy(T domainObject);

}
