package com.ams.billingandpayment.application.api.datamapper;

import java.util.HashMap;
import java.util.Map;

import com.ams.billingandpayment.application.api.servicedata.BillDto;
import com.ams.billingandpayment.domain.model.bill.Bill;
import com.ams.billingandpayment.domain.model.bill.BillItem;

public class BillServiceDataAssembler
{

	public BillDto toBillDTO(Bill bill)
	{
		BillDto bsd = new BillDto();
		if (bill != null)
		{
			bsd.setBillNumber(bill.getBillNumber());
			if (bill.getBilledPerson() != null)
			{
				bsd.setCustomerId(bill.getBilledPerson().getPersnId());
			}
			bsd.setBillDate(bill.getBillDate());
			bsd.setBillDueDate(bill.getBillDueDate());
			bsd.setBillPeriodFromDate(bill.getBillPeriod().getFromDate());
			bsd.setBillPeriodToDate(bill.getBillPeriod().getToDate());
			bsd.setBillTotalTax(bill.getBillTotalTax().doubleValue());
			bsd.setBillTotalAmount(bill.getBillTotalAmount().doubleValue());
			Map<String, Long> bitem = new HashMap<String, Long>();
			for (BillItem bi : bill.getBillItems())
			{
				bitem.put(bi.getBillItemService().getSrvcCode(), bi.getBillItemQuantity());
				bsd.getBillLineItems().add(bi);
			}
		}

		return bsd;

	}

	public Bill toBill(BillDto bsd)
	{
		return null;

	}

}
