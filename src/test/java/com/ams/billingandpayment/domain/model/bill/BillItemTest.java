/**
 *
 */
package com.ams.billingandpayment.domain.model.bill;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.ams.billingandpayment.domain.model.bill.policy.DiscountPolicy;
import com.ams.billingandpayment.domain.model.bill.policy.TaxPolicy;
import com.ams.billingandpayment.domain.model.serviceportfolio.Service;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePlan;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePriceSpecification;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice.ServicePriceCategory;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServiceSubscription.Status;
import com.ams.billingandpayment.domain.service.ServicePriceSpecAdvisor;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.sharedkernel.domain.model.measuresandunits.Quantity;
import com.ams.sharedkernel.domain.model.measuresandunits.TimeUnit;

/**
 * @author Raghavendra Badiger
 */
public class BillItemTest
{

	private ServicePrice			sp;
	private Quantity				qty;
	private TaxPolicy				taxPolicy;
	private DiscountPolicy			discntPolicy;
	private ServicePriceSpecAdvisor	srvcPriceSpecAdvsr;

	@Before
	public void setUp() throws Exception
	{

		this.taxPolicy = new TaxPolicy()
		{

			@Override
			public Tax calculateTax(Money amount)
			{
				return new Tax(new Money(BigDecimal.valueOf(30), Currency.getInstance("INR")), "Default Tax");
			}
		};

		this.discntPolicy = new DiscountPolicy()
		{

			@Override
			public Discount calculateDiscount(Money amnt)
			{
				return new Discount(new Money(BigDecimal.TEN, Currency.getInstance("INR")), "Default Discount");
			}
		};

		this.srvcPriceSpecAdvsr = new ServicePriceSpecAdvisor()
		{

			@Override
			public ServicePriceSpecification adviseSpec(ServicePrice srvcPrice)
			{
				return new ServicePriceSpecification()
				{

					@Override
					public boolean isApplicableFor(Period billPeriod)
					{
						return true;
					}
				};
			}
		};
		this.sp = new ServicePrice(new ServicePlan("SPL-1", "Economy-Plan", new Date(), Status.ACTIVE.toString()), new Service("SRVC01", "XYZ Service", "XYZ Services"),
								ServicePriceCategory.NON_USAGE.toString(), new BigDecimal(10), "INR", TimeUnit.Months.toString());
		this.qty = new Quantity(new BigDecimal(3), TimeUnit.Months);

	}

	@Test
	public final void testBillItem()
	{
		BillItem bi = new BillItem(this.sp, this.qty, this.taxPolicy, this.discntPolicy);
		System.out.println("ServicePrice/Unit:" + bi.servicePrice());
		System.out.println("Item Qty:" + bi.getQuantity());
		System.out.println("Item Gross Amount:" + bi.getGrossAmount());
		System.out.println("Discount:" + bi.getItemDiscount().getDiscntAmount());
		System.out.println("Tax:" + bi.getItemTax().getTaxAmount());
		System.out.println("Item Net Amount:" + bi.getNetAmount());

		assertEquals(BigDecimal.valueOf(50), bi.getNetAmount().getAmount());

		System.out.println("Item Qty:" + bi.getQuantity());
		this.qty.add(BigDecimal.valueOf(5));
		bi.increaseQuantity(this.qty, this.taxPolicy, this.discntPolicy);
		System.out.println("Item Qty:" + bi.getQuantity());
		System.out.println("Local Qty value:" + this.qty.getValue());

		System.out.println("Item Gross Amount:" + bi.getGrossAmount());
		System.out.println("Discount:" + bi.getItemDiscount().getDiscntAmount());
		System.out.println("Tax:" + bi.getItemTax().getTaxAmount());
		System.out.println("Item Net Amount:" + bi.getNetAmount());

	}

}
