package com.ams.billingandpayment.domain.model.bill;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.ams.sharedkernel.domain.model.measuresandunits.Money;

/**
 * @author Raghavendra Badiger
 * 
 */
@Embeddable
@Access(AccessType.FIELD)
public class Discount implements Serializable
{
	public static final Discount	ZERO_DISCOUNT		= new Discount(Money.ZERO, "Zero! Discount");
	private static final long	serialVersionUID	= 1L;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "DiscountAmount_Amount")),
			@AttributeOverride(name = "currency",column = @Column(name = "DiscountAmount_Currency"))
	})
	private Money				discntAmount;
	@Column(name = "Discount_Description")
	private String				discntDescription;

	Discount()
	{}

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
