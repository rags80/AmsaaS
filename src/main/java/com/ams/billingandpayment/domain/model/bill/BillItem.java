package com.ams.billingandpayment.domain.model.bill;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ams.billingandpayment.domain.model.servicecatalog.Service;

@Entity
@Access(PROPERTY)
@Table(name = "T_BILLITEM")
public class BillItem implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Long				billItemNumber;
	private Bill				bill;
	private BigDecimal			billItemTax		= new BigDecimal(0);
	private BigDecimal			billItemAmount		= new BigDecimal(0);
	private long				billItemQuantity	= 0;
	private Service			billItemService;

	public BillItem()
	{}

	public BillItem(long itemQty, Service service)
	{
		this.setBillItemQuantity(itemQty);
		this.setBillItemService(service);
		this.calculateBillItemAmount();

	}

	public BillItem(Service service)
	{
		this.setBillItemService(service);
		this.calculateBillItemTax();
		this.calculateBillItemAmount();
	}

	private BigDecimal calculateBillItemAmount()
	{
		if ((this.billItemService != null))
		{

			// this.billItemAmount = new
			// BigDecimal(this.billItemService.getSrvcPrice() *
			// this.billItemServiceUsage).add(calculateBillItemTax());
		}
		return billItemAmount;

	}

	private BigDecimal calculateBillItemTax()
	{

		return this.billItemTax;

	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "BillNumber")
	public Bill getBill()
	{
		return bill;
	}

	public BigDecimal getBillItemAmount()
	{
		return billItemAmount;
	}

	@Id
	@GeneratedValue
	public Long getBillItemNumber()
	{
		return billItemNumber;
	}

	public long getBillItemQuantity()
	{
		return billItemQuantity;
	}

	@ManyToOne(optional = false,targetEntity = Service.class)
	@JoinColumn(name = "Service_Code")
	public Service getBillItemService()
	{
		return billItemService;
	}

	public BigDecimal getBillItemTax()
	{
		return billItemTax;
	}

	public void setBill(Bill param)
	{
		this.bill = param;
	}

	public void setBillItemAmount(BigDecimal billItemAmount)
	{
		this.billItemAmount = billItemAmount;
	}

	public void setBillItemNumber(Long id)
	{
		this.billItemNumber = id;
	}

	public void setBillItemQuantity(long billItemQuantity)
	{
		this.billItemQuantity = billItemQuantity;
	}

	public void setBillItemService(Service param)
	{
		this.billItemService = param;
	}

	public void setBillItemTax(BigDecimal billItemTax)
	{
		this.billItemTax = billItemTax;
	}

}
