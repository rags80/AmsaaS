package com.ams.billingandpayment.ports.adapter.web.struts2.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.ams.sharedkernel.domain.service.exception.ServiceException;
import com.ams.users.application.api.ManageUser;
import com.ams.users.domain.model.Person;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/")
public class UserManagerAction extends ActionSupport
{

	private static final long	serialVersionUID	= 9086072862754088699L;

	@Autowired
	private ManageUser			manageUserService;
	private Person				user;
	private ArrayList<Person>	users;

	@JSON(serialize = true)
	public Person getUser()
	{
		return this.user;
	}

	@Action(value = "/getUserDetailAction",results = { @Result(name = "success",type = "json",
													params = { "includeProperties",
															"user" }) })
	public String getUserDetail()
	{
		long id = this.user.getPersnId();
		try
		{
			this.user = (this.manageUserService.getUserDetails(id));
			System.out.println("From Service Person Name is:"
					+ this.user.getPersnFirstName());
		} catch (Exception e)
		{
			System.out.println("Exception thrown & personId is:"
					+ this.user.getPersnId());

			e.printStackTrace();
			return "error";
		}

		return "success";
	}

	@JSON(serialize = true)
	public List<Person> getUsers()
	{
		return this.users;
	}

	@Action(
			value = "/getUsersListAction",
			results = { @Result(
							name = "success",
							type = "json",
							params = {
									"includeProperties",
									"users\\[\\d+\\]\\.persnId,users\\[\\d+\\]\\.persnFirstName,users\\[\\d+\\]\\.persnLastName,users\\[\\d+\\]\\.persnDetail.*,users\\[\\d+\\]\\.persnAddress.*" }) })
	public String getUsersList()
	{
		try
		{
			this.users = (ArrayList<Person>) this.manageUserService.getAllUsers();
		} catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	@Action(value = "/registerUserAction",results = { @Result(name = "success",type = "json") })
	public String registerUser()
	{
		try
		{
			this.manageUserService.registerUser(this.user);
		} catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	@Action(value = "/removeUserAction",results = { @Result(name = "success",type = "json",params = {
			"includeProperties",
			"users\\[\\d+\\]\\.persnId,users\\[\\d+\\]\\.persnFirstName,users\\[\\d+\\]\\.persnLastName,users\\[\\d+\\]\\.persnDetail.*,users\\[\\d+\\]\\.persnAddress.*" }) })
	public String removeUser()
	{
		try
		{
			this.manageUserService.deleteUser(this.user.getPersnId());

		} catch (ServiceException e)
		{
			e.printStackTrace();
		}
		return "success";
	}

	public void setUser(Person arg)
	{
		this.user = arg;
	}

	public void setUsers(ArrayList<Person> users)
	{
		this.users = users;
	}

	@Action(value = "/updateUserAction",results = { @Result(name = "success",type = "json") })
	public String updateUserDetails()
	{
		try
		{
			this.manageUserService.updateUserDetails(this.user);
		} catch (ServiceException e)
		{
			e.printStackTrace();
		}
		return "success";
	}

}
