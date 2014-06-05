package com.ams.billingandpayment.domain.model.bill;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.ams.billingandpayment.domain.model.bill.policy.DiscountPolicy;
import com.ams.billingandpayment.domain.model.bill.policy.TaxPolicy;
import com.ams.billingandpayment.domain.model.serviceportfolio.Service;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.sharedkernel.domain.model.measuresandunits.Quantity;

/**
 * @author Raghavendra Badiger
 * 
 */
@Embeddable
@Access(AccessType.FIELD)
public class BillItem implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "SrvcPlan_Name",referencedColumnName = "ServicePlan_Name"), @JoinColumn(name = "Srvc_Code",referencedColumnName = "Service_Code") })
	private ServicePrice		servicePrice;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "ItemGrossAmount_Amount")),
			@AttributeOverride(name = "currency",column = @Column(name = "ItemGrossAmount_Currency"))
	})
	private Money				grossAmount;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "taxDescription",column = @Column(name = "ItemTax_Description")),
			@AttributeOverride(name = "taxAmount.amount",column = @Column(name = "ItemTax_Amount")),
			@AttributeOverride(name = "taxAmount.currency",column = @Column(name = "ItemTax_Currency"))
	})
	private Tax				itemTax;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "discntDescription",column = @Column(name = "ItemDiscnt_Description")),
			@AttributeOverride(name = "discntAmount.amount",column = @Column(name = "ItemDiscnt_Amount")),
			@AttributeOverride(name = "discntAmount.currency",column = @Column(name = "ItemDiscnt_Currency"))
	})
	private Discount			itemDiscount;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "ItemNetAmount_Amount")),
			@AttributeOverride(name = "currency",column = @Column(name = "ItemNetAmount_Currency"))
	})
	private Money				netAmount;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "value",column = @Column(name = "ItemQty_Value")),
			@AttributeOverride(name = "unit",column = @Column(name = "ItemQty_Unit"))
	})
	private Quantity			quantity;

	BillItem()
	{}

	public BillItem(ServicePrice srvcPrice, Quantity qty, TaxPolicy itemTaxPolicy,
					DiscountPolicy itemDscntPolicy)
	{
		if ((srvcPrice != null) && (qty != null) && (itemTaxPolicy != null) && (itemDscntPolicy != null))
		{
			this.servicePrice = srvcPrice;
			this.quantity = qty;
			System.out.println("Item Qty:" + this.quantity);
			System.out.println("Service Price:" + this.servicePrice());
			this.calculateItemNetAmount(srvcPrice, qty, itemTaxPolicy, itemDscntPolicy);

		}
		else
		{
			throw new NullPointerException("BillItem arguements shouldn't be null!!");
		}

	}

	void increaseQuantity(Quantity qty, TaxPolicy itemTaxPolicy, DiscountPolicy itemDscntPolicy)
	{
		System.out.println("Increase qty");
		this.quantity = this.quantity.add(qty);
		System.out.println("BillItem Qty:" + this.getQuantity());
		this.calculateItemNetAmount(this.servicePrice, this.quantity, itemTaxPolicy, itemDscntPolicy);

	}

	private void calculateItemNetAmount(ServicePrice srvcPrice, Quantity qty, TaxPolicy taxPolicy, DiscountPolicy disntPolicy)
	{
		this.grossAmount = srvcPrice.getSrvcPricePerUnit().multiplyBy(qty.getValue());
		System.out.println("Item gross amount:" + this.grossAmount);
		this.itemTax = taxPolicy.calculateTax(this.grossAmount);
		System.out.println("Item tax amount:" + this.itemTax.getTaxAmount());
		this.itemDiscount = disntPolicy.calculateDiscount(this.grossAmount);
		System.out.println("Item discount amount:" + this.itemDiscount.getDiscntAmount());
		this.netAmount = (this.grossAmount.subtract(this.itemDiscount.getDiscntAmount())).add(this.itemTax.getTaxAmount());
		System.out.println("Item net amount:" + this.netAmount);
	}

	/*
	 * BillItem Accessor methods
	 */

	public static long getSerialversionuid()
	{
		return serialVersionUID;
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
