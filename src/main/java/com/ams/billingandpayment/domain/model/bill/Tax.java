package com.ams.billingandpayment.domain.model.bill;

import com.ams.sharedkernel.domain.model.measuresandunits.Money;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Tax implements Serializable {
    public static final Tax DEFAULT_TAX = new Tax(Money.ZERO, "Default Tax");
    private static final long serialVersionUID = 1L;
    private Money taxAmount;
    private String taxDescription;

    public Tax(Money taxAmount, String description) {
        this.taxAmount = taxAmount;
        this.taxDescription = description;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Money getTaxAmount() {
        return this.taxAmount;
    }

    public String getTaxDescription() {
        return this.taxDescription;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.taxAmount == null) ? 0 : this.taxAmount.hashCode());
        result = (prime * result) + ((this.taxDescription == null) ? 0 : this.taxDescription.hashCode());
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
        if (!(obj instanceof Tax)) {
            return false;
        }
        Tax other = (Tax) obj;
        if (this.taxAmount == null) {
            if (other.taxAmount != null) {
                return false;
            }
        } else if (!this.taxAmount.equals(other.taxAmount)) {
            return false;
        }
        if (this.taxDescription == null) {
            if (other.taxDescription != null) {
                return false;
            }
        } else if (!this.taxDescription.equals(other.taxDescription)) {
            return false;
        }
        return true;
    }

}
