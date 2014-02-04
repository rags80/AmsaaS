package com.ams.sharedkernel.domain.model.measureandunits;

import com.ams.sharedkernel.domain.model.DomainException;

public class InvalidUnitException extends DomainException
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public InvalidUnitException()
	{

	}

	public InvalidUnitException(String msg)
	{
		super(msg);

	}

}
