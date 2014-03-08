package com.ams.sales.application.impl.bill;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ams.sales.application.api.datamapper.BillServiceDataAssembler;
import com.ams.sales.application.api.service.bill.ManageBill;
import com.ams.sales.application.api.servicedata.BillDto;
import com.ams.sales.domain.model.bill.Bill;
import com.ams.sales.domain.model.bill.BillItem;
import com.ams.sales.domain.model.bill.BillSpecification;
import com.ams.sales.domain.model.bill.Payment;
import com.ams.sales.domain.model.bill.TaxPolicy;
import com.ams.sales.domain.model.servicecatalog.ServiceCharge;
import com.ams.sales.domain.model.servicecatalog.ServicePlan;
import com.ams.sales.domain.model.servicecatalog.ServiceRate;
import com.ams.sales.domain.model.servicecatalog.ServiceRateCategory;
import com.ams.sales.domain.model.servicecatalog.ServiceRatePolicy;
import com.ams.sales.domain.model.servicecatalog.ServiceUsage;
import com.ams.sales.domain.repository.BillRepository;
import com.ams.sales.domain.repository.ServicePlanRepository;
import com.ams.sales.domain.repository.ServiceRepository;
import com.ams.sales.domain.repository.ServiceUsageRepository;
import com.ams.sales.domain.service.ServiceRatePolicyAdvisor;
import com.ams.sales.domain.service.TaxPolicyAdvisor;
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

	private ServiceRatePolicyAdvisor	serviceRatePolicyAdvisor;

	private TaxPolicyAdvisor			serviceTaxAdvisor;

	private Bill					bill;

	private BillServiceDataAssembler	bsdAssmblr	= new BillServiceDataAssembler();

	public void billSubscriberForPeriod(Person srvcSubscriber, Period billPeriod, Date billDate, Date billDueDate)
	{
		Bill bill = new Bill(srvcSubscriber, billDate, billDueDate, billPeriod);
		ServicePlan subscbrSrvcPlan = srvcSubscriber.getPersnServiceProfile().getSubscribedSrvcsPlan();
		bill = this.nonUsageCharges(bill, subscbrSrvcPlan);
		bill = this.usageCharges(bill, subscbrSrvcPlan);
	}

	Bill usageCharges(Bill bill, ServicePlan subscbrSrvcPlan)
	{
		Person srvcSubscriber = bill.getBilledPerson();
		List<ServiceUsage> srvcUsageList = serviceUsageRepository.findAllForCustomerWithinPeriod(srvcSubscriber, bill.getBillPeriod());

		for (ServiceUsage srvcUsage : srvcUsageList)
		{
			/*
			 * ServiceRatePolicyAdvisor: Factory that returns Rating rules
			 * policy object to rate usage/nonusage charges.
			 */
			ServiceRatePolicy srvcRatePolicy = serviceRatePolicyAdvisor.adviseSrvcRatePolicyForUsage(srvcSubscriber, bill.getBillPeriod(), srvcUsage, subscbrSrvcPlan);
			TaxPolicy srvcTaxPolicy = serviceTaxAdvisor.adviseTaxPolicy(srvcUsage, srvcSubscriber);
			bill.addBillItem(srvcUsage.getSrvc(), srvcUsage.getSrvcUsageQty(), srvcRatePolicy.calculateServiceCharge(subscbrSrvcPlan, srvcUsage, bill.getBillDate()), srvcTaxPolicy.calculateTax());
		}

		return bill;
	}

	Bill nonUsageCharges(Bill bill, ServicePlan subscbrSrvcPlan)
	{
		List<ServiceRate> nonUsageServiceRateList = servicePlanRepository.findAllServiceRateByCriteria(subscbrSrvcPlan.getSrvcPlanName(), ServiceRateCategory.NON_USAGE.toString());
		Person srvcSubscriber = bill.getBilledPerson();

		for (ServiceRate nonUsageServiceRate : nonUsageServiceRateList)
		{
			if (nonUsageServiceRate.srvcSubscrptnSpecification().isSatisfiedBy(srvcSubscriber.getPersnServiceProfile()))
			{
				ServiceCharge srvcCharge = new ServiceCharge();
				srvcCharge.nonUsage(srvcSubscriber, nonUsageServiceRate, bill.getBillPeriod(), new Date());
				bill.addBillItem(srvcCharge);
			};
		}

		return bill;
	}

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
				if ((item != null) && ((Long) item.getBillItemQuantity() != null) && (item.getBillItemService().getSrvcCode() != null))
				{
					bill.addBillItem(item.getBillItemQuantity(), serviceRepository.findById(item.getBillItemService().getSrvcCode()));
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
