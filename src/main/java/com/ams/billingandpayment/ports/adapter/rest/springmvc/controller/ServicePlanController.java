package com.ams.billingandpayment.ports.adapter.rest.springmvc.controller;

import com.ams.billingandpayment.application.api.service.serviceportfolio.ManageServicePlan;
import com.ams.billingandpayment.domain.model.services.ServicePlan;
import com.ams.sharedkernel.application.api.exception.ServiceException;
import com.ams.sharedkernel.domain.repository.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ServicePlanController {
    @Autowired
    private ManageServicePlan manageServicePlan;

    @RequestMapping(value = "serviceplans/{srvcPlanName}", method = RequestMethod.DELETE)
    @ResponseBody
    public List<ServicePlan> deleteServicePlan(@PathVariable final String srvcPlanName) {
        System.out.println("Delete service plan:" + srvcPlanName);
        this.manageServicePlan.removeServicePlan(srvcPlanName);
        return this.getServicePlanList();

    }

    @RequestMapping(value = "serviceplans", method = RequestMethod.GET)
    @ResponseBody
    public List<ServicePlan> getServicePlanList() {
        System.out.println("GET SERVICE PLAN CATALOGUE");
        return this.manageServicePlan.getAllServicePlans();
    }

    @RequestMapping(value = "serviceplans/page/{currentIndex}&{nextIndex}", method = RequestMethod.GET)
    @ResponseBody
    public Page<ServicePlan> getServicePlanNextPage(@PathVariable final int currentIndex, @PathVariable final int nextIndex) {
        System.out.println("GET PAGINATED SERVICE PLAN CATALOGUE");
        Page<ServicePlan> p = new Page<ServicePlan>(currentIndex, nextIndex);
        p.setPageDataList(this.manageServicePlan.getAllServicePlans());
        return p;
    }

    @RequestMapping(value = "serviceplans", method = RequestMethod.POST)
    @ResponseBody
    public String registerNewServicePlan(@RequestBody final ServicePlan servicePlan) throws ServiceException {
        try {
            System.out.println("Persisting Service Plan:" + servicePlan.getSrvcPlanCreatDate());
            return this.manageServicePlan.registerServicePlan(servicePlan);
        } catch (Exception e) {
            throw new ServiceException(e.toString());

        }

    }

    @RequestMapping(value = "serviceplans", method = RequestMethod.PUT)
    @ResponseBody
    public String updateServicePlan(@RequestBody final ServicePlan servicePlan) throws ServiceException {
        try {
            System.out.println("Service Plan Name:" + servicePlan.getSrvcPlanName() + "\n Persisting Service Plan:" + servicePlan.getSrvcPlanCreatDate());
            return this.manageServicePlan.updateServicePlanDetails(servicePlan);
        } catch (Exception e) {
            throw new ServiceException(e.toString());

        }

    }

}
