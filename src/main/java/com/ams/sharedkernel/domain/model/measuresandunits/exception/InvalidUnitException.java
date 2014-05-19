package com.ams.sharedkernel.domain.model.measuresandunits.exception;

import com.ams.sharedkernel.domain.exception.DomainException;

/**
 * 
 * @author Raghavendra Badiger
 */
public class InvalidUnitException extends DomainException
{

	private static final long	serialVersionUID	= 1L;

	public InvalidUnitException()
	{

	}

	public InvalidUnitException(String msg)
	{
		super(msg);

	}

}
