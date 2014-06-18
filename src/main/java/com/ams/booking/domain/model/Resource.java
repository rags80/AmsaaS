/**
 *
 */
package com.ams.booking.domain.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Raghavendra Badiger
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "T_RESOURCE")
public class Resource
{
	@EmbeddedId
	private ResourceId	resourceId;
	private String		resourceName;

	Resource()
	{}

	public Resource(ResourceId id, String name)
	{
		this.resourceId = id;
		this.resourceName = name;
	}

	public ResourceId getResourceId()
	{
		return this.resourceId;
	}

	public String getResourceName()
	{
		return this.resourceName;
	}

}
