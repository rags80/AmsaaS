package com.ams.sharedkernel.domain.model.measuresandunits;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Period implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	@Column(name = "Period_fromDate")
	@Temporal(TemporalType.TIMESTAMP)
	private DateTime			fromDate;

	@Column(name = "Period_toDate")
	@Temporal(TemporalType.TIMESTAMP)
	private DateTime			toDate;

	public Period(DateTime fromDate, DateTime toDate)
	{
		if (toDate.isAfter(fromDate))
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

		switch (unit)
		{

		case Hrs:
			value = BigDecimal.valueOf(Hours.hoursBetween(period.getFromDate(), period.getToDate()).getHours());
			break;
		case Days:
			value = BigDecimal.valueOf(Days.daysBetween(period.getFromDate(), period.getToDate()).getDays());
			break;
		case Weeks:
			value = BigDecimal.valueOf(Weeks.weeksBetween(period.getFromDate(), period.getToDate()).getWeeks());
			break;
		case FortNights:
			value = BigDecimal.valueOf(Weeks.weeksBetween(period.getFromDate(), period.getToDate()).getWeeks() / 2);
			break;
		case Months:
			value = BigDecimal.valueOf(Months.monthsBetween(period.getFromDate(), period.getToDate()).getMonths());
			break;
		case Years:
			value = BigDecimal.valueOf(Years.yearsBetween(period.getFromDate(), period.getToDate()).getYears());
			break;
		case QuarterYears:
			value = BigDecimal.valueOf(Years.yearsBetween(period.getFromDate(), period.getToDate()).getYears() * 4);
			break;
		case HalfYears:
			value = BigDecimal.valueOf(Years.yearsBetween(period.getFromDate(), period.getToDate()).getYears() * 2);
			break;

		default:
			throw new IllegalArgumentException("Unknown Unit to convert!!");

		}

		return new Quantity(value, unit);

	}

	/*
	 * Accessor Methods
	 */

	public DateTime getFromDate()
	{
		return this.fromDate;
	}

	public DateTime getToDate()
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
