package com.ams.application.service.apartmentservicemanagerservice;

import static org.junit.Assert.assertEquals;

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

import com.ams.sales.application.api.service.serviceportfolio.ManageService;
import com.ams.sales.domain.model.servicecatalog.Service;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ApplicationContext.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager_Jpa",defaultRollback = true)
public class ManageServiceTest
{
	private Service		aptSrvc;

	@Autowired
	private ManageService	manageService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{}

	@Before
	public void setUp() throws Exception
	{
		aptSrvc = new Service("SRVC-02", "Monthly-Maintainance", "Monthly maintainance service");
	}

	@After
	public void tearDown() throws Exception
	{}

	@Test
	public final void testRegisterService()
	{
		assertEquals(1, manageService.getAllServices().size());
		manageService.registerService(aptSrvc);
		assertEquals(aptSrvc.getSrvcDescription(), manageService.getService("SRVC-02").getSrvcDescription());
		assertEquals(2, manageService.getAllServices().size());

	}

	@Test
	public final void testUpdateServiceDetails()
	{
		aptSrvc.modifyServiceDetails("MM", "Monthly maint srvc");
		manageService.updateServiceDetails(aptSrvc);
		assertEquals("MM", manageService.getService(aptSrvc.getSrvcCode()).getSrvcName());
	}

	@Test
	public final void testDeleteService()
	{
		assertEquals(1, manageService.getAllServices().size());
		manageService.registerService(aptSrvc);
		assertEquals(aptSrvc.getSrvcDescription(), manageService.getService("SRVC-02").getSrvcDescription());
		assertEquals(2, manageService.getAllServices().size());
		manageService.deleteService(aptSrvc.getSrvcCode());
		assertEquals(1, manageService.getAllServices().size());

	}

	@Test
	public final void testGetService()
	{
		manageService.registerService(aptSrvc);
		assertEquals(aptSrvc.getSrvcDescription(), manageService.getService("SRVC-02").getSrvcDescription());

	}

	@Test
	public final void testGetAllServices()
	{
		assertEquals(1, manageService.getAllServices().size());
		manageService.registerService(aptSrvc);
		assertEquals(aptSrvc.getSrvcDescription(), manageService.getService("SRVC-02").getSrvcDescription());
		assertEquals(2, manageService.getAllServices().size());

	}

}
