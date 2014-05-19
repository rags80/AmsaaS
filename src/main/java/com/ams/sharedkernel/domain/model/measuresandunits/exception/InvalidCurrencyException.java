package com.ams.sharedkernel.domain.model.measuresandunits.exception;

import java.util.Currency;

import com.ams.sharedkernel.domain.exception.DomainException;

public class InvalidCurrencyException extends DomainException
{

	/**
     *
     */
	private static final long	serialVersionUID	= 1L;

	public enum CurrencyExceptionCode
	{

		CONVERSION_EXCPTN(Currency.getInstance("INR"), Currency.getInstance("INR"));

		private Currency	current;
		private Currency	other;

		CurrencyExceptionCode(Currency current, Currency other)
		{
			this.current = current;
			this.other = other;
		}

		public String getExceptionMessage()
		{

			return "Currency of type:" + this.current + "can't be transacted with currency:" + this.other + "without currency conversion!!";
		}

	};

	public InvalidCurrencyException()
	{

	}

	public InvalidCurrencyException(String msg)
	{
		super(msg);
	}

}
