package com.ams.users.domain.repository;

import java.util.List;

import com.ams.sharedkernel.domain.repository.Repository;
import com.ams.users.domain.model.Person;

public interface PersonRepository extends Repository<Person, Long>
{

	public List<Person> findAllByCriteria(String... criterias);

	public List<Person> findAllByIds(long[] personIds);

}
