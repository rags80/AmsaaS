package com.ams.billingandpayment.application.impl.serviceportfolio;

import com.ams.billingandpayment.application.api.service.serviceportfolio.ManageService;
import com.ams.billingandpayment.domain.model.servicecatalog.Service;
import com.ams.billingandpayment.domain.repository.ServiceRepository;
import com.ams.sharedkernel.domain.repository.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Raghavendra Badiger
 */

@Transactional
@org.springframework.stereotype.Service("ManageService")
public class ManageServiceImpl implements ManageService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<Service> getAllServices() {
        return this.serviceRepository.findAll();
    }

    @Override
    public Service getService(String svcCode) {
        return this.serviceRepository.findById(svcCode);
    }

    @Override
    public Page<Service> getServicesNextPage(int index, int offset) {
        return this.serviceRepository.findNextPageData(new Page<Service>(index, offset));

    }

    @Override
    public String registerService(Service svc) {
        return this.serviceRepository.createOrUpdate(svc);

    }

    @Override
    public void removeService(String svcCode) {
        this.serviceRepository.delete(svcCode);

    }

    @Override
    public String updateServiceDetails(Service svc) {
        return this.serviceRepository.createOrUpdate(svc);
    }

}
