package com.ams.sharedkernel.domain.model.measuresandunits;

import com.ams.sharedkernel.domain.DomainException;
import com.ams.sharedkernel.domain.model.measuresandunits.exception.InvalidCurrencyException;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Raghavendra Badiger
 */
@Embeddable
public class Money implements Serializable, Comparable<Money> {

    public static final Currency DEFAULT_CURRENCY = Currency.getInstance("INR");
    public static final Money ZERO = new Money(BigDecimal.ZERO, DEFAULT_CURRENCY);
    private static final long serialVersionUID = 1L;
    private BigDecimal amount;
    private Currency currency;

    public Money(BigDecimal amt, Currency curncy) {
        this.amount = amt;
        this.currency = curncy;
    }

    private Money(Money copy) {
        this.amount = copy.amount;
        this.currency = copy.currency;
    }

    public Money copy() {
        return new Money(this);
    }

	/*
     * MONEY DOMAIN FUNCTIONS
	 */

    public Money add(BigDecimal other) {
        this.amount = this.amount.add(other);
        return this;

    }

    public Money add(Money other) throws DomainException {
        if (this.currency.equals(other.getCurrency())) {
            this.amount = this.amount.add(other.getAmount());
        } else {
            throw new InvalidCurrencyException("Currency of Money to be added is different!!");
        }
        ;

        return this;

    }

    public Money divideBy(BigDecimal other) {
        this.amount = this.amount.divide(other);
        return this;

    }

    public Money divideBy(Money other) throws DomainException {
        if (this.currency.equals(other.getCurrency())) {
            this.amount = this.amount.divide(other.getAmount());
        } else {
            throw new InvalidCurrencyException("Currency of Money to be divided is different!!");
        }
        ;

        return this;

    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    @Enumerated(EnumType.STRING)
    public Currency getCurrency() {
        return this.currency;
    }

    public Money multiplyBy(BigDecimal other) {
        this.amount = this.amount.multiply(other);
        return this;
    }

    public Money multiplyBy(Money other) throws DomainException {
        if (this.currency.equals(other.getCurrency())) {
            this.amount = this.amount.multiply(other.getAmount());
        } else {
            throw new InvalidCurrencyException("Currency of Money to be multiplied is different!!");
        }
        ;

        return this;

    }

    public Money subtract(BigDecimal other) {
        this.amount = this.amount.subtract(other);
        return this;

    }

    public Money subtract(Money other) throws DomainException {
        if (this.currency.equals(other.getCurrency())) {
            this.amount = this.amount.subtract(other.getAmount());
        } else {
            throw new InvalidCurrencyException("Currency of Money to be subtracted is different!!");
        }
        ;

        return this;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.amount == null) ? 0 : this.amount.hashCode());
        result = (prime * result) + ((this.currency == null) ? 0 : this.currency.hashCode());
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
        if (!(obj instanceof Money)) {
            return false;
        }
        Money other = (Money) obj;
        if (this.amount == null) {
            if (other.amount != null) {
                return false;
            }
        } else if (!this.amount.equals(other.amount)) {
            return false;
        }
        if (this.currency != other.currency) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.amount + "" + this.currency;
    }

    @Override
    public int compareTo(Money o) {
        if (this.currency.getCurrencyCode().equals(o.getCurrency().getCurrencyCode())) {
            return this.amount.compareTo(o.getAmount());
        } else {
            throw new InvalidCurrencyException();
        }
    }

}
