package com.ams.users.ports.adapter.persistance;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ams.sharedkernel.domain.repository.Page;
import com.ams.users.domain.model.Person;
import com.ams.users.domain.repository.PersonRepository;

@Repository("PersonRepository")
public class PersonRepositoryImpl implements PersonRepository
{
	@Autowired
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager	entityManager;

	@Override
	public Long create(Person person)
	{
		this.entityManager.persist(person);
		return person.getPersnId();
	}

	@Override
	public Long delete(Long personId)
	{
		Person p = this.entityManager.find(Person.class, personId);
		this.entityManager.remove(p);
		return p.getPersnId();
	}

	@Override
	public List<Person> findAll()
	{
		TypedQuery<Person> query = this.entityManager.createQuery("select P from Person P", Person.class);
		return query.getResultList();
	}

	@Override
	public List<Person> findAllByCriteria(String... criterias)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findAllByIds(long[] userIds)
	{
		return null;
	}

	@Override
	public Person findById(Long personId)
	{
		return this.entityManager.find(Person.class, personId);
	}

	@Override
	public Page<Person> findNextPageData(Page<Person> page)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(Person person)
	{
		this.entityManager.merge(person);
		this.entityManager.flush();
		return person.getPersnId();
	}

}
