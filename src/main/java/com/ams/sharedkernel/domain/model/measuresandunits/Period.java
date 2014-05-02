package com.ams.sharedkernel.domain.model.measuresandunits;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Period implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Date				fromDate;
	private Date				toDate;

	public Period()
	{}

	public Period(Date fromDate, Date toDate)
	{
		this.setFromDate(fromDate);
		this.setToDate(toDate);
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
		if (!(obj instanceof Period))
		{
			return false;
		}
		Period other = (Period) obj;
		if (fromDate == null)
		{
			if (other.fromDate != null)
			{
				return false;
			}
		}
		else if (!fromDate.equals(other.fromDate))
		{
			return false;
		}
		if (toDate == null)
		{
			if (other.toDate != null)
			{
				return false;
			}
		}
		else if (!toDate.equals(other.toDate))
		{
			return false;
		}
		return true;
	}

	public Date getFromDate()
	{
		return this.fromDate;
	}

	public Date getToDate()
	{
		return this.toDate;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((fromDate == null) ? 0 : fromDate.hashCode());
		result = (prime * result) + ((toDate == null) ? 0 : toDate.hashCode());
		return result;
	}

	private void setFromDate(Date fromDate)
	{
		this.fromDate = fromDate;
	}

	private void setToDate(Date toDate)
	{
		this.toDate = toDate;
	}

	@Override
	public String toString()
	{
		return "Period [FromDate:" + fromDate + ", ToDate=" + toDate + "]";
	}

}
