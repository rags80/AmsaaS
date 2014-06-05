package com.ams.sharedkernel.domain.model.specification;

public interface Specification<T>
{

	boolean isSatisfiedBy(T domainObject);

}
