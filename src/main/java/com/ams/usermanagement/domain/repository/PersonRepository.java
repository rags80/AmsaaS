package com.ams.usermanagement.domain.repository;

import java.util.List;

import com.ams.sharedkernel.domain.repository.Repository;
import com.ams.usermanagement.domain.model.Person;

public interface PersonRepository extends Repository<Person, Long>
{

	public List<Person> findAllByIds(long[] personIds);

	public List<Person> findAllByCriteria(String... criterias);

}
