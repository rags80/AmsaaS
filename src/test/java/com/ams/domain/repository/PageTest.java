package com.ams.domain.repository;

import com.ams.billingandpayment.domain.model.service.Service;
import com.ams.sharedkernel.domain.repository.Page;

public class PageTest
{

	public static void main(String[] args)
	{

		Page<Service> serviceModelPage = new Page<Service>(0, 100);
		System.out.println("Next index is:" + serviceModelPage.nextIndexIs());
		System.out.println("Starting index is:" + serviceModelPage.getCurrentIndex());
		System.out.println("Model List is:" + serviceModelPage.getPageDataList());
	}
}
