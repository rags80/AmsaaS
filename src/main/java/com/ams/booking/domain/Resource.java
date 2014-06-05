/**
 *
 */
package com.ams.booking.domain;

/**
 * @author Raghavendra Badiger
 */
public class Resource
{
	private long	resourceId;
	private String	resourceName;

	public long getResourceId()
	{
		return this.resourceId;
	}

	public void setResourceId(long resourceId)
	{
		this.resourceId = resourceId;
	}

	public String getResourceName()
	{
		return this.resourceName;
	}

	public void setResourceName(String resourceName)
	{
		this.resourceName = resourceName;
	}

}
