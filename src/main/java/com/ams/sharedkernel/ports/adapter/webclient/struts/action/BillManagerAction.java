package com.ams.sharedkernel.ports.adapter.webclient.struts.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ams.billingandpayment.application.api.service.bill.ManageBill;
import com.ams.billingandpayment.application.api.servicedata.BillDto;

@ParentPackage("json-default")
@Namespace("/")
@InterceptorRef(value = "json")
public class BillManagerAction extends ActionSupport implements SessionAware
{

	private static final long	serialVersionUID	= 1L;
	@Autowired
	private ManageBill			billingService;
	private Map<String, Object>	session;
	private BillDto			billSrvcData		= new BillDto();

	public BillDto getBillSrvcData()
	{
		return billSrvcData;
	}

	public void setBillSrvcData(BillDto billSrvcData)
	{
		this.billSrvcData = billSrvcData;
	}

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
			System.out.println(billSrvcData.getCustomerId());
			System.out.println(billSrvcData.getBillDate());
			billSrvcData = billingService.addBillItems(billSrvcData);

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
			billSrvcData = billingService.createNewBill(billSrvcData);

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return "success";
	}

}
