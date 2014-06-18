/**
 * 
 */
package com.ams.billingandpayment.application.impl.bill;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.ams.billingandpayment.application.api.service.bill.ManageBill;
import com.ams.billingandpayment.domain.model.bill.BillPaymentRegister;
import com.ams.billingandpayment.domain.model.bill.Discount;
import com.ams.billingandpayment.domain.model.bill.Tax;
import com.ams.billingandpayment.domain.model.bill.policy.DiscountPolicy;
import com.ams.billingandpayment.domain.model.bill.policy.TaxPolicy;
import com.ams.billingandpayment.domain.model.serviceportfolio.Service;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePlan;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice.ServicePriceCategory;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePriceSpecification;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServiceSubscription.Status;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServiceUsageEvent;
import com.ams.billingandpayment.domain.repository.BillRepository;
import com.ams.billingandpayment.domain.repository.ServicePlanRepository;
import com.ams.billingandpayment.domain.repository.ServiceUsageEventRepository;
import com.ams.billingandpayment.domain.service.DiscountPolicyAdvisor;
import com.ams.billingandpayment.domain.service.ServicePriceSpecAdvisor;
import com.ams.billingandpayment.domain.service.TaxPolicyAdvisor;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.sharedkernel.domain.model.measuresandunits.TimeUnit;
import com.ams.sharedkernel.domain.service.exception.ServiceException;
import com.ams.users.domain.model.Person;
import com.ams.users.domain.repository.PersonRepository;

/**
 * @author Raghavendra Badiger
 * 
 */
public class ManageBillImplTest_MockDependencies
{

	private ManageBill					manageBill;

	private PersonRepository				mockPersnRepo;

	private BillRepository				mockBillRepo;

	private ServicePlanRepository			mockSrvcPlanRepo;

	private ServiceUsageEventRepository	mockSrvcUsageEventRepo;

	private DiscountPolicyAdvisor			mockDiscntPolicyAdvsr;

	private TaxPolicyAdvisor				mockTaxPolicyAdvsr;

	private DiscountPolicy				mockDiscountPolicy;

	private TaxPolicy					mockTaxPolicy;

	private List<ServicePrice>			usageSrvcPriceList, nonUsageSrvcPriceList;

	private Person						subscrbr;

	private Date						billDate, billDueDate;

	private Period						billPeriod;

	private BillPaymentRegister			billPayReg;

	private ServicePlan					srvcPlan;

	private Service					s1, s2, s3, s4;

	private ServicePriceSpecAdvisor		mockSrvcPriceSpecAdvsr	= new ServicePriceSpecAdvisor()
														{

															@Override
															public ServicePriceSpecification adviseSpec(ServicePrice srvcPrice)
															{

																if (srvcPrice.getService().getSrvcCode().equals("Srvc-01") || srvcPrice.getService().getSrvcCode().equals("Srvc-02") || srvcPrice.getService().getSrvcCode().equals("Srvc-03") || srvcPrice.getService().getSrvcCode().equals("Srvc-04"))
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

																else
																{
																	throw new ServiceException("No spec exists!!");
																}
															}
														};

	@Before
	public void setUp() throws Exception
	{
		// MOCK INJECTS
		this.manageBill = new ManageBillImpl();
		this.mockPersnRepo = mock(PersonRepository.class);
		ReflectionTestUtils.setField(this.manageBill, "personRepository", this.mockPersnRepo);
		this.mockBillRepo = mock(BillRepository.class);
		ReflectionTestUtils.setField(this.manageBill, "billRepository", this.mockBillRepo);
		this.mockSrvcPlanRepo = mock(ServicePlanRepository.class);
		ReflectionTestUtils.setField(this.manageBill, "srvcPlanRepository", this.mockSrvcPlanRepo);
		this.mockSrvcUsageEventRepo = mock(ServiceUsageEventRepository.class);
		ReflectionTestUtils.setField(this.manageBill, "srvcUsageEventRepository", this.mockSrvcUsageEventRepo);
		this.mockDiscntPolicyAdvsr = mock(DiscountPolicyAdvisor.class);
		ReflectionTestUtils.setField(this.manageBill, "discountPolicyAdvisor", this.mockDiscntPolicyAdvsr);
		this.mockTaxPolicyAdvsr = mock(TaxPolicyAdvisor.class);
		ReflectionTestUtils.setField(this.manageBill, "taxPolicyAdvisor", this.mockTaxPolicyAdvsr);
		this.subscrbr = mock(Person.class);
		ReflectionTestUtils.setField(this.subscrbr, "persnId", 1L);
		ReflectionTestUtils.setField(this.manageBill, "srvcPriceSpecAdvsr", this.mockSrvcPriceSpecAdvsr);

		// Dependent objects set up
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
		this.billDate = sdf.parse("01-04-2014 00:00:00");
		this.billDueDate = sdf.parse("20-04-2014 00:00:00");
		this.billPeriod = new Period(sdf.parse("01-03-2014 00:00:00"), sdf.parse("01-04-2014 00:00:00"));
		this.billPayReg = new BillPaymentRegister(1L);

		this.srvcPlan = new ServicePlan("ECONOMY-PLAN", "Economy plan", new Date(), Status.ACTIVE.toString());

		this.mockDiscountPolicy = new DiscountPolicy()
		{

			@Override
			public Discount calculateDiscount(Money amnt)
			{

				return new Discount(amnt.multiplyBy(BigDecimal.valueOf(0.1)), "10% Discount on all Services..:D");
			}
		};

		this.mockTaxPolicy = new TaxPolicy()
		{

			@Override
			public Tax calculateTax(Money amount)
			{
				return new Tax(amount.multiplyBy(BigDecimal.valueOf(0.05)), "5% Tax on all Services");
			}
		};

		this.s1 = new Service("Srvc-01", "Maint-Srvc", "Manitainace Service");
		ServicePrice srvcprice1 = new ServicePrice(this.srvcPlan, this.s1, ServicePriceCategory.NON_USAGE.toString(), new Money(BigDecimal.valueOf(1000), Currency.getInstance("INR")), TimeUnit.Months.toString());
		this.s2 = new Service("Srvc-02", "Inst-Srvc", "Installation");
		ServicePrice srvcprice2 = new ServicePrice(this.srvcPlan, this.s2, ServicePriceCategory.NON_USAGE.toString(), new Money(BigDecimal.valueOf(200), Currency.getInstance("INR")), TimeUnit.Months.toString());
		this.nonUsageSrvcPriceList = new ArrayList<ServicePrice>(2);
		this.nonUsageSrvcPriceList.add(srvcprice1);
		this.nonUsageSrvcPriceList.add(srvcprice2);

		this.s3 = new Service("Srvc-03", "Plumb-Srvc", "Plumbing Service");
		ServicePrice srvcprice3 = new ServicePrice(this.srvcPlan, this.s3, ServicePriceCategory.USAGE.toString(), new Money(BigDecimal.valueOf(100), Currency.getInstance("INR")), TimeUnit.Hrs.toString());
		this.usageSrvcPriceList = new ArrayList<ServicePrice>(2);
		this.usageSrvcPriceList.add(srvcprice3);

		List<ServiceUsageEvent> sueList = new ArrayList<ServiceUsageEvent>(3);
		sueList.add(new ServiceUsageEvent(this.subscrbr, this.s3, new Period(sdf.parse("01-03-2014 13:00:00"), sdf.parse("01-03-2014 17:00:00"))));

		when(this.mockBillRepo.findBillPaymentRegisterFor(this.subscrbr)).thenReturn(this.billPayReg);

		when(this.mockSrvcPlanRepo.findServicePlanOfPerson(this.subscrbr.getPersnId())).thenReturn(this.srvcPlan);

		when(this.mockSrvcPlanRepo.findAllServicePricesByCriteria(this.srvcPlan.getSrvcPlanName(), ServicePriceCategory.NON_USAGE.toString())).thenReturn(this.nonUsageSrvcPriceList);

		when(this.mockSrvcPlanRepo.findServicePriceByCriteria(this.srvcPlan.getSrvcPlanName(), this.s3.getSrvcCode())).thenReturn(srvcprice3);

		when(this.mockSrvcUsageEventRepo.findAllForCustomerWithinPeriod(this.subscrbr.getPersnId(),
															this.billPeriod)).thenReturn(sueList);

		// when(srvcprice3.getSrvcPriceSpec(this.mockSrvcPriceSpecAdvsr).isApplicableFor(null)).thenReturn(true);

		when(this.mockDiscntPolicyAdvsr.adviseServiceDiscountPolicy(this.subscrbr, this.billPeriod, this.srvcPlan, this.nonUsageSrvcPriceList.get(0))).thenReturn(this.mockDiscountPolicy);

		when(this.mockDiscntPolicyAdvsr.adviseServiceDiscountPolicy(this.subscrbr, this.billPeriod, this.srvcPlan, this.nonUsageSrvcPriceList.get(1))).thenReturn(this.mockDiscountPolicy);

		when(this.mockDiscntPolicyAdvsr.adviseServiceDiscountPolicy(this.subscrbr, this.billPeriod, this.srvcPlan, srvcprice3)).thenReturn(this.mockDiscountPolicy);

		when(this.mockTaxPolicyAdvsr.adviseServiceTaxPolicy(this.s1)).thenReturn(this.mockTaxPolicy);

		when(this.mockTaxPolicyAdvsr.adviseServiceTaxPolicy(this.s2)).thenReturn(this.mockTaxPolicy);

		when(this.mockTaxPolicyAdvsr.adviseServiceTaxPolicy(this.s3)).thenReturn(this.mockTaxPolicy);

		when(this.mockDiscntPolicyAdvsr.adviseBillDiscountPolicy(this.subscrbr, this.billPeriod)).thenReturn(this.mockDiscountPolicy);

		when(this.mockTaxPolicyAdvsr.adviseBillTaxPolicy(this.subscrbr, this.billPeriod)).thenReturn(this.mockTaxPolicy);

	}

	@Test
	public final void testBillSubscriberForPeriod() throws ParseException
	{

		this.manageBill.billSubscriberForPeriod(this.subscrbr, this.billPeriod, this.billDate, this.billDueDate);

	}

	@Test
	public final void testNonUsageCharges()
	{
		fail("Not yet implemented");
	}

	@Test
	public final void testUsageCharges()
	{
		fail("Not yet implemented");
	}

}
