package com.ams.sharedkernel.domain.specification;

public interface Specification<T>
{
	boolean isSatisfiedBy(T domainObject);

}
