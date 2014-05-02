package com.ams.users.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private int				flatNumber;
	private String				apartmentName;
	private String				street;
	private String				locality;
	private String				landMark;
	private String				city;
	private String				district;
	private String				state;
	private int				pin;

	public String getApartmentName()
	{
		return this.apartmentName;
	}

	public String getCity()
	{
		return city;
	}

	public String getDistrict()
	{
		return this.district;
	}

	public int getFlatNumber()
	{
		return this.flatNumber;
	}

	public String getLandMark()
	{
		return this.landMark;
	}

	public String getLocality()
	{
		return this.locality;
	}

	public int getPin()
	{
		return this.pin;
	}

	public String getState()
	{
		return this.state;
	}

	public String getStreet()
	{
		return this.street;
	}

	public void setApartmentName(String apartmentName)
	{
		this.apartmentName = apartmentName;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public void setDistrict(String district)
	{
		this.district = district;
	}

	public void setFlatNumber(int flatNumber)
	{
		this.flatNumber = flatNumber;
	}

	public void setLandMark(String landMark)
	{
		this.landMark = landMark;
	}

	public void setLocality(String locality)
	{
		this.locality = locality;
	}

	public void setPin(int pin)
	{
		this.pin = pin;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

}
