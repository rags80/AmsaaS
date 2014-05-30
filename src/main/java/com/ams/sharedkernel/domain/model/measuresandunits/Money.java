package com.ams.sharedkernel.domain.model.measuresandunits;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.ams.sharedkernel.domain.model.exception.DomainException;
import com.ams.sharedkernel.domain.model.measuresandunits.exception.InvalidCurrencyException;

/**
 * 
 * @author Raghavendra Badiger
 */

@Embeddable
@Access(AccessType.FIELD)
public class Money implements Serializable, Comparable<Money>
{

	private static final long	serialVersionUID	= 1L;

	@Transient
	public static final Currency	DEFAULT_CURRENCY	= Currency.getInstance("INR");
	@Transient
	public static final Money	ZERO				= new Money(BigDecimal.ZERO, DEFAULT_CURRENCY);
	private BigDecimal			amount;
	private Currency			currency;

	public Money(BigDecimal amt, Currency curncy)
	{
		this.amount = amt;
		this.currency = curncy;
	}

	private Money(Money copy)
	{
		this.amount = copy.amount;
		this.currency = copy.currency;
	}

	/*
	 * MONEY DOMAIN FUNCTIONS
	 */

	public Money copy()
	{
		return new Money(this);
	}

	public Money add(BigDecimal other)
	{
		Money copy = this.copy();
		copy.amount = other.add(this.amount);
		return copy;

	}

	public Money add(Money other) throws DomainException
	{
		if (this.currency.equals(other.getCurrency()))
		{
			Money copy = this.copy();
			copy.amount = other.getAmount().add(this.amount);
			return copy;

		}
		else
		{
			throw new InvalidCurrencyException("Currency of Money to be added is different!!");
		}

	}

	public Money divideBy(BigDecimal other)
	{
		Money copy = this.copy();
		copy.amount = this.amount.divide(other);
		return copy;

	}

	public Money divideBy(Money other) throws DomainException
	{
		if (this.currency.equals(other.getCurrency()))
		{
			Money copy = this.copy();
			copy.amount = this.amount.divide(other.getAmount());
			return copy;
		}
		else
		{
			throw new InvalidCurrencyException("Currency of Money to be divided is different!!");
		}

	}

	public Money multiplyBy(BigDecimal other)
	{
		Money copy = this.copy();
		copy.amount = this.amount.multiply(other);
		return copy;
	}

	public Money multiplyBy(Money other) throws DomainException
	{
		if (this.currency.equals(other.getCurrency()))
		{
			Money copy = this.copy();
			copy.amount = this.amount.multiply(other.getAmount());
			return copy;
		}
		else
		{
			throw new InvalidCurrencyException("Currency of Money to be multiplied is different!!");
		}

	}

	public Money subtract(BigDecimal other)
	{
		Money copy = this.copy();
		copy.amount = this.amount.subtract(other);
		return copy;

	}

	public Money subtract(Money other) throws DomainException
	{
		if (this.currency.equals(other.getCurrency()))
		{
			Money copy = this.copy();
			copy.amount = this.amount.subtract(other.getAmount());
			return copy;
		}
		else
		{
			throw new InvalidCurrencyException("Currency of Money to be subtracted is different!!");
		}

	}

	public BigDecimal getAmount()
	{
		return this.amount;
	}

	@Enumerated(EnumType.STRING)
	public Currency getCurrency()
	{
		return this.currency;
	}

	/*
	 * Equals,Hashcode,toString & CompareTo Functions
	 */

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.amount == null) ? 0 : this.amount.hashCode());
		result = (prime * result) + ((this.currency == null) ? 0 : this.currency.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof Money))
		{
			return false;
		}
		Money other = (Money) obj;
		if (this.amount == null)
		{
			if (other.amount != null)
			{
				return false;
			}
		}
		else if (!this.amount.equals(other.amount))
		{
			return false;
		}
		if (this.currency != other.currency)
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return this.amount + "" + this.currency;
	}

	@Override
	public int compareTo(Money o)
	{
		if (this.currency.getCurrencyCode().equals(o.getCurrency().getCurrencyCode()))
		{
			return this.amount.compareTo(o.getAmount());
		}
		else
		{
			throw new InvalidCurrencyException();
		}
	}

}
