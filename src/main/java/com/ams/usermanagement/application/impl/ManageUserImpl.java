package com.ams.usermanagement.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ams.usermanagement.application.api.ManageUser;
import com.ams.usermanagement.domain.model.Person;
import com.ams.usermanagement.domain.repository.PersonRepository;

@Transactional
@Service("ManageUserService")
public class ManageUserImpl implements ManageUser
{

	@Autowired
	private PersonRepository	personRepository;

	@Transactional(propagation = Propagation.REQUIRED)
	public void registerUser(Person user)
	{
		personRepository.createOrUpdate(user);

	}

	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
	public void updateUserDetails(Person user)
	{
		personRepository.createOrUpdate(user);

	}

	public void deleteUser(long userId)
	{
		personRepository.delete(userId);

	}

	public Person getUserDetails(long userId)
	{
		Person user = personRepository.findById(userId);
		return user;
	}

	public List<Person> getAllUsers()
	{
		List<Person> users = personRepository.findAll();
		return users;

	}

}
