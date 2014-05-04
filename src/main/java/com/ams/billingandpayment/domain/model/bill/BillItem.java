package com.ams.billingandpayment.domain.model.bill;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.ams.billingandpayment.domain.model.bill.policy.DiscountPolicy;
import com.ams.billingandpayment.domain.model.bill.policy.TaxPolicy;
import com.ams.billingandpayment.domain.model.servicecatalog.Service;
import com.ams.billingandpayment.domain.model.servicecatalog.ServicePrice;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.sharedkernel.domain.model.measuresandunits.Quantity;

@Embeddable
public class BillItem implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private ServicePrice		servicePrice;
	private Money				grossAmount;
	private Tax		    		itemTax;
	private Discount			itemDiscount;
	private Money				netAmount;
	private Quantity			quantity;

	public BillItem(ServicePrice srvcPrice, Quantity qty, TaxPolicy itemTaxPolicy,
					DiscountPolicy itemDscntPolicy)
	{
		if ((srvcPrice != null) && (qty != null) && (itemTaxPolicy != null) && (itemDscntPolicy != null))
		{
			this.servicePrice = srvcPrice;
			this.quantity = qty;
			this.calculateItemNetAmount(srvcPrice, qty, itemTaxPolicy, itemDscntPolicy);
		}
		else
		{
			throw new NullPointerException();
		}

	}

	/*
	 * BillItem Accessor methods
	 */
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public void increaseQuantity(Quantity qty, TaxPolicy itemTaxPolicy, DiscountPolicy itemDscntPolicy)
	{
		this.quantity.add(qty);
		this.calculateItemNetAmount(this.servicePrice, this.quantity, itemTaxPolicy, itemDscntPolicy);

	}

	private void calculateItemNetAmount(ServicePrice srvcPrice, Quantity qty, TaxPolicy taxPolicy, DiscountPolicy disntPolicy)
	{
		Money price = srvcPrice.getSrvcPricePerUnit().copy();
		this.grossAmount = price.multiplyBy(qty.getValue());
		this.itemTax = taxPolicy.calculateTax(this.grossAmount);
		this.itemDiscount = disntPolicy.calculateDiscount(this.grossAmount);
		this.netAmount = this.grossAmount.copy().add(this.itemTax.getTaxAmount()).subtract(this.itemDiscount.getDiscntAmount());
	}

	public Service getItemService()
	{
		return this.servicePrice.getService();
	}

	public ServicePrice getServicePrice()
	{
		return this.servicePrice;
	}

	public String servicePrice()
	{
		return this.servicePrice.getSrvcPricePerUnit().toString() + "/" + this.servicePrice.getSrvcUnitOfMeasure().toString();
	}

	public Money getGrossAmount()
	{
		return this.grossAmount;
	}

	public Tax getItemTax()
	{
		return this.itemTax;
	}

	public Discount getItemDiscount()
	{
		return this.itemDiscount;
	}

	public Money getNetAmount()
	{
		return this.netAmount;
	}

	public Quantity getQuantity()
	{
		return this.quantity;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.servicePrice == null) ? 0 : this.servicePrice.hashCode());
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
		if (!(obj instanceof BillItem))
		{
			return false;
		}
		BillItem other = (BillItem) obj;
		if (this.servicePrice == null)
		{
			if (other.servicePrice != null)
			{
				return false;
			}
		}
		else if (!this.servicePrice.equals(other.servicePrice))
		{
			return false;
		}
		return true;
	}

}
