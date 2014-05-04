/**
 *
 */
package com.ams.billing.ports.adapter.persistance.jpa;

import com.ams.billingandpayment.domain.model.servicecatalog.ServicePlan;
import com.ams.billingandpayment.domain.repository.ServicePlanRepository;
import com.ams.sharedkernel.domain.repository.Page;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Raghavendra Badiger
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "txManager_Jpa", defaultRollback = true)
public class TestServicePlanRepository {

    private ServicePlan srvcPlan;

    @Autowired
    private ServicePlanRepository srvcPlanRepo;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.srvcPlan = new ServicePlan("ECONOMY-PLAN", "Economy plan", new Date(), "ACTIVE");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.ams.billingandpayment.ports.adapter.persistance.jpa.ServicePlanRepositoryImpl#createOrUpdate(com.ams.billingandpayment.domain.model.servicecatalog.ServicePlan)}
     * .
     */
    @Test
    public final void testCreateOrUpdate() {
        assertEquals(0, this.srvcPlanRepo.findAll().size());
        this.srvcPlanRepo.createOrUpdate(this.srvcPlan);
        assertEquals(1, this.srvcPlanRepo.findAll().size());
        assertEquals(this.srvcPlanRepo.findById(this.srvcPlan.getSrvcPlanName()).getSrvcPlanDescription(),
                this.srvcPlan.getSrvcPlanDescription());
        this.srvcPlan.updateServicePlanDetails("Economy plan for common users", new Date(), "ACTIVE");
        this.srvcPlanRepo.createOrUpdate(this.srvcPlan);
        assertEquals(this.srvcPlanRepo.findById(this.srvcPlan.getSrvcPlanName()).getSrvcPlanDescription(),
                this.srvcPlan.getSrvcPlanDescription());

    }

    /**
     * Test method for
     * {@link com.ams.billingandpayment.ports.adapter.persistance.jpa.ServicePlanRepositoryImpl#delete(java.lang.String)}
     * .
     */
    @Test
    public final void testDelete() {
        assertEquals(0, this.srvcPlanRepo.findAll().size());
        this.srvcPlanRepo.createOrUpdate(this.srvcPlan);
        assertEquals(1, this.srvcPlanRepo.findAll().size());
        this.srvcPlanRepo.delete(this.srvcPlan.getSrvcPlanName());
        assertEquals(0, this.srvcPlanRepo.findAll().size());

    }

    /**
     * Test method for
     * {@link com.ams.billingandpayment.ports.adapter.persistance.jpa.ServicePlanRepositoryImpl#findById(java.lang.String)}
     * .
     */
    @Test
    public final void testFindById() {
        this.srvcPlanRepo.createOrUpdate(this.srvcPlan);
        assertEquals(1, this.srvcPlanRepo.findAll().size());
        assertEquals(this.srvcPlanRepo.findById(this.srvcPlan.getSrvcPlanName()).getSrvcPlanDescription(),
                this.srvcPlan.getSrvcPlanDescription());

    }

    /**
     * Test method for
     * {@link com.ams.billingandpayment.ports.adapter.persistance.jpa.ServicePlanRepositoryImpl#findAll()}
     * .
     */
    @Test
    public final void testFindAll() {
        this.srvcPlanRepo.createOrUpdate(this.srvcPlan);
        assertEquals(1, this.srvcPlanRepo.findAll().size());

    }

    /**
     * Test method for
     * {@link com.ams.billingandpayment.ports.adapter.persistance.jpa.ServicePlanRepositoryImpl#findNextPageData(com.ams.sharedkernel.domain.repository.Page)}
     * .
     */
    @Test
    public final void testFindNextPageData() {

        for (int i = 0; i < 5; i++) {
            this.srvcPlan = new ServicePlan("SRVCPLAN" + i, "Service Plan:" + i, new Date(), "ACTIVE");
            this.srvcPlanRepo.createOrUpdate(this.srvcPlan);
        }
        assertEquals(5, this.srvcPlanRepo.findAll().size());
        assertEquals(3, this.srvcPlanRepo.findNextPageData(new Page<ServicePlan>(0, 3)).getPageDataList().size());

    }

    /**
     * Test method for
     * {@link com.ams.billingandpayment.ports.adapter.persistance.jpa.ServicePlanRepositoryImpl#saveOrUpdateServicePriceToPlan(com.ams.billingandpayment.domain.model.servicecatalog.ServicePrice)}
     * .
     */
    @Test
    public final void testSaveOrUpdateServicePriceToPlan() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.ams.billingandpayment.ports.adapter.persistance.jpa.ServicePlanRepositoryImpl#deleteServicePriceOfPlan(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public final void testDeleteServicePriceOfPlan() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.ams.billingandpayment.ports.adapter.persistance.jpa.ServicePlanRepositoryImpl#findServicePriceByCriteria(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public final void testFindServicePriceByCriteria() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.ams.billingandpayment.ports.adapter.persistance.jpa.ServicePlanRepositoryImpl#findAllServicePriceByPlanName(java.lang.String)}
     * .
     */
    @Test
    public final void testFindAllServicePriceByPlanName() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.ams.billingandpayment.ports.adapter.persistance.jpa.ServicePlanRepositoryImpl#findNextPageOfServicePrices(java.lang.String, int, int)}
     * .
     */
    @Test
    public final void testFindNextPageOfServicePrices() {
        fail("Not yet implemented");
    }

}
