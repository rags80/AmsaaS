/**
 *
 */
package com.ams.billingandpayment.domain.model.servicecatalog;

import java.io.Serializable;

/**
 * @author Raghavendra Badiger
 */
public class ServicePriceId implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String service;
    private String srvcPlan;

    public ServicePriceId(String srvcPlan, String srvc) {
        this.srvcPlan = srvcPlan;
        this.service = srvc;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ServicePriceId)) {
            return false;
        }
        ServicePriceId other = (ServicePriceId) obj;

        if (this.service == null) {
            if (other.service != null) {
                return false;
            }
        } else if (!this.service.equals(other.service)) {
            return false;
        }
        if (this.srvcPlan == null) {
            if (other.srvcPlan != null) {
                return false;
            }
        } else if (!this.srvcPlan.equals(other.srvcPlan)) {
            return false;
        }
        return true;
    }

    public String getService() {
        return this.service;
    }

    private void setService(String service) {
        this.service = service;
    }

    public String getSrvcPlan() {
        return this.srvcPlan;
    }

    private void setSrvcPlan(String srvcPlan) {
        this.srvcPlan = srvcPlan;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.service == null) ? 0 : this.service.hashCode());
        result = (prime * result) + ((this.srvcPlan == null) ? 0 : this.srvcPlan.hashCode());
        return result;
    }

}
