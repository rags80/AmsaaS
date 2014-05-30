package com.ams.sharedkernel.domain.model.measuresandunits.exception;

import com.ams.sharedkernel.domain.model.exception.DomainException;

/**
 * 
 * @author Raghavendra Badiger
 */
public class InvalidCurrencyException extends DomainException
{

	private static final long	serialVersionUID	= 1L;

	public InvalidCurrencyException()
	{}

	public InvalidCurrencyException(String msg)
	{
		super(msg);
	}

}
