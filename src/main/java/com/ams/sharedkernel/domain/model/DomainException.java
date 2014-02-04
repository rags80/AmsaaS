package com.ams.sharedkernel.domain.model;

public class DomainException extends RuntimeException
{

	public DomainException()
	{}

	public DomainException(String msg)
	{
		super(msg);
	}

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2463863730457225308L;

}
