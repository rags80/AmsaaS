package com.ams.billingandpayment.domain.model.bill;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.ams.sharedkernel.domain.model.measuresandunits.Money;

@Embeddable
public class Discount implements Serializable
{
	public static final Discount	ZERO_DISCOUNT		= new Discount(Money.ZERO, "Zero! Discount");
	private static final long	serialVersionUID	= 1L;
	private Money				discntAmount;
	private String				discntDescription;

	public Discount(Money discntAmnt, String descrptn)
	{
		this.discntAmount = discntAmnt;
		this.discntDescription = descrptn;
	}

	public Money getDiscntAmount()
	{
		return this.discntAmount;
	}

	public String getDiscntDescription()
	{
		return this.discntDescription;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.discntAmount == null) ? 0 : this.discntAmount.hashCode());
		result = (prime * result) + ((this.discntDescription == null) ? 0 : this.discntDescription.hashCode());
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
		if (!(obj instanceof Discount))
		{
			return false;
		}
		Discount other = (Discount) obj;
		if (this.discntAmount == null)
		{
			if (other.discntAmount != null)
			{
				return false;
			}
		}
		else if (!this.discntAmount.equals(other.discntAmount))
		{
			return false;
		}
		if (this.discntDescription == null)
		{
			if (other.discntDescription != null)
			{
				return false;
			}
		}
		else if (!this.discntDescription.equals(other.discntDescription))
		{
			return false;
		}
		return true;
	}

}
