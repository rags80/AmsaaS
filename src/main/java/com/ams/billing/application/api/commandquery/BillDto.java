package com.ams.billing.application.api.commandquery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ams.billing.domain.model.bill.BillItem;

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

	public long getBillNumber()
	{
		return billNumber;
	}

	public void setBillNumber(long billNumber)
	{
		this.billNumber = billNumber;
	}

	public long getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(long customerId)
	{
		this.customerId = customerId;
	}

	public Date getBillDate()
	{
		return billDate;
	}

	public void setBillDate(Date billDate)
	{
		this.billDate = billDate;
	}

	public Date getBillDueDate()
	{
		return billDueDate;
	}

	public void setBillDueDate(Date billDueDate)
	{
		this.billDueDate = billDueDate;
	}

	public Date getBillPeriodFromDate()
	{
		return billPeriodFromDate;
	}

	public void setBillPeriodFromDate(Date billPeriodFromDate)
	{
		this.billPeriodFromDate = billPeriodFromDate;
	}

	public Date getBillPeriodToDate()
	{
		return billPeriodToDate;
	}

	public void setBillPeriodToDate(Date billPeriodToDate)
	{
		this.billPeriodToDate = billPeriodToDate;
	}

	public Double getBillTotalTax()
	{
		return billTotalTax;
	}

	public void setBillTotalTax(Double billTotalTaxAmount)
	{
		this.billTotalTax = billTotalTaxAmount;
	}

	public Double getBillTotalAmount()
	{
		return billTotalAmount;
	}

	public void setBillTotalAmount(Double billTotalAmount)
	{
		this.billTotalAmount = billTotalAmount;
	}

	public List<BillItem> getBillLineItems()
	{
		return billLineItems;
	}

	public void setBillLineItems(List<BillItem> billLineItems)
	{
		this.billLineItems = billLineItems;
	}

}
