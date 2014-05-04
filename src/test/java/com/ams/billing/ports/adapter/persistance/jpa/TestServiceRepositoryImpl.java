/**
 *
 */
package com.ams.billing.ports.adapter.persistance.jpa;

import com.ams.billingandpayment.domain.model.servicecatalog.Service;
import com.ams.billingandpayment.domain.repository.ServiceRepository;
import com.ams.sharedkernel.domain.repository.Page;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @author Raghavendra Badiger
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "txManager_Jpa", defaultRollback = true)
public class TestServiceRepositoryImpl {
    private Service srvc;

    @Autowired
    private ServiceRepository srvcRepo;

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
        this.srvc = new Service("SRVC-X", "MAINTSRVC", "Maintainance Service");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.ams.billingandpayment.ports.adapter.persistance.jpa.ServiceRepositoryImpl#createOrUpdate(com.ams.billingandpayment.domain.model.servicecatalog.Service)}
     * .
     */
    @Test
    public final void testCreateOrUpdate() {
        assertEquals(0, this.srvcRepo.findAll().size());
        this.srvcRepo.createOrUpdate(this.srvc);
        assertEquals(1, this.srvcRepo.findAll().size());
        String name = this.srvc.getSrvcName();
        String descr = this.srvc.getSrvcDescription();
        this.srvc.modifyServiceDetails("PLUMBSRVC", "Plumbing Service");
        this.srvcRepo.createOrUpdate(this.srvc);
        this.srvc = this.srvcRepo.findById(this.srvc.getSrvcCode());
        assertEquals(name, this.srvc.getSrvcName());
    }

    /**
     * Test method for
     * {@link com.ams.billingandpayment.ports.adapter.persistance.jpa.ServiceRepositoryImpl#delete(java.lang.String)}
     * .
     */
    @Test
    public final void testDelete() {
        assertEquals(0, this.srvcRepo.findAll().size());
        this.srvcRepo.createOrUpdate(this.srvc);
        assertEquals(1, this.srvcRepo.findAll().size());
        this.srvcRepo.delete(this.srvc.getSrvcCode());
        assertEquals(0, this.srvcRepo.findAll().size());

    }

    /**
     * Test method for
     * {@link com.ams.billingandpayment.ports.adapter.persistance.jpa.ServiceRepositoryImpl#findById(java.lang.String)}
     * .
     */
    @Test
    public final void testFindById() {
        this.srvcRepo.createOrUpdate(this.srvc);
        assertEquals(1, this.srvcRepo.findAll().size());
        Service service = this.srvcRepo.findById(this.srvc.getSrvcCode());
        assertTrue(this.srvc.equals(service));
    }

    /**
     * Test method for
     * {@link com.ams.billingandpayment.ports.adapter.persistance.jpa.ServiceRepositoryImpl#findAll()}
     * .
     */
    @Test
    public final void testFindAll() {
        int size = this.srvcRepo.findAll().size();
        this.srvcRepo.createOrUpdate(this.srvc);
        assertFalse(size == this.srvcRepo.findAll().size());

    }

    /**
     * Test method for
     * {@link com.ams.billingandpayment.ports.adapter.persistance.jpa.ServiceRepositoryImpl#findNextPageData(com.ams.sharedkernel.domain.repository.Page)}
     * .
     */
    @Test
    public final void testFindNextPageData() {
        Page<Service> sp = new Page<Service>(0, 2);
        assertTrue(sp.getPageDataList().size() == 0);
        sp = this.srvcRepo.findNextPageData(sp);
        assertEquals("SRVC01", sp.getPageDataList().get(0).getSrvcCode());
        assertTrue(sp.getPageDataList().size() > 0);
    }
}
