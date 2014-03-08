package com.ams.sales.application.impl.serviceportfolio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ams.sales.application.api.service.serviceportfolio.ManageService;
import com.ams.sales.domain.model.servicecatalog.Service;
import com.ams.sales.domain.model.servicecatalog.ServiceSubscription;
import com.ams.sales.domain.repository.ServiceRepository;
import com.ams.sharedkernel.application.common.ServiceException;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.sharedkernel.domain.repository.Page;

/**
 * @author Raghavendra Badiger
 * 
 */

@Transactional
@org.springframework.stereotype.Service("ManageService")
public class ManageServiceImpl implements ManageService
{

	@Autowired
	private ServiceRepository	serviceRepository;

	@Transactional
	public String registerService(Service svc)
	{
		return serviceRepository.createOrUpdate(svc);

	}

	@Transactional
	public String updateServiceDetails(Service svc)
	{
		return serviceRepository.createOrUpdate(svc);
	}

	@Transactional
	public void deleteService(String svcCode)
	{
		serviceRepository.delete(svcCode);

	}

	@Transactional(readOnly = true)
	public Service getService(String svcCode)
	{
		return serviceRepository.findById(svcCode);
	}

	@Transactional(readOnly = true)
	public List<Service> getAllServices()
	{
		return serviceRepository.findAll();
	}

	@Transactional(readOnly = true)
	public void registerServiceSub(ServiceSubscription serviceSub)
	{

		serviceRepository.createOrUpdateServiceSubscription(serviceSub);

	}

	@Transactional(propagation = Propagation.MANDATORY,isolation = Isolation.REPEATABLE_READ)
	public void registerServiceUsage(long persnId, String srvcCode, Period duration) throws ServiceException
	{
		/*
		 * Person persn = personRepository.findById(persnId); Service srvc =
		 * serviceRepository.findById(srvcCode); ServiceUsage svcUsage = new
		 * ServiceUsage(persn, srvc, duration); if
		 * (persn.getPersnServiceProfile().isSrvcProfileStatus(Status.ACTIVE))
		 * {
		 * 
		 * Its better to avoid train-wreck coding like below.
		 * 
		 * ServiceChargeRatePlan svcRate =
		 * persn.getPersnServiceProfile().getSubscribedSrvcsPlan
		 * ().findServiceChargeRate(srvc); if (svcRate == null) { // Find the
		 * service rate from generic service plan }
		 * 
		 * ServiceCharge svcCharge = new ServiceCharge();
		 * svcCharge.forUsage(svcUsage, svcRate, new Date());
		 * serviceRepository.createServiceCharge(svcCharge); } else { throw
		 * new ServiceException("User Service Profile isn't ACTIVE!!"); }
		 */
	}

	public void calculateRecurringServiceCharges(long userId, Period billPeriod) throws ServiceException
	{
		/*
		 * Person chargedPersn = personRepository.findById(userId);
		 * ServiceCharge serviceCharge; if
		 * (chargedPersn.getPersnServiceProfile
		 * ().isSrvcProfileStatus(Status.ACTIVE)) {
		 * 
		 * Its better to avoid train-wreck(Law of demeter) coding like below.
		 * 
		 * for (Map<Service, Set<ChargeComponent>> srvcChargeRate :
		 * chargedPersn.getPersnServiceProfile().getSubscribedSrvcsPlan().
		 * getSrvcChargeRateList()) { if
		 * (srvcChargeRate.getChargeType().equals
		 * (ServiceChargeType.RECURRING)) { serviceCharge = new
		 * ServiceCharge(); serviceCharge.forPeriod(chargedPersn, billPeriod,
		 * srvcChargeRate, new Date()); } } } else { throw new
		 * ServiceException("User Service Profile isn't ACTIVE!!"); }
		 */

	}

	public Page<Service> getServicesNextPage(int index, int offset)
	{
		return serviceRepository.findNextPageData(new Page<Service>(index, offset));

	}

}
