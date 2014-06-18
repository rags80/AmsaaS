package com.ams.billingandpayment.domain.model.serviceportfolio;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 
 * @author Raghavendra Badiger
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "T_SERVICE")
@Cacheable(true)
public class Service implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	@Id
	private String				srvcCode;

	private String				srvcName;
	private String				srvcDescription;

	/*
	 * @OneToMany(mappedBy = "service",targetEntity = ServicePrice.class)
	 * private Set<ServicePrice> srvcPriceSet;
	 */

	Service()
	{
		super();
	}

	public Service(String code, String name, String description)
	{
		this.srvcCode = code;
		this.srvcName = name;
		this.srvcDescription = description;
		// this.srvcPriceSet = new HashSet<ServicePrice>();
	}

	void updateSrvcPrice(ServicePrice sp)
	{
		// this.srvcPriceSet.add(sp);
	}

	public void modifyServiceDetails(String name, String descrptn)
	{
		this.srvcName = name;
		this.srvcDescription = descrptn;
	}

	/*
	 * 
	 * ACCESSOR FUNCTIONS
	 */

	public String getSrvcCode()
	{
		return this.srvcCode;
	}

	public String getSrvcName()
	{
		return this.srvcName;
	}

	public String getSrvcDescription()
	{
		return this.srvcDescription;
	}

	@JsonIgnore
	/*
	 * public Set<ServicePrice> getSrvcPriceSet() { return this.srvcPriceSet; }
	 */
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
		if (!(obj instanceof Service))
		{
			return false;
		}
		Service other = (Service) obj;
		if (this.srvcCode == null)
		{
			if (other.srvcCode != null)
			{
				return false;
			}
		}
		else if (!this.srvcCode.equals(other.srvcCode))
		{
			return false;
		}
		return true;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.srvcCode == null) ? 0 : this.srvcCode.hashCode());
		return result;
	}

	@Override
	public String toString()
	{
		return "Service [srvcCode=" + this.srvcCode + ", srvcName=" + this.srvcName + ", srvcDescription=" + this.srvcDescription + "]";
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
