/**
 * 
 */
package com.ams.users.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 
 * @author Raghavendra Badiger
 */
@Embeddable
public class LoginDetails implements Serializable
{
	private static final long	serialVersionUID	= 1877779178530043950L;
	private String				loginId;
	private String				loginPassword;

	LoginDetails()
	{}

	public LoginDetails(String id, String passwd)
	{
		this.loginId = id;
		this.loginPassword = passwd;
	}

	public String getLoginId()
	{
		return this.loginId;
	}

	public String getLoginPassword()
	{
		return this.loginPassword;
	}

}
