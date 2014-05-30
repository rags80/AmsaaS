package com.ams.sharedkernel.domain.model.measuresandunits;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Months;
import org.joda.time.Weeks;
import org.joda.time.Years;

/**
 * @author Raghavendra Badiger
 * 
 */
@Embeddable
@Access(AccessType.FIELD)
public class Period implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	@Column(name = "Period_fromDate")
	private Date				fromDate;

	@Column(name = "Period_toDate")
	private Date				toDate;

	public Period(Date fromDate, Date toDate)
	{
		if (toDate.after(fromDate))
		{
			this.fromDate = fromDate;
			this.toDate = toDate;
		}
		else
		{
			throw new IllegalArgumentException("Period From-Date can't be after To-Date ");
		}
	}

	public static Quantity convertTo(Period period, TimeUnit unit)
	{

		BigDecimal value;

		DateTime frmDate = new DateTime(period.getFromDate());
		DateTime toDate = new DateTime(period.getToDate());

		switch (unit)
		{

		case Hrs:
			value = BigDecimal.valueOf(Hours.hoursBetween(frmDate, toDate).getHours());
			break;
		case Days:
			value = BigDecimal.valueOf(Days.daysBetween(frmDate, toDate).getDays());
			break;
		case Weeks:
			value = BigDecimal.valueOf(Weeks.weeksBetween(frmDate, toDate).getWeeks());
			break;
		case FortNights:
			value = BigDecimal.valueOf(Weeks.weeksBetween(frmDate, toDate).getWeeks() / 2);
			break;
		case Months:
			value = BigDecimal.valueOf(Months.monthsBetween(frmDate, toDate).getMonths());
			break;
		case Years:
			value = BigDecimal.valueOf(Years.yearsBetween(frmDate, toDate).getYears());
			break;
		case QuarterYears:
			value = BigDecimal.valueOf(Years.yearsBetween(frmDate, toDate).getYears() * 4);
			break;
		case HalfYears:
			value = BigDecimal.valueOf(Years.yearsBetween(frmDate, toDate).getYears() * 2);
			break;

		default:
			throw new IllegalArgumentException("Unknown Unit to convert!!");

		}

		return new Quantity(value, unit);

	}

	/*
	 * Accessor Methods
	 */

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
		result = (prime * result) + ((this.fromDate == null) ? 0 : this.fromDate.hashCode());
		result = (prime * result) + ((this.toDate == null) ? 0 : this.toDate.hashCode());
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
		if (!(obj instanceof Period))
		{
			return false;
		}
		Period other = (Period) obj;
		if (this.fromDate == null)
		{
			if (other.fromDate != null)
			{
				return false;
			}
		}
		else if (!this.fromDate.equals(other.fromDate))
		{
			return false;
		}
		if (this.toDate == null)
		{
			if (other.toDate != null)
			{
				return false;
			}
		}
		else if (!this.toDate.equals(other.toDate))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "Period [FromDate:" + this.fromDate + ", ToDate=" + this.toDate + "]";
	}

}
