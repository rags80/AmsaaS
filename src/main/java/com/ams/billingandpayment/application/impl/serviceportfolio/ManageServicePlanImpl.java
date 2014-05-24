package com.ams.billingandpayment.application.impl.serviceportfolio;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ams.billingandpayment.application.api.dto.ServicePriceCommand;
import com.ams.billingandpayment.application.api.service.serviceportfolio.InvalidServicePricePlanException;
import com.ams.billingandpayment.application.api.service.serviceportfolio.ManageServicePlan;
import com.ams.billingandpayment.domain.model.serviceportfolio.Service;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePlan;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice;
import com.ams.billingandpayment.domain.repository.ServicePlanRepository;
import com.ams.billingandpayment.domain.repository.ServiceRepository;
import com.ams.sharedkernel.domain.repository.Page;

@Transactional
@org.springframework.stereotype.Service("ManageServicePlan")
public class ManageServicePlanImpl implements ManageServicePlan
{

	@Autowired
	private ServiceRepository	serviceRepository;

	@Autowired
	private ServicePlanRepository	servicePlanRepository;

	@Override
	public List<ServicePlan> getAllServicePlans()
	{
		return this.servicePlanRepository.findAll();
	}

	@Override
	public Page<ServicePrice> getNextPageOfServicePricesForPlan(String srvcPlanName, int startIndex, int offset)
	{
		return this.servicePlanRepository.findNextPageOfServicePrices(srvcPlanName, startIndex, offset);
	}

	@Override
	public ServicePlan getServicePlan(String planName)
	{
		return this.servicePlanRepository.findById(planName);
	}

	@Override
	public Page<ServicePlan> getServicePlansNextPage(int index, int offset)
	{

		return this.servicePlanRepository.findNextPageData(new Page<ServicePlan>(index, offset));
	}

	/*
	 * Service Plan query/read functions implementation
	 */

	@Override
	public List<ServicePrice> getServicePricesForPlan(String srvcPlanName)
	{
		return this.servicePlanRepository.findAllServicePriceByPlanName(srvcPlanName);
	}

	/*
	 * Service Plan command/write functions implementation
	 */
	@Override
	public String registerServicePlan(ServicePlan srvcPlan)
	{

		return this.servicePlanRepository.createOrUpdate(srvcPlan);

	}

	/*
	 * Plan-Service Price command/write functions implementation
	 */

	@Override
	public String registerServicePriceToPlan(ServicePriceCommand srvcPriceCommand)
	{
		ServicePlan srvcPlan = this.servicePlanRepository.findById(srvcPriceCommand.getSrvcPlanName());
		Service srvc = this.serviceRepository.findById(srvcPriceCommand.getSrvcCode());

		ServicePrice srvcRate = srvcPlan.servicePriceToPlan(srvcPriceCommand.getSrvcPriceCategory(),
													srvc,
													srvcPriceCommand.getSrvcPriceAmountValue(),
													srvcPriceCommand.getSrvcPriceAmountCurrency(),
													srvcPriceCommand.getSrvcPriceUnitOfMeasure());

		return this.servicePlanRepository.saveOrUpdateServicePriceToPlan(srvcRate);

	}

	@Override
	public void removeServicePlan(String srvcPlanName)
	{
		this.servicePlanRepository.delete(srvcPlanName);
	}

	@Override
	public void removeServicePrice(ServicePriceCommand spc) throws InvalidServicePricePlanException
	{
		this.servicePlanRepository.deleteServicePriceOfPlan(spc.getSrvcPlanName(), spc.getSrvcCode());

	}

	@Override
	public void removeServicePrice(String srvcPlanName, String srvcCode) throws InvalidServicePricePlanException
	{
		this.servicePlanRepository.deleteServicePriceOfPlan(srvcPlanName, srvcCode);
	}

	/*
	 * Plan-Service Price query/read functions implementation
	 */

	@Override
	public String updateServicePlanDetails(ServicePlan srvcPlan)
	{
		return this.servicePlanRepository.createOrUpdate(srvcPlan);
	}

	@Override
	public void updateServicePriceDetails(ServicePriceCommand spc)
	{
		ServicePrice srvcRate = this.servicePlanRepository.findServicePriceByCriteria(spc.getSrvcPlanName(), spc.getSrvcCode());
		srvcRate.updateDetails(spc.getSrvcPriceCategory(),
							BigDecimal.valueOf(spc.getSrvcPriceAmountValue()),
							spc.getSrvcPriceAmountCurrency(),
							spc.getSrvcPriceUnitOfMeasure());
	}

}
