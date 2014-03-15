package com.ams.sharedkernel.domain.model.measuresandunits;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Rate implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private Money				pricePerUnit;
	private TimeUnit				unitOfMeasure;

	public Rate()
	{}

	public Rate(Money money, TimeUnit perUnit)
	{
		this.pricePerUnit = money;
		this.unitOfMeasure = perUnit;
	}

	public Money getPricePerUnit()
	{
		return pricePerUnit;
	}

	private void setPricePerUnit(Money pricePerUnit)
	{
		this.pricePerUnit = pricePerUnit;
	}

	public TimeUnit getUnitOfMeasure()
	{
		return unitOfMeasure;
	}

	private void setUnitOfMeasure(TimeUnit unitOfMeasure)
	{
		this.unitOfMeasure = unitOfMeasure;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	@Override
	public String toString()
	{
		return "Rate [" + pricePerUnit + "/" + unitOfMeasure + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((pricePerUnit == null) ? 0 : pricePerUnit.hashCode());
		result = (prime * result) + ((unitOfMeasure == null) ? 0 : unitOfMeasure.hashCode());
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
		if (!(obj instanceof Rate))
		{
			return false;
		}
		Rate other = (Rate) obj;
		if (pricePerUnit == null)
		{
			if (other.pricePerUnit != null)
			{
				return false;
			}
		}
		else if (!pricePerUnit.equals(other.pricePerUnit))
		{
			return false;
		}
		if (unitOfMeasure != other.unitOfMeasure)
		{
			return false;
		}
		return true;
	}

}
