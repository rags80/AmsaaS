/**
 *
 */
package com.ams.booking.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author Raghavendra Badiger
 */
@Embeddable
public class PersonId implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private long				personId;

	PersonId()
	{}

	public PersonId(long id)
	{
		this.personId = id;
	}

	public long getPersonId()
	{
		return this.personId;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + (int) (this.personId ^ (this.personId >>> 32));
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
		PersonId other = (PersonId) obj;
		if (this.personId != other.personId)
		{
			return false;
		}
		return true;
	}

}
