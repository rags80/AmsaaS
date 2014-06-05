package com.ams.finance.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AccountDetail implements Serializable
{

	/**
     *
     */
	private static final long	serialVersionUID	= 1L;
	private String				acntDetNotes;

	/**
	 * 
	 */
	public AccountDetail()
	{}

	public String getAcntDetNotes()
	{
		return this.acntDetNotes;
	}

	public void setAcntDetNotes(String notes)
	{
		this.acntDetNotes = notes;
	}

}
