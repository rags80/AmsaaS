/**
 * 
 */
package com.ams.billingandpayment.domain.model.serviceportfolio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

/**
 * @author Raghavendra Badiger
 * 
 */
@Embeddable
public class ServiceUsageEventId implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private String				srvc;
	private long				srvcUser;
	private Date				fromDate;
	private Date				toDate;

	public ServiceUsageEventId(String srvc, long usr, Date frmDate, Date toDate)
	{
		this.srvc = srvc;
		this.srvcUser = usr;
		this.fromDate = frmDate;
		this.toDate = toDate;
	}

	public String getSrvc()
	{
		return this.srvc;
	}

	public void setSrvc(String srvc)
	{
		this.srvc = srvc;
	}

	public long getSrvcUser()
	{
		return this.srvcUser;
	}

	public void setSrvcUser(long srvcUser)
	{
		this.srvcUser = srvcUser;
	}

	public Date getFromDate()
	{
		return this.fromDate;
	}

	public Date getToDate()
	{
		return this.toDate;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
