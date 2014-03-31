package com.ams.billingandpayment.application.impl.bill;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Weeks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ams.billingandpayment.application.api.service.bill.ManageBill;
import com.ams.billingandpayment.application.api.service.bill.ManageBillBatch;
import com.ams.billingandpayment.domain.model.bill.Bill;
import com.ams.billingandpayment.domain.model.bill.BillItem;
import com.ams.billingandpayment.domain.model.servicecatalog.ServiceSubscription;
import com.ams.billingandpayment.domain.repository.BillRepository;
import com.ams.billingandpayment.domain.repository.ServicePlanRepository;
import com.ams.billingandpayment.domain.repository.ServiceRepository;
import com.ams.sharedkernel.domain.model.measuresandunits.TimeUnit;
import com.ams.users.domain.repository.PersonRepository;

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

	public Collection<Bill> fetchBills()
	{

		List<Bill> billLst = this.billRepository.findBillsByPaymentStatus();
		System.out.println(billLst.size());
		return null;
	}

	public void fetchSubs()
	{
		/*
		 * Date billDate = new Date(); BillDto billDTO = null;
		 * List<ServiceSubscription> serviceSubList =
		 * this.serviceRepository.findServiceSubscription(billDate);
		 * Iterator<ServiceSubscription> serviceSubscriptionItr =
		 * serviceSubList.iterator();
		 * 
		 * List<BillItem> billItemLst = new ArrayList<BillItem>(); while
		 * (serviceSubscriptionItr.hasNext()) { ServiceSubscription
		 * serviceSubscription = serviceSubscriptionItr.next(); billDTO = new
		 * BillDto();
		 * 
		 * this.generateBillItemForServicePlan(billItemLst,
		 * serviceSubscription);
		 * 
		 * billDTO.setBillLineItems(billItemLst);
		 * billDTO.setCustomerId(serviceSubscription
		 * .getSrvcSubcrptnOfPerson().getPersnId()); Calendar calendar =
		 * Calendar.getInstance(); calendar.setTime(billDate);
		 * calendar.add(Calendar.DATE, this.noOfDaysBillAdv);
		 * 
		 * billDTO.setBillDueDate(calendar.getTime());
		 * billDTO.setBillDate(billDate);
		 * this.manageBill.createNewBill(billDTO); ); }
		 */

	}

	private void generateBillItemForServicePlan(List<BillItem> billItemLst,
										ServiceSubscription serviceSubscription)
	{
		/*
		 * ServicePlan serviceplan =
		 * serviceSubscription.getSubscribedSrvcsPlan(); Set<ServiceRate>
		 * serviceRateSet = serviceplan.getServiceRateSet();
		 * Iterator<ServiceRate> serviceRateItr = serviceRateSet.iterator();
		 * 
		 * BillItem billItem = null; while (serviceRateItr.hasNext()) {
		 * ServiceRate serviceRate = serviceRateItr.next(); // ChargeComponent
		 * chargeComponent = // serviceRate.getSrvcChargeComponent(); if
		 * (serviceRate.getSrvcChargeFrequency().equals(Frequency.MONTHLY)) {
		 * billItem = new BillItem();
		 * billItem.setBillItemAmount(serviceRate.getSrvcChargeRate
		 * ().getPrice().getAmount()); billItem.setBillItemQuantity(1);
		 * billItem.setBillItemService(serviceRate.getService());
		 * 
		 * } else if
		 * (serviceRate.getSrvcChargeFrequency().equals(Frequency.WEEKLY)) {
		 * billItem = new BillItem(); billItem. setBillItemAmount(
		 * serviceRate.getSrvcChargeRate(). getPrice().getAmount(). multiply(
		 * this.calculateQuantity(
		 * serviceSubscription.getSrvcSubcrptnStartDate(),
		 * serviceSubscription.getSrvcSubcrptnEndDate(),
		 * serviceRate.getSrvcChargeRate().getPerUnit()))); } else if
		 * (serviceRate.getSrvcChargeFrequency().equals(Frequency.DAILY)) {
		 * billItem = new BillItem(); billItem. setBillItemAmount(
		 * serviceRate.getSrvcChargeRate(). getPrice().getAmount(). multiply(
		 * this.calculateQuantity(
		 * serviceSubscription.getSrvcSubcrptnStartDate(),
		 * serviceSubscription.getSrvcSubcrptnEndDate(),
		 * serviceRate.getSrvcChargeRate().getPerUnit())) );
		 * 
		 * }
		 * 
		 * if (billItem != null) { billItemLst.add(billItem); } }
		 */

	}

	private BigDecimal calculateQuantity(final Date startDate, final Date endDate, final TimeUnit unit)
	{
		DateTime startDateJ = new DateTime(startDate);
		DateTime endDateJ = new DateTime(endDate);

		int quantity = 0;
		if (startDateJ.isBefore(endDateJ))
		{
			if (unit.equals(TimeUnit.Weeks))
			{
				quantity = Weeks.weeksBetween(startDateJ, endDateJ).getWeeks();
			}
			else if (unit.equals(TimeUnit.Days))
			{
				quantity = Days.daysBetween(startDateJ, endDateJ).getDays();
			}

		}

		return quantity == 0 ? BigDecimal.ZERO : (quantity > 0 ? new BigDecimal(quantity) : null);
	}

}
