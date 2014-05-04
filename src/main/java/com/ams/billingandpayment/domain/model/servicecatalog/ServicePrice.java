package com.ams.billingandpayment.domain.model.servicecatalog;

import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.sharedkernel.domain.model.measuresandunits.TimeUnit;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Raghavendra Badiger
 */

@Entity
@Access(AccessType.FIELD)
@Table(name = "T_SERVICEPRICE")
@IdClass(ServicePriceId.class)
public class ServicePrice implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "srvcPlanName")
    private ServicePlan srvcPlan;

    @Id
    @ManyToOne
    @JoinColumn(name = "srvcCode")
    private Service service;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ServicePriceCategory srvcPriceCategory;

    private Money srvcPricePerUnit;

    @Enumerated(EnumType.STRING)
    private TimeUnit srvcUnitOfMeasure;

    public ServicePrice(ServicePlan srvcPlan, Service srvc, String srvcPriceCategory,
                        BigDecimal pricePerUnit, String currencyCode,
                        String unitOfMeasure) {
        this.srvcPlan = srvcPlan;
        this.service = srvc;
        this.srvcPriceCategory = ServicePriceCategory.valueOf(srvcPriceCategory);
        this.srvcPricePerUnit = new Money(pricePerUnit, Currency.getInstance(currencyCode));
        this.srvcUnitOfMeasure = TimeUnit.valueOf(unitOfMeasure);
    }

    public ServicePrice(ServicePlan servicePlan, Service srvc, String srvcPriceCategory,
                        Money srvcPrice,
                        String unitOfMeasure) {
        this.srvcPlan = servicePlan;
        this.service = srvc;
        this.srvcPriceCategory = ServicePriceCategory.valueOf(srvcPriceCategory);
        this.srvcPricePerUnit = srvcPrice;
        this.srvcUnitOfMeasure = TimeUnit.valueOf(unitOfMeasure);

    }

	/*
     * Domain functions
	 */

    public void updateDetails(String srvcPriceCategory, BigDecimal srvcPriceAmountValue, String srvcPriceAmountCurrency, String srvcPriceUnitOfMeasure) {
        this.srvcPriceCategory = ServicePriceCategory.valueOf(srvcPriceCategory);
        this.srvcPricePerUnit = new Money(srvcPriceAmountValue, Currency.getInstance(srvcPriceAmountCurrency));
        this.srvcUnitOfMeasure = TimeUnit.valueOf(srvcPriceUnitOfMeasure);
    }

    /*
     * Accessor & Mutators
     */
    public Service getService() {
        return this.service;
    }

    public ServicePlan getSrvcPlan() {
        return this.srvcPlan;
    }

    public ServicePriceCategory getSrvcPriceCategory() {
        return this.srvcPriceCategory;
    }

    public Money getSrvcPricePerUnit() {
        return this.srvcPricePerUnit;
    }

    public TimeUnit getSrvcUnitOfMeasure() {
        return this.srvcUnitOfMeasure;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.service == null) ? 0 : this.service.hashCode());
        result = (prime * result) + ((this.srvcPlan == null) ? 0 : this.srvcPlan.hashCode());
        result = (prime * result) + ((this.srvcPriceCategory == null) ? 0 : this.srvcPriceCategory.hashCode());
        result = (prime * result) + ((this.srvcPricePerUnit == null) ? 0 : this.srvcPricePerUnit.hashCode());
        result = (prime * result) + ((this.srvcUnitOfMeasure == null) ? 0 : this.srvcUnitOfMeasure.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ServicePrice)) {
            return false;
        }
        ServicePrice other = (ServicePrice) obj;
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
        if (this.srvcPriceCategory != other.srvcPriceCategory) {
            return false;
        }
        if (this.srvcPricePerUnit == null) {
            if (other.srvcPricePerUnit != null) {
                return false;
            }
        } else if (!this.srvcPricePerUnit.equals(other.srvcPricePerUnit)) {
            return false;
        }
        if (this.srvcUnitOfMeasure != other.srvcUnitOfMeasure) {
            return false;
        }
        return true;
    }

}
