package com.ams.billingandpayment.application.api.service.serviceportfolio;

import com.ams.billingandpayment.domain.model.serviceportfolio.Service;
import com.ams.sharedkernel.domain.repository.Page;

import java.util.List;

public interface ManageService
{
	List<Service> getAllServices();

	Service getService(String svcCode);

	Page<Service> getServicesNextPage(int index, int offset);

	String registerService(Service svc);

	void removeService(String svcCode);

	String updateServiceDetails(Service svc);

}
