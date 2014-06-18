package com.ams.billingandpayment.ports.adapter.rest.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ams.billingandpayment.application.api.service.serviceportfolio.ManageService;
import com.ams.billingandpayment.domain.model.serviceportfolio.Service;
import com.ams.sharedkernel.domain.repository.Page;
import com.ams.sharedkernel.domain.service.exception.ServiceException;

/**
 * @author Raghavendra Badiger
 * 
 */
@Controller
public class ServiceController
{
	@Autowired
	private ManageService	manageService;

	@RequestMapping("{srvcCode}")
	@ResponseBody
	public Service getServiceDetail(@PathVariable String srvcCode)
	{
		return this.manageService.getService(srvcCode);

	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Service> getServiceList()
	{
		return (this.manageService.getAllServices());

	}

	@RequestMapping(value = "page/{index}&{offset}",method = RequestMethod.GET)
	@ResponseBody
	public Page<Service> getServiceListNextPage(@PathVariable final int index, @PathVariable final int offset)
	{
		return (this.manageService.getServicesNextPage(index, offset));

	}

	@RequestMapping(value = "{srvcCode}",method = RequestMethod.DELETE)
	@ResponseBody
	public void removeService(@PathVariable final String srvcCode)
	{
		System.out.println(srvcCode);
		this.manageService.removeService(srvcCode);

	}

	@RequestMapping(value = "/services",method = RequestMethod.POST)
	@ResponseBody
	public String saveService(@RequestBody final Service service) throws ServiceException
	{
		return this.manageService.registerService(service);

	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public String updateServiceDetail(@RequestBody final Service service)
	{
		System.out.println(service.getSrvcCode());
		throw new ServiceException("This method is not implemented");

	}

}
