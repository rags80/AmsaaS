package com.ams.billingandpayment.application.impl.bill;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.ams.billingandpayment.application.api.commandquery.BillDto;
import com.ams.billingandpayment.application.api.datamapper.BillServiceDataAssembler;
import com.ams.billingandpayment.application.api.service.bill.ManageBill;
import com.ams.billingandpayment.domain.model.bill.Bill;
import com.ams.billingandpayment.domain.model.bill.BillExceptionCode;
import com.ams.billingandpayment.domain.model.bill.BillSpecification;
import com.ams.billingandpayment.domain.model.bill.Payment;
import com.ams.billingandpayment.domain.model.bill.TaxPolicy;
import com.ams.billingandpayment.domain.model.servicecatalog.ServicePlan;
import com.ams.billingandpayment.domain.model.servicecatalog.ServicePrice;
import com.ams.billingandpayment.domain.model.servicecatalog.ServicePriceCategory;
import com.ams.billingandpayment.domain.model.servicecatalog.ServiceUsageEvent;
import com.ams.billingandpayment.domain.model.servicecatalog.policy.ServiceChargePolicy;
import com.ams.billingandpayment.domain.repository.BillRepository;
import com.ams.billingandpayment.domain.repository.ServicePlanRepository;
import com.ams.billingandpayment.domain.repository.ServiceUsageEventRepository;
import com.ams.billingandpayment.domain.service.ServiceChargePolicyAdvisor;
import com.ams.billingandpayment.domain.service.TaxPolicyAdvisor;
import com.ams.sharedkernel.application.api.ManageMail;
import com.ams.sharedkernel.application.api.exception.ServiceException;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.sharedkernel.domain.model.measuresandunits.Quantity;
import com.ams.users.domain.model.Person;
import com.ams.users.domain.repository.PersonRepository;

/**
 * @author Raghavendra Badiger
 * 
 */

@Transactional(isolation = Isolation.DEFAULT)
@org.springframework.stereotype.Service("ManageBillService")
public class ManageBillImpl implements ManageBill
{

	@Autowired
	private BillRepository				billRepository;

	@Autowired
	private PersonRepository				personRepository;

	@Autowired
	private ServicePlanRepository			servicePlanRepository;

	private ServiceUsageEventRepository	serviceUsageEventRepository;

	private ServiceChargePolicyAdvisor		serviceChargePolicyAdvisor;

	private TaxPolicyAdvisor				serviceTaxAdvisor;

	@Autowired
	private ManageMail					manageMailService;

	private BillServiceDataAssembler		bsdAssmblr	= new BillServiceDataAssembler();

	/**
	 * Periodic Billing operations
	 */

	@Override
	public void billSubscriberForPeriod(Person srvcSubscriber, Period billPeriod, Date billDate, Date billDueDate)
	{
		Bill billForPeriod = new BillBuilder().header(srvcSubscriber, billDate, billDueDate, billPeriod).
										nonUsageCharges().
										usageCharges().
										prevBalance().
										getBillInstance();

		this.billRepository.createBill(billForPeriod);
	}

	/**
	 * Ad-hoc Billing operations
	 */

	@Override
	public List<BillDto> getAllBills()
	{
		return Collections.emptyList();
	}

	@Override
	public BillDto createNewBill(BillDto billSrvcData)
	{
		Person billedPerson = this.personRepository.findById(billSrvcData.getCustomerId());
		Bill bill = new Bill(billedPerson, billSrvcData.getBillDate(), billSrvcData.getBillDueDate(),
							new Period(billSrvcData.getBillPeriodFromDate(), billSrvcData.getBillPeriodToDate()));
		bill.addBillItems(billSrvcData.getBillLineItems());

		return this.bsdAssmblr.toBillDTO(this.billRepository.createBill(bill));
	}

	@Override
	public BillDto addBillItems(BillDto billSrvcData)
	{
		Bill bill = new Bill();
		bill.addBillItems(billSrvcData.getBillLineItems());
		return this.bsdAssmblr.toBillDTO(bill);
	}

	@Override
	public void deleteBill(long billNumber)
	{
		this.billRepository.deleteBill(billNumber);
	}

	/**
	 * Payement for Bill operations
	 */
	@Override
	public void payBill(Payment pymnt)
	{
		Bill bill = this.billRepository.findBill(pymnt.getPaymntForBill().getBillNumber());
		bill.makePayment(pymnt);
		this.billRepository.updateBill(bill);
		String billPaymntSuccessMessage = this.manageMailService.getMailTemplate("BILL_PAYMENT_SUCCESS", bill.getBillNumber(),
																	pymnt.getPaymntAmount());

		String mailToParty = bill.getBilledPerson().getPersnDetail().getEmailId();
		this.manageMailService.sendMail(mailToParty, BillSpecification.sourceEmailId, billPaymntSuccessMessage);
	}

	/*
	 * Bill Builder class
	 */
	class BillBuilder
	{

		private Bill	bill;

		BillBuilder()
		{}

		Bill getBillInstance()
		{
			if (this.bill == null)
			{
				throw new ServiceException(BillExceptionCode.HEADER_NOT_SET.getExceptionDetails());
			}
			else
			{
				return this.bill;
			}

		}

		BillBuilder header(Person billedPerson, Date billDate, Date billDueDate, Period billPeriod)
		{
			if ((billedPerson != null) && (billDate != null) && (billDueDate != null) && (billPeriod != null))
			{
				this.bill = new Bill(billedPerson, billDate, billDueDate, billPeriod);
			}
			else
			{
				throw new ServiceException(BillExceptionCode.HEADER_NULL_ARGUMENT.getExceptionDetails());
			}

			return this;
		}

		boolean isHeaderSet()
		{
			if (this.bill != null)
			{
				return true;
			}
			else
			{
				throw new ServiceException(BillExceptionCode.HEADER_NOT_SET.getExceptionDetails());
			}

		}

		BillBuilder nonUsageCharges()
		{

			if (this.isHeaderSet())
			{
				ServicePlan srvcPlan = this.bill.getBilledPerson().getPersnServiceProfile().getSubscribedSrvcsPlan();
				String srvcPlanName = this.bill.getBilledPerson().getPersnServiceProfile().getSubscribedSrvcsPlan().getSrvcPlanName();

				List<ServicePrice> nonUsageServicePriceList = ManageBillImpl.this.servicePlanRepository.findAllServicePricesByCriteria(srvcPlanName, ServicePriceCategory.NON_USAGE.toString());

				for (ServicePrice nonUsageServicePrice : nonUsageServicePriceList)
				{

					/*
					 * ServiceChargePolicyAdvisor: Factory that returns
					 * Rating rules policy object to calculate
					 * usage/nonusage charges.
					 */

					ServiceChargePolicy srvcChargePolicy = ManageBillImpl.this.serviceChargePolicyAdvisor.adviseSrvcChargePolicyForNonUsage(this.bill.getBilledPerson(),
																													this.bill.getBillPeriod(),
																													srvcPlan,
																													nonUsageServicePrice);
					TaxPolicy srvcTaxPolicy = ManageBillImpl.this.serviceTaxAdvisor.adviseTaxPolicy(nonUsageServicePrice.getService(), this.bill.getBilledPerson());

					this.bill.addBillItem(nonUsageServicePrice.getService(),
										Quantity.quantify(this.bill.getBillPeriod(), nonUsageServicePrice.getSrvcUnitOfMeasure()),
										srvcChargePolicy.calculateServiceCharge(nonUsageServicePrice, this.bill.getBillDate(), this.bill.getBillPeriod()),
										srvcTaxPolicy.calculateTax());

				}
			}
			return this;

		}

		BillBuilder prevBalance()
		{
			if (this.isHeaderSet())
			{}

			return this;

		}

		BillBuilder usageCharges()
		{
			if (this.isHeaderSet())
			{

				List<ServiceUsageEvent> srvcUsageEventList = ManageBillImpl.this.serviceUsageEventRepository.findAllForCustomerWithinPeriod(this.bill.getBilledPerson(), this.bill.getBillPeriod());

				for (ServiceUsageEvent srvcUsageEvent : srvcUsageEventList)
				{
					/*
					 * ServiceChargePolicyAdvisor: Factory that returns
					 * Rating rules policy object to calculate
					 * usage/nonusage charges.
					 */

					ServicePlan subscbrSrvcPlan = this.bill.getBilledPerson().getPersnServiceProfile().getSubscribedSrvcsPlan();
					ServicePrice srvcPrice = ManageBillImpl.this.servicePlanRepository.findServicePriceByCriteria(subscbrSrvcPlan.getSrvcPlanName(), srvcUsageEvent.getSrvc().getSrvcCode());
					ServiceChargePolicy srvcChargePolicy = ManageBillImpl.this.serviceChargePolicyAdvisor.adviseSrvcChargePolicyForUsage(this.bill.getBilledPerson(), this.bill.getBillPeriod(), srvcUsageEvent, subscbrSrvcPlan);
					TaxPolicy srvcTaxPolicy = ManageBillImpl.this.serviceTaxAdvisor.adviseTaxPolicy(srvcUsageEvent, this.bill.getBilledPerson());

					this.bill.addBillItem(srvcUsageEvent.getSrvc(), Quantity.quantify(srvcUsageEvent.getSrvcUsagePeriod(), srvcPrice.getSrvcUnitOfMeasure()), srvcChargePolicy.calculateServiceCharge(srvcUsageEvent, srvcPrice, this.bill.getBillDate(), this.bill.getBillPeriod()),
										srvcTaxPolicy.calculateTax());
				}
			}
			return this;

		}
	}

}
