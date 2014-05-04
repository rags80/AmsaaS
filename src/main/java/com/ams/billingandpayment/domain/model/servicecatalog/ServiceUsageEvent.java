package com.ams.billingandpayment.domain.model.servicecatalog;

import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.sharedkernel.domain.model.measuresandunits.Quantity;
import com.ams.sharedkernel.domain.model.measuresandunits.TimeUnit;
import com.ams.users.domain.model.Person;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Raghavendra Badiger
 */
@Table(name = "T_SERVICEUSAGE_EVENTS")
public class ServiceUsageEvent implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Service srvc;
    private Person srvcUser;
    private Period srvcUsagePeriod;

    public ServiceUsageEvent() {
    }

    public ServiceUsageEvent(Person persn, Service srvc, Period duration) {
        this.srvc = srvc;
        this.srvcUser = persn;
        this.srvcUsagePeriod = duration;

    }

	/*
     * SERVICE CHARGE DOMAIN FUNCTIONS
	 */

    public Service getSrvc() {
        return this.srvc;
    }

    private void setSrvc(Service srvc) {
        this.srvc = srvc;
    }

    public Period getSrvcUsagePeriod() {
        return this.srvcUsagePeriod;
    }

	/*
	 * SERVICE CHARGE ACCESSOR & MUTATOR FUNCTIONS
	 */

    private void setSrvcUsagePeriod(Period srvcUsagePeriod) {
        this.srvcUsagePeriod = srvcUsagePeriod;
    }

    public Quantity srvcUsageQuantity(TimeUnit unitOfMeasure) {
        return Quantity.quantify(this.srvcUsagePeriod, unitOfMeasure);

    }

    public Quantity getSrvcUsageQty() {

        return null;
    }

    public Person getSrvcUser() {
        return this.srvcUser;
    }

    private void setSrvcUser(Person srvcUser) {
        this.srvcUser = srvcUser;
    }

}
