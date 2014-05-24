package com.ams.billingandpayment.domain.model.bill;

import java.io.Serializable;

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
public class Tax implements Serializable
{
	public static final Tax		ZERO_TAX			= new Tax(Money.ZERO, "Zero! Tax");
	private static final long	serialVersionUID	= 1L;

	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "TaxAmount_Amount")),
			@AttributeOverride(name = "currency",column = @Column(name = "TaxAmount_Currency"))
	})
	@Embedded
	private Money				taxAmount;

	@Column(name = "Tax_Description")
	private String				taxDescription;

	public Tax(Money taxAmount, String description)
	{
		this.taxAmount = taxAmount;
		this.taxDescription = description;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public Money getTaxAmount()
	{
		return this.taxAmount;
	}

	public String getTaxDescription()
	{
		return this.taxDescription;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.taxAmount == null) ? 0 : this.taxAmount.hashCode());
		result = (prime * result) + ((this.taxDescription == null) ? 0 : this.taxDescription.hashCode());
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
		if (!(obj instanceof Tax))
		{
			return false;
		}
		Tax other = (Tax) obj;
		if (this.taxAmount == null)
		{
			if (other.taxAmount != null)
			{
				return false;
			}
		}
		else if (!this.taxAmount.equals(other.taxAmount))
		{
			return false;
		}
		if (this.taxDescription == null)
		{
			if (other.taxDescription != null)
			{
				return false;
			}
		}
		else if (!this.taxDescription.equals(other.taxDescription))
		{
			return false;
		}
		return true;
	}

}
