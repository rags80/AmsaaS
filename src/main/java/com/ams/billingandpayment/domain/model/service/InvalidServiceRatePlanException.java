package com.ams.billingandpayment.domain.model.service;

import com.ams.sharedkernel.domain.model.DomainException;

public class InvalidServiceRatePlanException extends DomainException
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public InvalidServiceRatePlanException()
	{}

	public InvalidServiceRatePlanException(String msg)
	{
		super(msg);
	}

}
