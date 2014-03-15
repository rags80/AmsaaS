package com.ams.billing.application.impl.serviceportfolio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ams.billing.application.api.service.serviceportfolio.ManageService;
import com.ams.billing.domain.model.servicecatalog.Service;
import com.ams.billing.domain.repository.ServiceRepository;
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

	public String registerService(Service svc)
	{
		return this.serviceRepository.createOrUpdate(svc);

	}

	public String updateServiceDetails(Service svc)
	{
		return this.serviceRepository.createOrUpdate(svc);
	}

	public void removeService(String svcCode)
	{
		this.serviceRepository.delete(svcCode);

	}

	public Service getService(String svcCode)
	{
		return this.serviceRepository.findById(svcCode);
	}

	public List<Service> getAllServices()
	{
		return this.serviceRepository.findAll();
	}

	public Page<Service> getServicesNextPage(int index, int offset)
	{
		return this.serviceRepository.findNextPageData(new Page<Service>(index, offset));

	}

}
