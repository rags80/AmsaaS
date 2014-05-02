package com.ams.users.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PersonDetail implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String				landLineNumber;
	private String				mobileNumber;
	private String				emailId;

	public String getEmailId()
	{
		return emailId;
	}

	public String getLandLineNumber()
	{
		return landLineNumber;
	}

	public String getMobileNumber()
	{
		return mobileNumber;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public void setLandLineNumber(String landLineNumber)
	{
		this.landLineNumber = landLineNumber;
	}

	public void setMobileNumber(String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}

}
