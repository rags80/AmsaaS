package com.ams.sharedkernel.domain.model.measuresandunits;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ams.sharedkernel.domain.model.measuresandunits.exception.InvalidUnitException;

/**
 * 
 * @author Raghavendra Badiger
 */
@Embeddable
@Access(AccessType.FIELD)
public class Quantity implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	@Column(name = "Qty_Value")
	private BigDecimal			value;

	@Column(name = "Qty_Unit")
	@Enumerated(EnumType.STRING)
	private TimeUnit			unit;

	public Quantity(BigDecimal qty, TimeUnit qtUnit)
	{
		this.value = qty;
		this.unit = qtUnit;
	}

	private Quantity(Quantity qty)
	{
		this.value = qty.value;
		this.unit = qty.unit;
	}

	public Quantity copy()
	{
		return new Quantity(this);
	}

	public Quantity add(BigDecimal other)
	{
		Quantity copy = this.copy();
		copy.value = other.add(this.value);
		System.out.println("Qty value:" + this.value);
		return copy;

	}

	public Quantity add(Quantity qty) throws InvalidUnitException
	{
		Quantity copy = this.copy();
		if (this.unit.equals(qty.getUnit()))
		{
			copy.value = qty.getValue().add(this.value);
		}
		else
		{
			throw new InvalidUnitException("Unit of Quantity to be added is different!!");
		}

		return copy;

	}

	public Quantity divideBy(BigDecimal other)
	{
		Quantity copy = this.copy();
		copy.value = this.value.divide(other);
		System.out.println("Value:" + this.value);
		return copy;

	}

	public Quantity divideBy(Quantity qty) throws InvalidUnitException
	{
		Quantity copy = this.copy();

		if (this.unit.equals(qty.getUnit()))
		{
			copy.value = this.value.divide(qty.value);
			System.out.println("Value:" + this.value);
		}
		else
		{
			throw new InvalidUnitException("Unit of Quantity to be divided is different!!");
		}

		return copy;

	}

	public Quantity multiplyBy(BigDecimal other)
	{
		Quantity copy = this.copy();
		copy.value = other.multiply(this.value);
		return copy;

	}

	public Quantity multiplyBy(Quantity qty) throws InvalidUnitException
	{
		Quantity copy = this.copy();
		if (this.unit.equals(qty.getUnit()))
		{
			copy.value = qty.value.multiply(this.value);
		}
		else
		{
			throw new InvalidUnitException("Unit of Quantity to be multiplied is different!!");
		}

		return copy;

	}

	public Quantity subtract(BigDecimal other)
	{
		Quantity copy = this.copy();
		copy.value = this.value.subtract(other);
		return copy;

	}

	public Quantity subtract(Quantity qty) throws InvalidUnitException
	{
		Quantity copy = this.copy();
		if (this.unit.equals(qty.getUnit()))
		{
			copy.value = this.value.subtract(qty.value);
		}
		else
		{
			throw new InvalidUnitException("Unit of Quantity to be subtracted is different!!");
		}

		return copy;

	}

	/*
	 * 
	 * ACCESSOR FUNCTIONS
	 */

	public TimeUnit getUnit()
	{
		return this.unit;
	}

	public BigDecimal getValue()
	{
		return this.value;
	}

	@Override
	public String toString()
	{
		return "Quantity [" + this.value + this.unit + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.unit == null) ? 0 : this.unit.hashCode());
		result = (prime * result) + ((this.value == null) ? 0 : this.value.hashCode());
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
		if (!(obj instanceof Quantity))
		{
			return false;
		}
		Quantity other = (Quantity) obj;
		if (this.unit != other.unit)
		{
			return false;
		}
		if (this.value == null)
		{
			if (other.value != null)
			{
				return false;
			}
		}
		else if (!this.value.equals(other.value))
		{
			return false;
		}
		return true;
	}

}
