package com.ams.billingandpayment.ports.adapter.web.struts2.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@ResultPath(value = "/")
@Result(name = "success",location = "/WEB-INF/jsp/index.jsp")
public class IndexAction extends ActionSupport
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 7782583679318984671L;

}
