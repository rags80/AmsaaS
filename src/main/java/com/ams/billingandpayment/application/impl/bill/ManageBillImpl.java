package com.ams.billingandpayment.application.impl.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ams.billingandpayment.application.api.datamapper.BillServiceDataAssembler;
import com.ams.billingandpayment.application.api.service.bill.ManageBill;
import com.ams.billingandpayment.application.api.servicedata.BillDto;
import com.ams.billingandpayment.domain.model.bill.Bill;
import com.ams.billingandpayment.domain.model.bill.BillItem;
import com.ams.billingandpayment.domain.model.bill.BillSpecification;
import com.ams.billingandpayment.domain.model.bill.Payment;
import com.ams.billingandpayment.domain.repository.BillRepository;
import com.ams.billingandpayment.domain.repository.ServiceRepository;
import com.ams.sharedkernel.application.service.emailservice.ManageMail;
import com.ams.sharedkernel.domain.model.DomainException;
import com.ams.sharedkernel.domain.model.measureandunits.Period;
import com.ams.usermanagement.domain.model.Person;
import com.ams.usermanagement.domain.repository.PersonRepository;

@Transactional
@org.springframework.stereotype.Service("ManageBillService")
public class ManageBillImpl implements ManageBill
{

	@Autowired
	private PersonRepository			personRepository;
	@Autowired
	private BillRepository			billRepository;
	@Autowired
	private ManageMail				manageMailService;
	@Autowired
	private ServiceRepository		serviceRepository;

	private Bill					bill;

	private BillServiceDataAssembler	bsdAssmblr	= new BillServiceDataAssembler();

	public void deleteBill(long billNumber)
	{
		billRepository.deleteBill(billNumber);
	}

	public void payBill(Payment pymnt)
	{
		Bill bill = this.billRepository.findBill(pymnt.getPaymntForBill()
												.getBillNumber());
		bill.makePayment(pymnt);
		this.billRepository.updateBill(bill);

		String billPaymntSuccessMessage = manageMailService.getMailTemplate("BILL_PAYMENT_SUCCESS", bill.getBillNumber(),
																pymnt.getPaymntAmount());

		String mailToParty = bill.getBilledPerson().getPersnDetail()
							.getEmailId();
		manageMailService.sendMail(mailToParty, BillSpecification.sourceEmailId, billPaymntSuccessMessage);
	}

	public Bill removeBillItem(Bill bill, String srvcCode)
	{

		return null;

	}

	public BillDto addBillItems(BillDto billSrvcData)
	{

		try
		{
			bill = new Bill();
			/*
			 * for (Map item : billSrvcData.getBillLineItems()) { if (item !=
			 * null && (String) item.get("srvcCode") != null && (Long)
			 * item.get("quantity") != null) { bill.addBillItem((Long)
			 * item.get("quantity"), serviceRepository.findById((String)
			 * item.get("srvcCode"))); } } bill.calculateTotalAmount();
			 */
			bill.addBillItems(billSrvcData.getBillLineItems());
			System.out.println("Total Amount:" + bill.getBillTotalAmount());

		} catch (DomainException e)
		{
			e.printStackTrace();
		}

		return bsdAssmblr.toBillDTO(bill);
	}

	public BillDto createNewBill(BillDto billSrvcData)
	{
		try
		{

			Person billedPerson = personRepository.findById(billSrvcData.getCustomerId());
			bill = new Bill(billedPerson, billSrvcData.getBillDate(),
							billSrvcData.getBillDueDate(),
							new Period(billSrvcData.getBillPeriodFromDate(),
										billSrvcData.getBillPeriodToDate()));
			bill.addBillItems(billSrvcData.getBillLineItems());

			for (BillItem item : billSrvcData.getBillLineItems())
			{
				if ((item != null) && ((Long) item.getBillItemQuantity() != null) && ((String)
						item.getBillItemService().getSrvcCode() != null))
				{
					bill.addBillItem((Long)
									item.getBillItemQuantity(), serviceRepository.findById((String)
																		item.getBillItemService().getSrvcCode()));
				}
			}
			bill.calculateTotalAmount();

			System.out.println("Bill total Amount:" + bill.getBillTotalAmount());
			billSrvcData = bsdAssmblr.toBillDTO(billRepository.createBill(bill));

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return billSrvcData;
	}
}
