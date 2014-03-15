package com.ams.billing.domain.model.servicecatalog;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author Raghavendra Badiger
 * 
 */
@Entity
@Access(PROPERTY)
@Table(name = "T_SERVICE")
public class Service implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String				srvcCode;
	private String				srvcName;
	private String				srvcDescription;
	private Set<ServicePrice>	srvcPriceSet;

	public Service()
	{}

	public Service(String code, String name, String description)
	{
		this.srvcCode = code;
		this.srvcName = name;
		this.srvcDescription = description;
		this.srvcPriceSet = new HashSet<ServicePrice>();
	}

	/**
	 * @param string
	 * @param string2
	 */
	public void modifyServiceDetails(String name, String descrptn)
	{
		this.srvcName = name;
		this.srvcDescription = descrptn;
	}

	/*
	 * ACCESSOR & MUTATORS
	 */

	@Id
	public String getSrvcCode()
	{
		return this.srvcCode;
	}

	private void setSrvcCode(String srvcCode)
	{
		this.srvcCode = srvcCode;
	}

	public String getSrvcName()
	{
		return this.srvcName;
	}

	private void setSrvcName(String srvcName)
	{
		this.srvcName = srvcName;
	}

	public String getSrvcDescription()
	{
		return this.srvcDescription;
	}

	private void setSrvcDescription(String srvcDescription)
	{
		this.srvcDescription = srvcDescription;
	}

	@OneToMany(mappedBy = "service",targetEntity = ServicePrice.class)
	@JsonIgnore
	public Set<ServicePrice> getSrvcPriceSet()
	{
		return this.srvcPriceSet;
	}

	@JsonIgnore
	private void setSrvcPriceSet(Set<ServicePrice> srvcPriceSet)
	{
		this.srvcPriceSet = srvcPriceSet;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	@Override
	public String toString()
	{
		return "Service [srvcCode=" + this.srvcCode + ", srvcName=" + this.srvcName + ", srvcDescription=" + this.srvcDescription + "]";
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

}
