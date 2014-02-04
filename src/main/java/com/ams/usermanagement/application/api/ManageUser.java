package com.ams.usermanagement.application.api;

import java.util.List;

import com.ams.sharedkernel.application.common.ServiceException;
import com.ams.usermanagement.domain.model.Person;

public interface ManageUser
{
	public void registerUser(Person user) throws ServiceException;

	public void updateUserDetails(Person user) throws ServiceException;

	public void deleteUser(long userId) throws ServiceException;

	public Person getUserDetails(long userId) throws ServiceException;

	public List<Person> getAllUsers() throws ServiceException;

}
