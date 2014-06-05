package com.ams.billingandpayment.application.impl.serviceportfolio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ams.billingandpayment.application.api.dto.ServicePriceCommand;
import com.ams.billingandpayment.application.api.service.serviceportfolio.ManageService;
import com.ams.billingandpayment.application.api.service.serviceportfolio.ManageServicePlan;
import com.ams.billingandpayment.domain.model.serviceportfolio.Service;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePlan;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice.ServicePriceCategory;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServiceSubscription.Status;
import com.ams.billingandpayment.domain.repository.ServicePlanRepository;
import com.ams.sharedkernel.domain.model.measuresandunits.TimeUnit;
import com.ams.sharedkernel.domain.repository.Page;

/**
 * @author Raghavendra Badiger
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:context-application.xml", "classpath:context-infrastructure-persistance.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ManageServicePlanImplTest
{

	@Autowired
	private ManageServicePlan	manageServicePlan;

	@Autowired
	private ManageService		manageService;

	@Autowired
	private ServicePlanRepository	srvcPlanRepo;

	private ServicePlan			srvcPlan;

	private Service			srvc;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		this.srvcPlan = new ServicePlan("ECONOMY_PLAN", "Economy plan", new Date(), Status.ACTIVE.toString());
		this.srvc = new Service("SRVC-01", "MAINT_SRVC", "Maintainance Service");

	}

	@Test
	public final void testGetAllServicePlans()
	{
		assertEquals(1, this.manageServicePlan.getAllServicePlans().size());
		this.manageServicePlan.registerServicePlan(new ServicePlan("MOD_PLAN", "Mod-plan", new Date(), Status.ACTIVE.toString()));
		assertEquals(2, this.manageServicePlan.getAllServicePlans().size());
	}

	@Test
	public final void testGetNextPageOfServicePricesForPlan()
	{
		fail("Not yet implemented");
	}

	@Test
	public final void testGetServicePlan()
	{
		assertEquals(this.srvcPlan, this.manageServicePlan.getServicePlan(this.srvcPlan.getSrvcPlanName()));

	}

	@Test
	public final void testGetServicePlansNextPage()
	{
		assertEquals(1, this.manageServicePlan.getAllServicePlans().size());
		this.manageServicePlan.registerServicePlan(new ServicePlan("MOD_PLAN", "Mod-plan", new Date(), Status.ACTIVE.toString()));
		Page<ServicePlan> p = this.manageServicePlan.getServicePlansNextPage(1, 5);
		assertEquals("MOD_PLAN", p.getPageDataList().get(0).getSrvcPlanName());

	}

	@Test
	public final void testGetServicePricesForPlan()
	{
		fail("Not yet implemented");
	}

	@Test
	public final void testRegisterServicePlan()
	{
		assertEquals(0, this.srvcPlanRepo.findAll().size());
		this.manageServicePlan.registerServicePlan(this.srvcPlan);
		this.manageService.registerService(this.srvc);
		assertEquals(1, this.srvcPlanRepo.findAll().size());

	}

	@Test
	public final void testRegisterServicePriceToPlan()
	{
		assertEquals(0, this.manageServicePlan.getServicePricesForPlan(this.srvcPlan.getSrvcPlanName()).size());
		ServicePriceCommand spc = new ServicePriceCommand(this.srvcPlan.getSrvcPlanName(), this.srvc.getSrvcCode(), ServicePriceCategory.USAGE.toString(), 1000.0, "INR", TimeUnit.Months.toString());
		this.manageServicePlan.registerServicePriceToPlan(spc);
		assertEquals(1, this.manageServicePlan.getServicePricesForPlan(this.srvcPlan.getSrvcPlanName()).size());

	}

	@Test
	public final void testRemoveServicePlan()
	{
		assertEquals(1, this.manageServicePlan.getAllServicePlans().size());
		this.manageServicePlan.removeServicePlan(this.srvcPlan.getSrvcPlanName());
		assertEquals(0, this.manageServicePlan.getAllServicePlans().size());

	}

	@Test
	public final void testRemoveServicePriceServicePriceCommand()
	{
		fail("Not yet implemented");
	}

	@Test
	public final void testRemoveServicePriceStringString()
	{
		fail("Not yet implemented");
	}

	@Test
	public final void testUpdateServicePlanDetails()
	{
		this.srvcPlan.updateServicePlanDetails("New Description", new Date(), Status.CANCELLED.toString());
		this.manageServicePlan.updateServicePlanDetails(this.srvcPlan);
		assertEquals(Status.CANCELLED, this.manageServicePlan.getServicePlan(this.srvcPlan.getSrvcPlanName()).getSrvcPlanStatus());

	}

	@Test
	public final void testUpdateServicePriceDetails()
	{
		fail("Not yet implemented");
	}

}
