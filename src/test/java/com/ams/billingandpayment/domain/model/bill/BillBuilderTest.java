/**
 * 
 */
package com.ams.billingandpayment.domain.model.bill;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.ams.billingandpayment.domain.model.bill.Bill.BillBuilder;
import com.ams.billingandpayment.domain.model.bill.policy.DiscountPolicy;
import com.ams.billingandpayment.domain.model.bill.policy.TaxPolicy;
import com.ams.billingandpayment.domain.model.serviceportfolio.Service;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePlan;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice.ServicePriceCategory;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePriceSpecification;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServiceSubscription.Status;
import com.ams.billingandpayment.domain.service.ServicePriceSpecAdvisor;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.sharedkernel.domain.model.measuresandunits.Quantity;
import com.ams.sharedkernel.domain.model.measuresandunits.TimeUnit;
import com.ams.users.domain.model.Person;

/**
 * @author Raghavendra Badiger
 * 
 */
public class BillBuilderTest
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private BillBuilder			billBuilder;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		this.billBuilder = new BillBuilder();
	}

	/**
	 * Test method for
	 * {@link com.ams.billingandpayment.domain.model.bill.Bill.BillBuilder#isHeaderSet()}
	 * .
	 * 
	 * @throws ParseException
	 */
	@Test
	public final void testIsHeaderSet() throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		this.billBuilder.header(new Person("Ragh", "Boww!"), new Date(), new Date(), new Period(sdf.parse("01-03-2014 13:00:00"), sdf.parse("01-03-2014 13:00:00")), new BillPaymentRegister(2));
		assertEquals(true, this.billBuilder.isHeaderSet());

	}

	/**
	 * Test method for
	 * {@link com.ams.billingandpayment.domain.model.bill.Bill.BillBuilder#header(com.ams.users.domain.model.Person, java.util.Date, java.util.Date, com.ams.sharedkernel.domain.model.measuresandunits.Period, com.ams.billingandpayment.domain.model.bill.BillPaymentRegister)}
	 * .
	 * 
	 * @throws ParseException
	 */
	@Test
	public final void testHeader() throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		DiscountPolicy dp = new DiscountPolicy()
		{

			@Override
			public Discount calculateDiscount(Money amnt)
			{
				return Discount.ZERO_DISCOUNT;
			}
		};

		TaxPolicy tp = new TaxPolicy()
		{

			@Override
			public Tax calculateTax(Money amount)
			{

				return Tax.ZERO_TAX;
			}
		};

		Person p = new Person("Scooby", "Doo!");
		// p.setPersnId(10L);

		this.billBuilder.header(p, new Date(), new Date(2014, 5, 20, 12, 00), new Period(sdf.parse("01-03-2014 13:00:00"), sdf.parse("30-03-2014 13:00:00")), new BillPaymentRegister(2));
		assertEquals(true, this.billBuilder.isHeaderSet());
		this.billBuilder.getBillInstance(dp, tp);

	}

	/**
	 * Test method for
	 * {@link com.ams.billingandpayment.domain.model.bill.Bill.BillBuilder#addLineItem(com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice, com.ams.sharedkernel.domain.model.measuresandunits.Quantity, com.ams.billingandpayment.domain.model.bill.policy.DiscountPolicy, com.ams.billingandpayment.domain.model.bill.policy.TaxPolicy)}
	 * .
	 * 
	 * @throws ParseException
	 */
	@Test
	public final void testAddLineItem() throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		Person p = new Person("Scooby", "Doo!");
		// p.setPersnId(10L);
		this.billBuilder.header(p, new Date(), new Date(2014, 5, 20, 12, 00), new Period(sdf.parse("01-03-2014 13:00:00"), sdf.parse("01-03-2014 13:00:00")), new BillPaymentRegister(2));

		ServicePlan spl = new ServicePlan("SPL-1", "EconomyPlan", new Date(), Status.ACTIVE.toString());
		Service srvc = new Service("SRVC01", "MaintSrvc", "Maintainace Service");
		ServicePriceSpecAdvisor spsAdvsr = new ServicePriceSpecAdvisor()
		{

			@Override
			public ServicePriceSpecification adviseSpec(ServicePrice sprice)
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

		ServicePrice srvcPrice = new ServicePrice(spl, srvc, ServicePriceCategory.NON_USAGE.toString(), new Money(BigDecimal.valueOf(1000), Currency.getInstance("INR")), TimeUnit.Months.toString());
		Period billPeriod = new Period(sdf.parse("01-03-2014 13:00:00"), sdf.parse("15-03-2014 13:00:00"));

		DiscountPolicy dp = new DiscountPolicy()
		{

			@Override
			public Discount calculateDiscount(Money amnt)
			{
				return new Discount(new Money(BigDecimal.valueOf(2), Currency.getInstance("INR")), "Default Bill Tax");
			}
		};

		TaxPolicy tp = new TaxPolicy()
		{

			@Override
			public Tax calculateTax(Money amount)
			{

				return new Tax(new Money(BigDecimal.valueOf(5), Currency.getInstance("INR")), "Default Bill Tax");
			}
		};

		Quantity qty = Period.convertTo(billPeriod, srvcPrice.getSrvcUnitOfMeasure());
		System.out.println("Qty in test:" + qty);
		this.billBuilder.addLineItem(srvcPrice, qty, dp, tp);
		assertEquals(1, this.billBuilder.bill.getBillItems().size());

		srvc = new Service("SRVC02", "PlumbSrvc", "Plumb Service");
		srvcPrice = new ServicePrice(spl, srvc, ServicePriceCategory.USAGE.toString(), new Money(BigDecimal.valueOf(20), Currency.getInstance("INR")), TimeUnit.Days.toString());
		qty = Period.convertTo(billPeriod, srvcPrice.getSrvcUnitOfMeasure());
		System.out.println("Qty in test for SRVC02:" + qty);
		this.billBuilder.addLineItem(srvcPrice, qty, dp, tp);
		assertEquals(2, this.billBuilder.bill.getBillItems().size());
		this.billBuilder.getBillInstance(dp, tp);

	}
}
