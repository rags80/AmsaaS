/**
 *
 */
package com.ams.billingandpayment.application.impl.serviceportfolio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import com.ams.billingandpayment.application.api.service.serviceportfolio.ManageService;
import com.ams.billingandpayment.domain.model.serviceportfolio.Service;
import com.ams.billingandpayment.domain.repository.ServiceRepository;

/**
 * @author Raghavendra Badiger
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:ApplicationContext.xml" })
public class ManageServiceImplTest_MockRepo
{

	private ServiceRepository	mockSrvcRepo;

	@Autowired
	private ManageService		manageService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		this.mockSrvcRepo = mock(ServiceRepository.class);
		ReflectionTestUtils.setField(this.manageService, "serviceRepository", this.mockSrvcRepo);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{}

	/**
	 * Test method for
	 * {@link com.ams.billingandpayment.application.impl.serviceportfolio.ManageServiceImpl#registerService(com.ams.billingandpayment.domain.model.serviceportfolio.Service)}
	 * .
	 */
	@Test
	public final void testRegisterService()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.ams.billingandpayment.application.impl.serviceportfolio.ManageServiceImpl#updateServiceDetails(com.ams.billingandpayment.domain.model.serviceportfolio.Service)}
	 * .
	 */
	@Test
	public final void testUpdateServiceDetails()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.ams.billingandpayment.application.impl.serviceportfolio.ManageServiceImpl#removeService(java.lang.String)}
	 * .
	 */
	@Test
	public final void testRemoveService()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.ams.billingandpayment.application.impl.serviceportfolio.ManageServiceImpl#getService(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetService()
	{
		Service srvcExpected = new Service("SR1", "APT MAINTANCE", "Maintainance Service");
		when(this.mockSrvcRepo.findById("SR1")).thenReturn(srvcExpected);

		assertEquals("Maintainance Service", this.manageService.getService("SR1").getSrvcDescription());
	}

	/**
	 * Test method for
	 * {@link com.ams.billingandpayment.application.impl.serviceportfolio.ManageServiceImpl#getAllServices()}
	 * .
	 */
	@Test
	public final void testGetAllServices()
	{
		Service sr1 = new Service("SR1", "APT MAINTANCE", "Maintainance Service");
		Service sr2 = new Service("SR2", "APT PLUMBING", "Plumbing Service");
		when(this.mockSrvcRepo.findAll()).thenReturn(Arrays.asList(sr1, sr2));

		List<Service> srvcList = this.manageService.getAllServices();

		for (Service srvc : srvcList)
		{
			System.out.println("Service:" + srvc.toString());
		}
		assertEquals(this.manageService.getAllServices().size(), 2);

	}

	/**
	 * Test method for
	 * {@link com.ams.billingandpayment.application.impl.serviceportfolio.ManageServiceImpl#getServicesNextPage(int, int)}
	 * .
	 */
	@Test
	public final void testGetServicesNextPage()
	{
		fail("Not yet implemented");
	}

}
