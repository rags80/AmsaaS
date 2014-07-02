package com.ams.billingandpayment.ports.adapter.web.struts2.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.ams.billingandpayment.application.api.service.bill.ManageBill;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/")
@InterceptorRef(value = "json")
public class BillManagerAction extends ActionSupport implements SessionAware
{

	private static final long	serialVersionUID	= 1L;
	@Autowired
	private ManageBill			billingService;
	private Map<String, Object>	session;
	private BillDTO			billSrvcData		= new BillDTO();

	public BillDTO getBillSrvcData()
	{
		return this.billSrvcData;
	}

	public void setBillSrvcData(BillDTO billSrvcData)
	{
		this.billSrvcData = billSrvcData;
	}

	@Override
	public void setSession(Map<String, Object> arg0)
	{
		this.session = arg0;

	}

	@Action(value = "/addBillItemsAction",interceptorRefs = { @InterceptorRef(value = "jsonValidationWorkflowStack") },
			results = { @Result(name = "success",type = "json",params = { "root", "billSrvcData"
			}), @Result(name = "input",type = "json",params = { "root", "billSrvcData" }) })
	public String addBillItems()
	{
		try
		{
			System.out.println(this.billSrvcData.getCustomerId());
			System.out.println(this.billSrvcData.getBillDate());
			this.billSrvcData = this.billingService.addBillItems(this.billSrvcData);

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return "success";
	}

	@Action(value = "/submitBillAction",interceptorRefs = { @InterceptorRef(value = "jsonValidationWorkflowStack") },
			results = { @Result(name = "success",type = "json",params = { "root", "billSrvcData"
			}), @Result(name = "input",type = "json",params = { "root", "billSrvcData" }) })
	public String submitBill()
	{
		try
		{
			this.billSrvcData = this.billingService.createNewBill(this.billSrvcData);

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return "success";
	}

}
