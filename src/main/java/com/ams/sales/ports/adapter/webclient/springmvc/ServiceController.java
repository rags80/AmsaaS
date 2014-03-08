package com.ams.sales.ports.adapter.webclient.springmvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ams.sales.application.api.service.serviceportfolio.ManageService;
import com.ams.sales.domain.model.servicecatalog.Service;
import com.ams.sharedkernel.application.common.ServiceException;
import com.ams.sharedkernel.domain.repository.Page;

@Controller
public class ServiceController
{
	@Autowired
	private ManageService	manageService;

	@RequestMapping("services")
	@ResponseBody
	public List<Service> getServiceList()
	{
		return (manageService.getAllServices());

	}

	@RequestMapping("services/page/{index}&{offset}")
	@ResponseBody
	public Page<Service> getServiceListNextPage(@PathVariable final int index, @PathVariable final int offset)
	{
		return (manageService.getServicesNextPage(index, offset));

	}

	@RequestMapping("services/{srvcCode}")
	@ResponseBody
	public Service getServiceDetail(@PathVariable String srvcCode)
	{
		return manageService.getService(srvcCode);

	}

	@RequestMapping(value = "services",method = RequestMethod.POST)
	@ResponseBody
	public String saveService(@RequestBody final Service service) throws ServiceException
	{
		try
		{
			System.out.println(service.getSrvcCode());
			return manageService.registerService(service);
		} catch (Exception e)
		{
			throw new ServiceException(e.toString());
		}

	}

	@RequestMapping(value = "services",method = RequestMethod.PUT)
	@ResponseBody
	public String updateServiceDetail(@RequestBody final Service service)
	{
		System.out.println(service.getSrvcCode());
		return manageService.registerService(service);

	}

	@RequestMapping(value = "services/{srvcCode}",method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteService(@PathVariable final String srvcCode)
	{
		System.out.println(srvcCode);
		manageService.deleteService(srvcCode);

	}

}
