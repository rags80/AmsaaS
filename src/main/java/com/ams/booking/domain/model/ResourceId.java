/**
 *
 */
package com.ams.booking.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * 
 * @author Raghavendra Badiger
 */
@Embeddable
public class ResourceId implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long				resourceId;

	ResourceId()
	{}

	public ResourceId(long resourceId)
	{
		this.resourceId = resourceId;
	}

	public long getResourceId()
	{
		return this.resourceId;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + (int) (this.resourceId ^ (this.resourceId >>> 32));
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
		if (this.getClass() != obj.getClass())
		{
			return false;
		}
		ResourceId other = (ResourceId) obj;
		if (this.resourceId != other.resourceId)
		{
			return false;
		}
		return true;
	}

}
