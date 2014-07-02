package com.ams.users.domain.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ContactDetail implements Serializable
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

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public String getLandLineNumber()
	{
		return landLineNumber;
	}

	public void setLandLineNumber(String landLineNumber)
	{
		this.landLineNumber = landLineNumber;
	}

	public String getMobileNumber()
	{
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}

}
