package com.ams.billingandpayment.application.impl.serviceportfolio;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ams.billingandpayment.application.api.commandquery.ServicePriceCommand;
import com.ams.billingandpayment.application.api.service.serviceportfolio.InvalidServicePricePlanException;
import com.ams.billingandpayment.application.api.service.serviceportfolio.ManageServicePlan;
import com.ams.billingandpayment.domain.model.servicecatalog.Service;
import com.ams.billingandpayment.domain.model.servicecatalog.ServicePlan;
import com.ams.billingandpayment.domain.model.servicecatalog.ServicePrice;
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

	/*
	 * Service Plan command/write functions implementation
	 */
	public String registerServicePlan(ServicePlan srvcPlan)
	{

		return this.servicePlanRepository.createOrUpdate(srvcPlan);

	}

	public String updateServicePlanDetails(ServicePlan srvcPlan)
	{
		return this.servicePlanRepository.createOrUpdate(srvcPlan);
	}

	public void removeServicePlan(String srvcPlanName)
	{
		this.servicePlanRepository.delete(srvcPlanName);
	}

	public ServicePlan getServicePlan(String planName)
	{
		return this.servicePlanRepository.findById(planName);
	}

	/*
	 * Service Plan query/read functions implementation
	 */

	public List<ServicePlan> getAllServicePlans()
	{
		return this.servicePlanRepository.findAll();
	}

	public Page<ServicePlan> getServicePlansNextPage(int index, int offset)
	{

		return this.servicePlanRepository.findNextPageData(new Page<ServicePlan>(index, offset));
	}

	/*
	 * Plan-Service Price command/write functions implementation
	 */

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

	public void updateServicePriceDetails(ServicePriceCommand spc)
	{
		ServicePrice srvcRate = this.servicePlanRepository.findServicePriceByCriteria(spc.getSrvcPlanName(), spc.getSrvcCode());
		srvcRate.updateDetails(spc.getSrvcPriceCategory(),
							BigDecimal.valueOf(spc.getSrvcPriceAmountValue()),
							spc.getSrvcPriceAmountCurrency(),
							spc.getSrvcPriceUnitOfMeasure());
	}

	public void removeServicePrice(String srvcPlanName, String srvcCode) throws InvalidServicePricePlanException
	{
		this.servicePlanRepository.deleteServicePriceOfPlan(srvcPlanName, srvcCode);
	}

	public void removeServicePrice(ServicePriceCommand spc) throws InvalidServicePricePlanException
	{
		this.servicePlanRepository.deleteServicePriceOfPlan(spc.getSrvcPlanName(), spc.getSrvcCode());

	}

	/*
	 * Plan-Service Price query/read functions implementation
	 */

	public List<ServicePrice> getServicePricesForPlan(String srvcPlanName)
	{
		return this.servicePlanRepository.findAllServicePriceByPlanName(srvcPlanName);
	}

	public Page<ServicePrice> getNextPageOfServicePricesForPlan(String srvcPlanName, int startIndex, int offset)
	{
		return this.servicePlanRepository.findNextPageOfServicePrices(srvcPlanName, startIndex, offset);
	}

}
