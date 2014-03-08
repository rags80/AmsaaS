package com.ams.sales.ports.adapter.webclient.springmvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ams.sales.application.api.datamapper.ServiceRateMapper;
import com.ams.sales.application.api.service.serviceportfolio.ManageServicePlan;
import com.ams.sales.application.api.servicedata.ServiceRateDto;
import com.ams.sales.application.api.servicedata.UnitsDto;
import com.ams.sales.domain.model.servicecatalog.Service;
import com.ams.sales.domain.model.servicecatalog.ServicePlan;
import com.ams.sales.domain.model.servicecatalog.ServiceRate;
import com.ams.sharedkernel.application.common.ServiceException;
import com.ams.sharedkernel.domain.repository.Page;
import com.ams.sharedkernel.ports.adapter.webclient.springmvc.controller.ServerMessage;

@Controller
public class ServicePlanController
{
	@Autowired
	private ManageServicePlan	manageServicePlan;

	@Autowired
	private ServiceRateMapper	srvcRateMapper;

	@RequestMapping(value = "serviceplans",method = RequestMethod.POST)
	@ResponseBody
	public String registerNewServicePlan(@RequestBody final ServicePlan servicePlan) throws ServiceException
	{
		try
		{
			System.out.println("Persisting Service Plan:" + servicePlan.getSrvcPlanCreatDate());
			return manageServicePlan.registerServicePlan(servicePlan);
		} catch (Exception e)
		{
			throw new ServiceException(e.toString());

		}

	}

	@RequestMapping(value = "serviceplans",method = RequestMethod.PUT)
	@ResponseBody
	public String updateServicePlan(@RequestBody final ServicePlan servicePlan) throws ServiceException
	{
		try
		{
			System.out.println("Service Plan Name:" + servicePlan.getSrvcPlanName() + "\n Persisting Service Plan:" + servicePlan.getSrvcPlanCreatDate());
			return manageServicePlan.updateServicePlanDetails(servicePlan);
		} catch (Exception e)
		{
			throw new ServiceException(e.toString());

		}

	}

	@RequestMapping(value = "serviceplans/{srvcPlanName}",method = RequestMethod.DELETE)
	@ResponseBody
	public List<ServicePlan> deleteServicePlan(@PathVariable final String srvcPlanName)
	{
		System.out.println("Delete service plan:" + srvcPlanName);
		manageServicePlan.removeServicePlan(srvcPlanName);
		return this.getServicePlanList();

	}

	@RequestMapping(value = "serviceplans",method = RequestMethod.GET)
	@ResponseBody
	public List<ServicePlan> getServicePlanList()
	{
		System.out.println("GET SERVICE PLAN CATALOGUE");
		return manageServicePlan.getAllServicePlans();
	}

	@RequestMapping(value = "serviceplans/page/{currentIndex}&{nextIndex}",method = RequestMethod.GET)
	@ResponseBody
	public Page<ServicePlan> getServicePlanNextPage(@PathVariable final int currentIndex, @PathVariable final int nextIndex)
	{
		System.out.println("GET PAGINATED SERVICE PLAN CATALOGUE");
		Page<ServicePlan> p = new Page<ServicePlan>(currentIndex, nextIndex);
		p.setPageDataList(manageServicePlan.getAllServicePlans());
		return p;
	}

	@RequestMapping(value = "serviceplans/{srvcPlanName}/servicerates/page/{currentIndex}&{nextIndex}",method = RequestMethod.GET)
	@ResponseBody
	public Page<ServiceRateDto> getServiceRatesForPlan(@PathVariable final String srvcPlanName, @PathVariable final int currentIndex, @PathVariable final int nextIndex)
	{
		System.out.println("GET SERVICE RATES CATALOGUE");
		Page<ServiceRate> p = new Page<ServiceRate>(currentIndex, nextIndex);
		p = manageServicePlan.getServiceRatesForPlanNextPage(srvcPlanName, currentIndex, nextIndex);
		Page<ServiceRateDto> dtoPage = new Page<ServiceRateDto>();
		dtoPage.setPageDataList(srvcRateMapper.getServiceRateDTOList(p.getPageDataList()));
		return dtoPage;
	}

	@RequestMapping(value = "unitsnmeasure",method = RequestMethod.GET)
	@ResponseBody
	public UnitsDto getAllUnitsList()
	{
		return new UnitsDto();

	}

	@RequestMapping(value = "serviceplans/{srvcPlanName}/servicerates",method = RequestMethod.POST)
	@ResponseBody
	public ServerMessage registerNewServiceRate(@PathVariable final String srvcPlanName, @RequestBody final ServiceRateDto srvcRateDTO)
	{
		srvcRateDTO.setSrvcPlanName(srvcPlanName);
		return new ServerMessage(manageServicePlan.addServiceRateComponent(srvcRateDTO));

	}

	@RequestMapping(value = "serviceplans/{srvcPlanName}/servicerates",method = RequestMethod.PUT)
	@ResponseBody
	public void updateServiceRate(@RequestBody final ServiceRateDto srvcRateDTO) throws ServiceException
	{
		try
		{
			manageServicePlan.updateServiceRateComponentDetails(srvcRateDTO);
		} catch (Exception e)
		{
			throw new ServiceException(e.toString());

		}

	}

	@RequestMapping(value = "serviceplans/{srvcPlanName}/servicerates/{srvcCode}/{chargeType}",method = RequestMethod.DELETE)
	@ResponseBody
	public List<ServiceRate> deleteServiceRate(@PathVariable final String srvcPlanName, @PathVariable final String srvcCode, @PathVariable final String chargeType)
	{
		ServiceRateDto srvcRateDTO = new ServiceRateDto();
		srvcRateDTO.setSrvcPlanName(srvcPlanName);
		srvcRateDTO.setService(new Service(srvcCode, "", ""));
		srvcRateDTO.setChargeType(chargeType);
		manageServicePlan.removeServiceRateComponent(srvcRateDTO);
		return new ArrayList<ServiceRate>();

	}

}
