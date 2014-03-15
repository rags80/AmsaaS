package com.ams.billing.application.impl.bill;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ams.billing.application.api.commandquery.BillDto;
import com.ams.billing.application.api.datamapper.BillServiceDataAssembler;
import com.ams.billing.application.api.service.bill.ManageBill;
import com.ams.billing.domain.model.bill.Bill;
import com.ams.billing.domain.model.bill.BillItem;
import com.ams.billing.domain.model.bill.BillSpecification;
import com.ams.billing.domain.model.bill.Payment;
import com.ams.billing.domain.model.bill.TaxPolicy;
import com.ams.billing.domain.model.servicecatalog.ServicePlan;
import com.ams.billing.domain.model.servicecatalog.ServicePrice;
import com.ams.billing.domain.model.servicecatalog.ServicePriceCategory;
import com.ams.billing.domain.model.servicecatalog.ServiceUsage;
import com.ams.billing.domain.repository.BillRepository;
import com.ams.billing.domain.repository.ServicePlanRepository;
import com.ams.billing.domain.repository.ServiceRepository;
import com.ams.billing.domain.repository.ServiceUsageRepository;
import com.ams.billing.domain.service.TaxPolicyAdvisor;
import com.ams.sharedkernel.application.service.emailservice.ManageMail;
import com.ams.sharedkernel.domain.model.DomainException;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.usermanagement.domain.model.Person;
import com.ams.usermanagement.domain.repository.PersonRepository;

@Transactional
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

		return this.bill;
	}

	Bill nonUsageCharges(Person srvcSubscriber, ServicePlan subscbrSrvcPlan, Period billPeriod)
	{
		List<ServicePrice> nonUsageServiceRateList = this.servicePlanRepository.findAllServicePricesByCriteria(subscbrSrvcPlan.getSrvcPlanName(), ServicePriceCategory.NON_USAGE.toString());

		for (ServicePrice nonUsageServiceRate : nonUsageServiceRateList)
		{

		}

		return this.bill;
	}

	public void deleteBill(long billNumber)
	{
		this.billRepository.deleteBill(billNumber);
	}

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
