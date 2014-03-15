package com.ams.billing.application.impl.serviceportfolio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ams.billing.application.api.commandquery.ServicePriceCommand;
import com.ams.billing.application.api.service.serviceportfolio.InvalidServicePricePlanException;
import com.ams.billing.application.api.service.serviceportfolio.ManageServicePlan;
import com.ams.billing.domain.model.servicecatalog.Service;
import com.ams.billing.domain.model.servicecatalog.ServicePlan;
import com.ams.billing.domain.model.servicecatalog.ServicePrice;
import com.ams.billing.domain.repository.ServicePlanRepository;
import com.ams.billing.domain.repository.ServiceRepository;
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

	public List<ServicePlan> getAllServicePlans()
	{
		return this.servicePlanRepository.findAll();
	}

	public ServicePlan getServicePlanDetails(String srvcPlanName)
	{
		return this.servicePlanRepository.findById(srvcPlanName);
	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ams.sales.application.api.service.serviceportfolio.ManageServicePlan
	 * #updateServicePriceDetails(com.ams.sales.application.api.commandquery.
	 * ServicePriceCommand)
	 */
	public void updateServicePriceDetails(ServicePriceCommand spc)
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ams.sales.application.api.service.serviceportfolio.ManageServicePlan
	 * #removeServicePrice(java.lang.String, java.lang.String)
	 */
	public void removeServicePrice(String srvcPlanName, String srvcCode) throws InvalidServicePricePlanException
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ams.sales.application.api.service.serviceportfolio.ManageServicePlan
	 * #removeServicePrice(com.ams.sales.application.api.commandquery.
	 * ServicePriceCommand)
	 */
	public void removeServicePrice(ServicePriceCommand spc) throws InvalidServicePricePlanException
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ams.sales.application.api.service.serviceportfolio.ManageServicePlan
	 * #getServicePricesForPlan(java.lang.String)
	 */
	public List<ServicePrice> getServicePricesForPlan(String srvcPlanName)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ams.sales.application.api.service.serviceportfolio.ManageServicePlan
	 * #getNextPageOfServicePricesForPlan(java.lang.String, int, int)
	 */
	public Page<ServicePrice> getNextPageOfServicePricesForPlan(String srvcPlanName, int startIndex, int offset)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
