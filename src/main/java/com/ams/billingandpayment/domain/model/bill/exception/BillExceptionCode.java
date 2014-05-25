/**
 *
 */
package com.ams.billingandpayment.domain.model.bill.exception;

import com.ams.sharedkernel.domain.exception.ExceptionCode;

/**
 * @author Raghavendra Badiger
 */
public enum BillExceptionCode implements ExceptionCode
{

	HEADER_NOT_SET("Bill Header not set!!"),

	HEADER_NULL_ARGUMENT("Bill Header argument can't be null!");

	private String	exceptionDetail;

	BillExceptionCode(String detail)
	{
		this.exceptionDetail = detail;
	}

	@Override
	public String getExceptionDetails()
	{
		return this.exceptionDetail;
	}

}
