package com.ams.billingandpayment.application.impl.bill;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Weeks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ams.billingandpayment.application.api.service.bill.ManageBill;
import com.ams.billingandpayment.application.api.service.bill.ManageBillBatch;
import com.ams.billingandpayment.application.api.servicedata.BillDto;
import com.ams.billingandpayment.domain.model.bill.Bill;
import com.ams.billingandpayment.domain.model.bill.BillItem;
import com.ams.billingandpayment.domain.model.service.ServiceCharge;
import com.ams.billingandpayment.domain.model.service.ServicePlan;
import com.ams.billingandpayment.domain.model.service.ServiceRate;
import com.ams.billingandpayment.domain.model.service.ServiceSubscription;
import com.ams.billingandpayment.domain.model.service.ServiceRate.ServiceRateCategory;
import com.ams.billingandpayment.domain.model.service.ServiceSubscription.Status;
import com.ams.billingandpayment.domain.repository.BillRepository;
import com.ams.billingandpayment.domain.repository.ServicePlanRepository;
import com.ams.billingandpayment.domain.repository.ServiceRepository;
import com.ams.sharedkernel.domain.model.measureandunits.Frequency;
import com.ams.sharedkernel.domain.model.measureandunits.Period;
import com.ams.sharedkernel.domain.model.measureandunits.Unit;
import com.ams.usermanagement.domain.model.Person;
import com.ams.usermanagement.domain.repository.PersonRepository;

@Transactional
@org.springframework.stereotype.Service("BillBatchService")
public class ManageBillBatchImpl implements ManageBillBatch
{

	private final int			noOfDaysBillAdv	= 10;

	@Autowired
	private BillRepository		billRepository;

	@Autowired
	private ServiceRepository	serviceRepository;

	@Autowired
	private ServicePlanRepository	servicePlanRepository;

	@Autowired
	private PersonRepository		personRepository;

	@Autowired
	private ManageBill			manageBill;

	public void createBillForAll(Period billPeriod)
	{

		/*
		 * Search person respository for all ACTIVE subscribers
		 */
		List<Person> activeSrvcSubscriberList = personRepository.findAllByCriteria(Status.ACTIVE.toString());

		/*
		 * If srvcSubscriberList is too big,we can do batch processing of the
		 * same using thread exec service.
		 */

		for (Person srvcSubscriber : activeSrvcSubscriberList)
		{
			Bill bill = new Bill(srvcSubscriber, new Date(), new Date(), billPeriod);

			ServicePlan subscbrSrvcPlan = srvcSubscriber.getPersnServiceProfile().getSubscribedSrvcsPlan();
			List<ServiceRate> usageServiceRateList = servicePlanRepository.findAllServiceRateByCriteria(subscbrSrvcPlan.getSrvcPlanName(), ServiceRateCategory.USAGE_CHARGES.toString());

			for (ServiceRate usageServiceRate : usageServiceRateList)
			{

			}

		}

	}

	Bill nonUsageCharges(Bill bill, ServicePlan subscbrSrvcPlan)
	{

		List<ServiceRate> nonUsageServiceRateList = servicePlanRepository.findAllServiceRateByCriteria(subscbrSrvcPlan.getSrvcPlanName(), ServiceRateCategory.NON_USAGE_CHARGES.toString());
		Person srvcSubscriber = bill.getBilledPerson();

		for (ServiceRate nonUsageServiceRate : nonUsageServiceRateList)
		{
			if (nonUsageServiceRate.srvcSubscrptnSpecification().isSatisfiedBy(srvcSubscriber.getPersnServiceProfile()))
			{
				ServiceCharge srvcCharge = new ServiceCharge();
				srvcCharge.nonUsage(srvcSubscriber, nonUsageServiceRate, bill.getBillPeriod(), new Date());
			};
		}

		return null;

	}

	public Collection<Bill> fetchBills()
	{

		List<Bill> billLst = billRepository.findBillsByPaymentStatus();
		System.out.println(billLst.size());
		return null;
	}

	public void fetchSubs()
	{
		Date billDate = new Date();
		BillDto billDTO = null;
		List<ServiceSubscription> serviceSubList =
				serviceRepository.findServiceSubscription(billDate);
		Iterator<ServiceSubscription> serviceSubscriptionItr =
				serviceSubList.iterator();

		List<BillItem> billItemLst = new ArrayList<BillItem>();
		while (serviceSubscriptionItr.hasNext())
		{
			ServiceSubscription serviceSubscription = serviceSubscriptionItr.next();
			billDTO = new BillDto();

			generateBillItemForServicePlan(billItemLst, serviceSubscription);

			billDTO.setBillLineItems(billItemLst);
			billDTO.setCustomerId(serviceSubscription.getSrvcSubcrptnOfPerson().getPersnId());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(billDate);
			calendar.add(Calendar.DATE, noOfDaysBillAdv);

			billDTO.setBillDueDate(calendar.getTime());
			billDTO.setBillDate(billDate);
			manageBill.createNewBill(billDTO);
			System.out.println(serviceSubscription.
											getSrvcSubcrptnOfPerson().getPersnFirstName()
					+ " Has subscribed for " + serviceSubscription.getSrvcSubcrptnName()
					/*
					 * + " with plan " + serviceplan.getSrvcPlanName()
					 */);
		}

	}

	private void generateBillItemForServicePlan(List<BillItem> billItemLst,
										ServiceSubscription serviceSubscription)
	{
		ServicePlan serviceplan = serviceSubscription.getSubscribedSrvcsPlan();
		Set<ServiceRate> serviceRateSet = serviceplan.getServiceRateSet();
		Iterator<ServiceRate> serviceRateItr = serviceRateSet.iterator();

		BillItem billItem = null;
		while (serviceRateItr.hasNext())
		{
			ServiceRate serviceRate = serviceRateItr.next();
			// ChargeComponent chargeComponent =
			// serviceRate.getSrvcChargeComponent();
			if (serviceRate.getSrvcChargeFrequency().equals(Frequency.MONTHLY))
			{
				billItem = new BillItem();
				billItem.setBillItemAmount(serviceRate.getSrvcChargeRate().getPrice().getAmount());
				billItem.setBillItemQuantity(1);
				billItem.setBillItemService(serviceRate.getService());

			}
			else if (serviceRate.getSrvcChargeFrequency().equals(Frequency.WEEKLY))
			{
				billItem = new BillItem();
				billItem.
						setBillItemAmount(
						serviceRate.getSrvcChargeRate().
									getPrice().getAmount().
									multiply(
											calculateQuantity(
															serviceSubscription.getSrvcSubcrptnStartDate(),
															serviceSubscription.getSrvcSubcrptnEndDate(),
															serviceRate.getSrvcChargeRate().getPerUnit())));
			}
			else if (serviceRate.getSrvcChargeFrequency().equals(Frequency.DAILY))
			{
				billItem = new BillItem();
				billItem.
						setBillItemAmount(
						serviceRate.getSrvcChargeRate().
									getPrice().getAmount().
									multiply(
											calculateQuantity(
															serviceSubscription.getSrvcSubcrptnStartDate(),
															serviceSubscription.getSrvcSubcrptnEndDate(),
															serviceRate.getSrvcChargeRate().getPerUnit()))
						);

			}

			if (billItem != null)
			{
				billItemLst.add(billItem);
			}
		}

	}

	private BigDecimal calculateQuantity(final Date startDate, final Date endDate, final Unit unit)
	{
		DateTime startDateJ = new DateTime(startDate);
		DateTime endDateJ = new DateTime(endDate);

		int quantity = 0;
		if (startDateJ.isBefore(endDateJ))
		{
			if (unit.equals(Unit.Weeks))
			{
				quantity = Weeks.weeksBetween(startDateJ, endDateJ).getWeeks();
			}
			else if (unit.equals(Unit.Days))
			{
				quantity = Days.daysBetween(startDateJ, endDateJ).getDays();
			}

		}

		return quantity == 0 ? BigDecimal.ZERO : (quantity > 0 ? new BigDecimal(quantity) : null);
	}

}
