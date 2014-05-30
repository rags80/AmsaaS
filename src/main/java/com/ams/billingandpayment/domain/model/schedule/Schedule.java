/**
 * 
 */
package com.ams.billingandpayment.domain.model.schedule;

import com.ams.sharedkernel.domain.model.measuresandunits.Frequency;

/**
 * @author Raghavendra Badiger
 * 
 */
public class Schedule
{
	enum Month
	{
		JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
	};

	enum WeekDay
	{
		SUN, MON, TUE, WED, THU, FRI, SAT
	};

	private int		min;
	private int		hour;
	private int		day;
	private Month		month;
	private WeekDay	dayOfWeek;
	private Frequency	recurrance;

}
