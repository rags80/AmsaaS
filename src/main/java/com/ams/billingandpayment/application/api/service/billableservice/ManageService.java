package com.ams.billingandpayment.application.api.service.billableservice;

import java.util.List;

import com.ams.billingandpayment.domain.model.service.Service;
import com.ams.sharedkernel.domain.repository.Page;

public interface ManageService
{
	String registerService(Service svc);

	String updateServiceDetails(Service svc);

	void deleteService(String svcCode);

	Service getService(String svcCode);

	List<Service> getAllServices();

	Page<Service> getServicesNextPage(int index, int offset);

}
