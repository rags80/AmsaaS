/**
 * 
 */
package com.ams.billingandpayment.application.impl.serviceportfolio;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ams.billingandpayment.application.api.service.serviceportfolio.ManageService;
import com.ams.billingandpayment.domain.model.serviceportfolio.Service;

/**
 * @author Raghavendra Badiger
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:context-application.xml", "classpath:context-infrastructure-persistance.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true,transactionManager = "txManager_Jpa")
public class ManageServiceImplTest_Repo
{

	@Autowired
	private ManageService	manageService;

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
	{}

	/**
	 * Test method for
	 * {@link com.ams.billingandpayment.application.impl.serviceportfolio.ManageServiceImpl#getAllServices()}
	 * .
	 */
	@Test
	public final void testGetAllServices()
	{
		int size = this.manageService.getAllServices().size();
		System.out.println("Size:" + size);
		Service srvc = this.manageService.getService("SRVC-01");
		System.out.println("Service01:" + srvc);

		srvc = this.manageService.getService("SRVC02");
		System.out.println("Service02:" + srvc);

		srvc = new Service("SRVC03", "ELECTRNSRVC", "Electrian Service");
		this.manageService.registerService(srvc);

		srvc = this.manageService.getService("SRVC-01");
		System.out.println("Service01:" + srvc);

		srvc = this.manageService.getService("SRVC03");
		System.out.println("Service03:" + srvc);
	}

}
