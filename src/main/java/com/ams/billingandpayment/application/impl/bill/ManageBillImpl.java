package com.ams.billingandpayment.application.impl.bill;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.ams.billingandpayment.application.api.commandquery.BillDto;
import com.ams.billingandpayment.application.api.datamapper.BillServiceDataAssembler;
import com.ams.billingandpayment.application.api.service.bill.ManageBill;
import com.ams.billingandpayment.domain.model.bill.Bill;
import com.ams.billingandpayment.domain.model.bill.BillItem;
import com.ams.billingandpayment.domain.model.bill.BillSpecification;
import com.ams.billingandpayment.domain.model.bill.Payment;
import com.ams.billingandpayment.domain.model.bill.TaxPolicy;
import com.ams.billingandpayment.domain.model.servicecatalog.ServicePlan;
import com.ams.billingandpayment.domain.model.servicecatalog.ServicePrice;
import com.ams.billingandpayment.domain.model.servicecatalog.ServiceUsage;
import com.ams.billingandpayment.domain.repository.BillRepository;
import com.ams.billingandpayment.domain.repository.ServicePlanRepository;
import com.ams.billingandpayment.domain.repository.ServiceRepository;
import com.ams.billingandpayment.domain.repository.ServiceUsageRepository;
import com.ams.billingandpayment.domain.service.TaxPolicyAdvisor;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.sharedkernel.exception.DomainException;
import com.ams.sharedkernel.service.ManageMail;
import com.ams.users.domain.model.Person;
import com.ams.users.domain.repository.PersonRepository;

@Transactional(isolation = Isolation.DEFAULT)
@org.springframework.stereotype.Service("ManageBillService")
public class ManageBillImpl implements ManageBill
{

	@Autowired
	private ManageMail				manageMailService;

	@Autowired
	private BillRepository			billRepository;

	@Autowired
	private PersonRepository			personRepository;

	@Autowired
	private ServiceRepository		serviceRepository;

	@Autowired
	private ServicePlanRepository		servicePlanRepository;

	private ServiceUsageRepository	serviceUsageRepository;

	private TaxPolicyAdvisor			serviceTaxAdvisor;

	private Bill					bill;

	private BillServiceDataAssembler	bsdAssmblr	= new BillServiceDataAssembler();

	@Override
	public void billSubscriberForPeriod(Person srvcSubscriber, Period billPeriod, Date billDate, Date billDueDate)
	{
		Bill bill = new Bill(srvcSubscriber, billDate, billDueDate, billPeriod);
		ServicePlan subscbrSrvcPlan = srvcSubscriber.getPersnServiceProfile().getSubscribedSrvcsPlan();
		bill = this.nonUsageCharges(srvcSubscriber, subscbrSrvcPlan, billPeriod);
		bill = this.usageCharges(srvcSubscriber, subscbrSrvcPlan, billPeriod);
	}

	Bill usageCharges(Person srvcSubscriber, ServicePlan subscbrSrvcPlan, Period billPeriod)
	{
		List<ServiceUsage> srvcUsageList = this.serviceUsageRepository.findAllForCustomerWithinPeriod(srvcSubscriber, billPeriod);

		for (ServiceUsage srvcUsage : srvcUsageList)
		{
			/*
			 * ServiceRatePolicyAdvisor: Factory that returns Rating rules
			 * policy object to rate usage/nonusage charges.
			 */
			// ServiceRatePolicy srvcRatePolicy =
			// this.serviceRatePolicyAdvisor.adviseSrvcRatePolicyForUsage(srvcSubscriber,
			// this.bill.getBillPeriod(), srvcUsage, subscbrSrvcPlan);
			TaxPolicy srvcTaxPolicy = this.serviceTaxAdvisor.adviseTaxPolicy(srvcUsage, srvcSubscriber);
			// this.bill.addBillItem(srvcUsage.getSrvc(),
			// srvcUsage.getSrvcUsageQty(),
			// srvcRatePolicy.calculateServiceCharge(subscbrSrvcPlan,
			// srvcUsage, this.bill.getBillDate()),
			// srvcTaxPolicy.calculateTax());
		}

		return null;
	}

	Bill nonUsageCharges(Person srvcSubscriber, ServicePlan subscbrSrvcPlan, Period billPeriod)
	{
		List<ServicePrice> nonUsageServiceRateList = null;// this.servicePlanRepository.findAllServicePricesByCriteria(subscbrSrvcPlan.getSrvcPlanName(),
												// ServicePriceCategory.NON_USAGE.toString());

		for (ServicePrice nonUsageServiceRate : nonUsageServiceRateList)
		{

		}

		return this.bill;
	}

	@Override
	public void deleteBill(long billNumber)
	{
		this.billRepository.deleteBill(billNumber);
	}

	@Override
	public void payBill(Payment pymnt)
	{
		Bill bill = this.billRepository.findBill(pymnt.getPaymntForBill()
												.getBillNumber());
		bill.makePayment(pymnt);
		this.billRepository.updateBill(bill);

		String billPaymntSuccessMessage = this.manageMailService.getMailTemplate("BILL_PAYMENT_SUCCESS", bill.getBillNumber(),
																	pymnt.getPaymntAmount());

		String mailToParty = bill.getBilledPerson().getPersnDetail()
							.getEmailId();
		this.manageMailService.sendMail(mailToParty, BillSpecification.sourceEmailId, billPaymntSuccessMessage);
	}

	public Bill removeBillItem(Bill bill, String srvcCode)
	{

		return null;

	}

	@Override
	public BillDto addBillItems(BillDto billSrvcData)
	{

		try
		{
			this.bill = new Bill();
			/*
			 * for (Map item : billSrvcData.getBillLineItems()) { if (item !=
			 * null && (String) item.get("srvcCode") != null && (Long)
			 * item.get("quantity") != null) { bill.addBillItem((Long)
			 * item.get("quantity"), serviceRepository.findById((String)
			 * item.get("srvcCode"))); } } bill.calculateTotalAmount();
			 */
			this.bill.addBillItems(billSrvcData.getBillLineItems());
			System.out.println("Total Amount:" + this.bill.getBillTotalAmount());

		} catch (DomainException e)
		{
			e.printStackTrace();
		}

		return this.bsdAssmblr.toBillDTO(this.bill);
	}

	@Override
	public BillDto createNewBill(BillDto billSrvcData)
	{
		try
		{

			Person billedPerson = this.personRepository.findById(billSrvcData.getCustomerId());
			this.bill = new Bill(billedPerson, billSrvcData.getBillDate(),
								billSrvcData.getBillDueDate(),
								new Period(billSrvcData.getBillPeriodFromDate(),
											billSrvcData.getBillPeriodToDate()));
			this.bill.addBillItems(billSrvcData.getBillLineItems());

			for (BillItem item : billSrvcData.getBillLineItems())
			{
				if ((item != null) && ((Long) item.getBillItemQuantity() != null) && (item.getBillItemService().getSrvcCode() != null))
				{
					this.bill.addBillItem(item.getBillItemQuantity(), this.serviceRepository.findById(item.getBillItemService().getSrvcCode()));
				}
			}
			this.bill.calculateTotalAmount();

			System.out.println("Bill total Amount:" + this.bill.getBillTotalAmount());
			billSrvcData = this.bsdAssmblr.toBillDTO(this.billRepository.createBill(this.bill));

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return billSrvcData;
	}
}
