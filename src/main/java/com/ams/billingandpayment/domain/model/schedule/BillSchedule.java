package com.ams.billingandpayment.domain.model.schedule;

import java.util.Date;

import com.ams.sharedkernel.domain.model.measuresandunits.Period;

/**
 * @author Raghavendra Badiger
 */
public class BillSchedule
{

	private Date		billDate;
	private Date		billDueDate;
	private Period		billPeriod;
	private boolean	recurring;

	public BillSchedule(Date bDate, Date bDDate, Period bPeriod, boolean recurng)
	{
		this.billDate = bDate;
		this.billDueDate = bDDate;
		this.billPeriod = bPeriod;
		this.recurring = recurng;
	}

	public Date getBillDate()
	{
		return this.billDate;
	}

	public Date getBillDueDate()
	{
		return this.billDueDate;
	}

	public Period getBillPeriod()
	{
		return this.billPeriod;
	}

	public boolean isRecurring()
	{
		return this.recurring;
	}

}
