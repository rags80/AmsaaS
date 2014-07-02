package com.ams.users.application.api;

import java.util.List;

import com.ams.users.domain.model.Person;

public interface ManageUser
{
	public void deleteUser(long userId);

	public List<Person> getAllUsers();

	public Person getUserDetails(long userId);

	public void registerUser(Person user);

	public void updateUserDetails(Person user);

}
