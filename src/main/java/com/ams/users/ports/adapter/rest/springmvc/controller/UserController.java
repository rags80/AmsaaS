package com.ams.users.ports.adapter.rest.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ams.sharedkernel.domain.service.exception.ServiceException;
import com.ams.users.application.api.ManageUser;
import com.ams.users.domain.model.Person;

@Controller
public class UserController
{
	@Autowired
	private ManageUser	manageUser;

	@RequestMapping(value = "user/{userId}",method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteUser(@PathVariable final Long userId) throws ServiceException
	{
		this.manageUser.deleteUser(userId);
		return "SUCCESS";

	}

	@RequestMapping("users/{userId}")
	@ResponseBody
	public Person getUserDetail(@PathVariable Long userId) throws ServiceException
	{
		return this.manageUser.getUserDetails(userId);

	}

	@RequestMapping("users")
	@ResponseBody
	public List<Person> getUsersList() throws ServiceException
	{

		return this.manageUser.getAllUsers();
	}

	@RequestMapping(value = "user",method = RequestMethod.POST)
	@ResponseBody
	public String saveUser(@RequestBody final Person user) throws ServiceException
	{
		try
		{
			System.out.println(user.getPersnFirstName());
			this.manageUser.registerUser(user);
			return "SUCCESS";
		} catch (Exception e)
		{
			throw new ServiceException(e.toString());
		}

	}

	@RequestMapping(value = "user",method = RequestMethod.PUT)
	@ResponseBody
	public String updateUseretail(@RequestBody final Person user) throws ServiceException
	{
		this.manageUser.updateUserDetails(user);
		return "SUCCESS";

	}

}
