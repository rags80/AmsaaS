package com.ams.sales.application.impl.serviceportfolio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ams.sales.application.api.service.serviceportfolio.ManageServicePlan;
import com.ams.sales.application.api.servicedata.ServiceRateDto;
import com.ams.sales.domain.model.servicecatalog.InvalidServiceRatePlanException;
import com.ams.sales.domain.model.servicecatalog.ServicePlan;
import com.ams.sales.domain.model.servicecatalog.ServiceRate;
import com.ams.sales.domain.repository.ServicePlanRepository;
import com.ams.sales.domain.repository.ServiceRepository;
import com.ams.sharedkernel.domain.repository.Page;

@Transactional
@org.springframework.stereotype.Service("ManageServicePlan")
public class ManageServicePlanImpl implements ManageServicePlan
{

	@Autowired
	private ServiceRepository	serviceRepository;

	@Autowired
	private ServicePlanRepository	servicePlanRepository;

	public String registerServicePlan(ServicePlan srvcPlan)
	{

		return servicePlanRepository.createOrUpdate(srvcPlan);

	}

	public String updateServicePlanDetails(ServicePlan srvcPlan)
	{
		return servicePlanRepository.createOrUpdate(srvcPlan);
	}

	public void removeServicePlan(String srvcPlanName)
	{
		servicePlanRepository.delete(srvcPlanName);
	}

	public List<ServicePlan> getAllServicePlans()
	{
		return servicePlanRepository.findAll();
	}

	public ServicePlan getServicePlanDetails(String srvcPlanName)
	{
		return servicePlanRepository.findById(srvcPlanName);
	}

	public String addServiceRateComponent(ServiceRateDto srvcRateDTO)
	{
		ServicePlan srvcPlan = servicePlanRepository.findById(srvcRateDTO.getSrvcPlanName());

		ServiceRate srvcRate = srvcPlan.addServiceRateToPlan(srvcRateDTO.getSrvcRateCategory(),
													srvcRateDTO.getService(),
													srvcRateDTO.getChargeName(),
													srvcRateDTO.getChargeType(),
													srvcRateDTO.getChargeAmount(),
													srvcRateDTO.getChargeCurrency(),
													srvcRateDTO.getChargeUnit(),
													srvcRateDTO.getChargeFrequency(),
													srvcRateDTO.isPercentBased());
		return servicePlanRepository.saveOrUpdateServiceRate(srvcRate);

	}

	public void removeServiceRateComponent(String srvcPlanName, String srvcCode) throws InvalidServiceRatePlanException
	{

		servicePlanRepository.deleteServiceRateByCriteria(srvcPlanName, srvcCode);
	}

	public void updateServiceRateComponentDetails(ServiceRateDto srvcRateDTO)
	{
		ServicePlan srvcPlan = servicePlanRepository.findById(srvcRateDTO.getSrvcPlanName());

		ServiceRate srvcRate = srvcPlan.addServiceRateToPlan(srvcRateDTO.getSrvcRateCategory(),
													srvcRateDTO.getService(),
													srvcRateDTO.getChargeName(),
													srvcRateDTO.getChargeType(),
													srvcRateDTO.getChargeAmount(),
													srvcRateDTO.getChargeCurrency(),
													srvcRateDTO.getChargeUnit(),
													srvcRateDTO.getChargeFrequency(),
													srvcRateDTO.isPercentBased());
		servicePlanRepository.saveOrUpdateServiceRate(srvcRate);

	}

	public List<ServiceRate> getServiceRatesForPlanList(String srvcPlanName)
	{
		return servicePlanRepository.findAllServiceRateByPlanName(srvcPlanName);
	}

	public Page<ServiceRate> getServiceRatesForPlanNextPage(String srvcPlanName, int startIndex, int offset)
	{
		return servicePlanRepository.findServiceRateNextPageData(srvcPlanName, startIndex, offset);
	}

	public void removeServiceRateComponent(ServiceRateDto srvcRateDTO) throws InvalidServiceRatePlanException
	{
		servicePlanRepository.deleteServiceRateByCriteria(srvcRateDTO.getSrvcPlanName(), srvcRateDTO.getService().getSrvcCode(), srvcRateDTO.getChargeType());
	}

}
