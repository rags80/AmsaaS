package testarchive.infrastructure.persistance.jpa;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ams.billing.domain.model.servicecatalog.Service;
import com.ams.billing.domain.model.servicecatalog.ServicePlan;
import com.ams.billing.domain.model.servicecatalog.ServiceRate;
import com.ams.billing.domain.model.servicecatalog.ServiceRate.ServiceChargeType;
import com.ams.billing.domain.repository.ServicePlanRepository;
import com.ams.billing.domain.repository.ServiceRepository;
import com.ams.sharedkernel.domain.model.measuresandunits.Currency;
import com.ams.sharedkernel.domain.model.measuresandunits.Frequency;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.sharedkernel.domain.model.measuresandunits.Rate;
import com.ams.sharedkernel.domain.model.measuresandunits.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ApplicationContext.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager_Jpa",defaultRollback = false)
public class ServicePlanRepositoryImpl_JPA_test
{
	@Autowired
	private ServicePlanRepository	srvcPlanRepo;

	@Autowired
	private ServiceRepository	srvcRepo;

	private ServicePlan			srvcPlan;
	private Service			srvc;
	private ServiceRate			srvcRate;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{}

	@Before
	public void setUp() throws Exception
	{
		srvcPlan = new ServicePlan("REGULAR-PLAN", new Date());
		srvc = new Service("SRVC-02", "PLUMB-Maintainance", "Plumber service");
	}

	@After
	public void tearDown() throws Exception
	{}

	@Test
	public final void testCreate()
	{
		assertEquals("service plan list size:", 0, srvcPlanRepo.findAll().size());
		String id = srvcPlanRepo.createOrUpdate(srvcPlan);
		System.out.println("Id:" + id);
		assertEquals(2, srvcPlanRepo.findAll().size());
		assertEquals("REGULAR-PLAN", srvcPlanRepo.findById(id).getSrvcPlanName());
	}

	@Test
	public final void testUpdate()
	{
		assertEquals("service plan list size:", 2, srvcPlanRepo.findAll().size());
		String id = srvcPlanRepo.createOrUpdate(srvcPlan);
		assertEquals("service plan name:", id, srvcPlanRepo.findById(id).getSrvcPlanName());
	}

	@Test
	public final void testDelete()
	{
		assertEquals("service plan list size:", 2, srvcPlanRepo.findAll().size());
		srvcPlanRepo.delete(srvcPlan.getSrvcPlanName());
		assertEquals("service plan list size:", 1, srvcPlanRepo.findAll().size());

	}

	@Test
	public final void testFindById()
	{
		assertEquals("service plan list size:", 2, srvcPlanRepo.findAll().size());
		String id = srvcPlanRepo.createOrUpdate(srvcPlan);
		assertEquals("service plan is:", id, srvcPlanRepo.findById(id).getSrvcPlanName());

	}

	@Test
	public final void testFindAll()
	{
		assertEquals("service plan list size:", 2, srvcPlanRepo.findAll().size());
		srvcPlanRepo.createOrUpdate(srvcPlan);
		assertEquals("service plan list size:", 2, srvcPlanRepo.findAll().size());
	}

	@Test
	public final void testSaveOrUpdateServiceRate()
	{
		srvc = srvcRepo.findById("SRVC-01");
		srvcPlan = srvcPlanRepo.findById("REGULAR-PLAN");
		srvcRate = new ServiceRate(srvcPlan, srvc, "Apartment Maintainance Service Charge", ServiceChargeType.valueOf("RATE"), new Rate(new Money(new BigDecimal(1000), Currency.INR), TimeUnit.Months), Frequency.valueOf("MONTHLY"), false);
		srvcPlanRepo.saveOrUpdateServiceRate(srvcRate);
		assertEquals("Apartment Maintainance Service Charge", srvcPlanRepo.findAllServiceRateByCriteria("REGULAR-PLAN", "SRVC-02").get(0).getSrvcChargeName());
		srvcRate = new ServiceRate(srvcPlan, srvc, "Apartment-Maintainance Service Charge", ServiceChargeType.valueOf("RATE"), new Rate(new Money(new BigDecimal(2000), Currency.INR), TimeUnit.Months), Frequency.valueOf("MONTHLY"), false);
		srvcPlanRepo.saveOrUpdateServiceRate(srvcRate);
		assertEquals("Apartment-Maintainance Service Charge", srvcPlanRepo.findAllServiceRateByCriteria("REGULAR-PLAN", "SRVC-01").get(0).getSrvcChargeName());
		assertEquals(new BigDecimal(2000), srvcPlanRepo.findAllServiceRateByCriteria("REGULAR-PLAN", "SRVC-02").get(0).getSrvcChargeRate().getPrice().getAmount());
	}

	@Test
	public final void testDeleteServiceRateComponentByCriteria()
	{
		assertEquals(0, srvcPlanRepo.findAllServiceRateByCriteria("REGULAR-PLAN", "SRVC-01").size());
		srvc = srvcRepo.findById("SRVC-01");
		srvcPlan = srvcPlanRepo.findById("REGULAR-PLAN");
		srvcRate = new ServiceRate(srvcPlan, srvc, "Maintainance Service Charge", ServiceChargeType.valueOf("RATE"), new Rate(new Money(new BigDecimal(1000), Currency.INR), TimeUnit.Months), Frequency.valueOf("MONTHLY"), false);
		srvcPlanRepo.saveOrUpdateServiceRate(srvcRate);
		assertEquals(1, srvcPlanRepo.findAllServiceRateByCriteria("REGULAR-PLAN", "SRVC-01").size());
		srvcPlanRepo.deleteServiceRateByCriteria(srvcPlan.getSrvcPlanName(), srvc.getSrvcCode());
		assertEquals(0, srvcPlanRepo.findAllServiceRateByCriteria("REGULAR-PLAN", "SRVC-01").size());
	}

	@Test
	public final void testFindAllServiceRate()
	{
		assertEquals(0, srvcPlanRepo.findAllServiceRateByPlanName("REGULAR-PLAN").size());
		srvc = srvcRepo.findById("SRVC-01");
		srvcPlan = srvcPlanRepo.findById("REGULAR-PLAN");
		srvcRate = new ServiceRate(srvcPlan, srvc, "Maintainance Service Charge", ServiceChargeType.valueOf("RATE"), new Rate(new Money(new BigDecimal(1000), Currency.INR), TimeUnit.Months), Frequency.valueOf("MONTHLY"), false);
		srvcPlanRepo.saveOrUpdateServiceRate(srvcRate);
		assertEquals(1, srvcPlanRepo.findAllServiceRateByPlanName("REGULAR-PLAN").size());
	}

	@Test
	public final void testFindAllServiceRateComponentByCriteria()
	{
		assertEquals(0, srvcPlanRepo.findAllServiceRateByCriteria("REGULAR-PLAN", "SRVC-01").size());
		srvc = srvcRepo.findById("SRVC-01");
		srvcPlan = srvcPlanRepo.findById("REGULAR-PLAN");
		srvcRate = new ServiceRate(srvcPlan, srvc, "Maintainance Service Charge", ServiceChargeType.valueOf("RATE"), new Rate(new Money(new BigDecimal(1000), Currency.INR), TimeUnit.Months), Frequency.valueOf("MONTHLY"), false);
		srvcPlanRepo.saveOrUpdateServiceRate(srvcRate);
		assertEquals(1, srvcPlanRepo.findAllServiceRateByCriteria("REGULAR-PLAN", "SRVC-01").size());
	}

}
