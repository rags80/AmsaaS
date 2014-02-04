package com.ams.sharedkernel.domain.model.measureandunits;

import com.ams.sharedkernel.domain.model.DomainException;

public class InvalidCurrencyException extends DomainException
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public InvalidCurrencyException()
	{

	}

	public InvalidCurrencyException(String msg)
	{
		super(msg);
	}

}
