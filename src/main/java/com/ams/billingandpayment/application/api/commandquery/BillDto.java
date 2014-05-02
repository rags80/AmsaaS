package com.ams.billingandpayment.application.api.commandquery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ams.billingandpayment.domain.model.bill.BillItem;

public class BillDto
{
	private long			billNumber;
	private long			customerId;
	private Date			billDate;
	private Date			billDueDate;
	private Date			billPeriodFromDate;
	private Date			billPeriodToDate;
	private Double			billTotalTax		= 0.0;
	private Double			billTotalAmount	= 0.0;
	private List<BillItem>	billLineItems		= new ArrayList<BillItem>();

	public Date getBillDate()
	{
		return billDate;
	}

	public Date getBillDueDate()
	{
		return billDueDate;
	}

	public List<BillItem> getBillLineItems()
	{
		return billLineItems;
	}

	public long getBillNumber()
	{
		return billNumber;
	}

	public Date getBillPeriodFromDate()
	{
		return billPeriodFromDate;
	}

	public Date getBillPeriodToDate()
	{
		return billPeriodToDate;
	}

	public Double getBillTotalAmount()
	{
		return billTotalAmount;
	}

	public Double getBillTotalTax()
	{
		return billTotalTax;
	}

	public long getCustomerId()
	{
		return customerId;
	}

	public void setBillDate(Date billDate)
	{
		this.billDate = billDate;
	}

	public void setBillDueDate(Date billDueDate)
	{
		this.billDueDate = billDueDate;
	}

	public void setBillLineItems(List<BillItem> billLineItems)
	{
		this.billLineItems = billLineItems;
	}

	public void setBillNumber(long billNumber)
	{
		this.billNumber = billNumber;
	}

	public void setBillPeriodFromDate(Date billPeriodFromDate)
	{
		this.billPeriodFromDate = billPeriodFromDate;
	}

	public void setBillPeriodToDate(Date billPeriodToDate)
	{
		this.billPeriodToDate = billPeriodToDate;
	}

	public void setBillTotalAmount(Double billTotalAmount)
	{
		this.billTotalAmount = billTotalAmount;
	}

	public void setBillTotalTax(Double billTotalTaxAmount)
	{
		this.billTotalTax = billTotalTaxAmount;
	}

	public void setCustomerId(long customerId)
	{
		this.customerId = customerId;
	}

}
