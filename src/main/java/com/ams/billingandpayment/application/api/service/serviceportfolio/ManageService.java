package com.ams.billingandpayment.application.api.service.serviceportfolio;

import java.util.List;

import com.ams.billingandpayment.domain.model.servicecatalog.Service;
import com.ams.sharedkernel.domain.repository.Page;

public interface ManageService
{
	String registerService(Service svc);

	String updateServiceDetails(Service svc);

	void removeService(String svcCode);

	Service getService(String svcCode);

	List<Service> getAllServices();

	Page<Service> getServicesNextPage(int index, int offset);

}
