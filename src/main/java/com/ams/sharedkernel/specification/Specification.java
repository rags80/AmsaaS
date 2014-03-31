package com.ams.sharedkernel.specification;

public interface Specification<T>
{
	boolean isSatisfiedBy(T domainObject);

}
