package com.ams.billingandpayment.application.impl.serviceportfolio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ams.billingandpayment.application.api.service.serviceportfolio.ManageService;
import com.ams.billingandpayment.domain.model.serviceportfolio.Service;
import com.ams.billingandpayment.domain.repository.ServiceRepository;
import com.ams.sharedkernel.domain.repository.Page;

/**
 * @author Raghavendra Badiger
 */

@Transactional
@org.springframework.stereotype.Service("ManageService")
public class ManageServiceImpl implements ManageService
{

	@Autowired
	private ServiceRepository	serviceRepository;

	@Override
	public String registerService(Service svc)
	{
		return this.serviceRepository.create(svc);

	}

	@Override
	public void removeService(String svcCode)
	{
		this.serviceRepository.delete(svcCode);

	}

	@Override
	public String updateServiceDetails(Service svc)
	{
		return this.serviceRepository.update(svc);
	}

	@Override
	public List<Service> getAllServices()
	{
		return this.serviceRepository.findAll();
	}

	@Override
	public Service getService(String svcCode)
	{
		return this.serviceRepository.findById(svcCode);
	}

	@Override
	public Page<Service> getServicesNextPage(int index, int offset)
	{
		return this.serviceRepository.findNextPageData(new Page<Service>(index, offset));

	}

}
