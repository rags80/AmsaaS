/**
 *
 */
package com.ams.sharedkernel.ports.adapter.rest.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ams.sharedkernel.application.api.exception.ServiceException;

/**
 * @author Raghavendra Badiger
 */
@ControllerAdvice
public class GlobalExceptionAdvice
{

	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	RestException handleException(HttpServletRequest req, Exception e)
	{
		return new RestException(req.getRequestURL().toString(), e.getMessage());
	}

}

class RestException
{

	private String	url;
	private String	message;

	public RestException(String url, String e)
	{
		this.url = url;
		this.message = e;
	}

	public String getUrl()
	{
		return this.url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getMessage()
	{
		return this.message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

}
