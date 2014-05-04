package com.ams.users.application.api;

import com.ams.sharedkernel.application.api.exception.ServiceException;
import com.ams.users.domain.model.Person;

import java.util.List;

public interface ManageUser {
    public void deleteUser(long userId) throws ServiceException;

    public List<Person> getAllUsers() throws ServiceException;

    public Person getUserDetails(long userId) throws ServiceException;

    public void registerUser(Person user) throws ServiceException;

    public void updateUserDetails(Person user) throws ServiceException;

}
