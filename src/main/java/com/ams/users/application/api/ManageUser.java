package com.ams.users.application.api;

import java.util.List;

import com.ams.sharedkernel.exception.ServiceException;
import com.ams.users.domain.model.Person;

public interface ManageUser
{
	public void registerUser(Person user) throws ServiceException;

	public void updateUserDetails(Person user) throws ServiceException;

	public void deleteUser(long userId) throws ServiceException;

	public Person getUserDetails(long userId) throws ServiceException;

	public List<Person> getAllUsers() throws ServiceException;

}
