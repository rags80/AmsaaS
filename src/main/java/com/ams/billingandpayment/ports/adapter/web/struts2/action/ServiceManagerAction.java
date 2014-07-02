package com.ams.billingandpayment.ports.adapter.web.struts2.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.ams.billingandpayment.application.api.service.serviceportfolio.ManageService;
import com.ams.billingandpayment.domain.model.serviceportfolio.Service;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/")
@InterceptorRef(value = "json")
public class ServiceManagerAction extends ActionSupport
{

	private static final long	serialVersionUID	= 1L;
	@Autowired
	private ManageService		manageServices;
	private Service			aptService;
	private List<Service>		aptServicesList;

	public Service getAptService()
	{
		return this.aptService;
	}

	public List<Service> getAptServicesList()
	{
		return this.aptServicesList;
	}

	public void setAptService(Service arg)
	{
		this.aptService = arg;
	}

	public void setAptServicesList(List<Service> servicesList)
	{
		this.aptServicesList = servicesList;
	}

	@Action(value = "/registerNewServiceAction",interceptorRefs = { @InterceptorRef(value = "jsonValidationWorkflowStack") },results = { @Result(name = "success",type = "json",params = { "root", "aptServicesList" }) })
	public String registerNewService()
	{
		try
		{
			this.manageServices.registerService(this.aptService);
			this.manageServices.getAllServices();
			return "success";

		} catch (Exception excptn)
		{
			return "failure";
		}

	}

	@Action(value = "/updateServiceDetailAction",interceptorRefs = { @InterceptorRef(value = "jsonValidationWorkflowStack") },results = { @Result(name = "success",type = "json",params = { "root", "aptServicesList" }) })
	public String updateServiceDetails()
	{
		try
		{
			System.out.println("Service code:" + this.aptService.getSrvcCode());
			this.manageServices.updateServiceDetails(this.aptService);
			this.manageServices.getAllServices();
			return "success";

		} catch (Exception excptn)
		{
			excptn.printStackTrace();
		}

		return "failure";
	}

	@Action(value = "/deleteServiceAction",interceptorRefs = { @InterceptorRef(value = "jsonValidationWorkflowStack") },results = { @Result(name = "success",type = "json",params = { "root", "aptServicesList" }) })
	public String deleteService()
	{
		try
		{
			System.out.println("Service code:" + this.aptService.getSrvcCode());
			this.manageServices.removeService(this.aptService.getSrvcCode());
			this.manageServices.getAllServices();
			return "success";

		} catch (Exception excptn)
		{
			excptn.printStackTrace();
		}

		return "failure";
	}

	@Action(value = "/getServiceDetailAction",results = { @Result(name = "success",type = "json",params = { "root", "aptService" }) })
	public String getServiceDetail()
	{
		this.aptService = this.manageServices.getService(this.aptService.getSrvcCode());
		return "success";
	}

	@Action(value = "/getServicesListAction",results = { @Result(name = "success",type = "json",params = { "root", "aptServicesList" }) })
	public String getServicesList()
	{
		this.aptServicesList = this.manageServices.getAllServices();
		return "success";
	}

}
