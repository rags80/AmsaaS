package com.ams.billingandpayment.domain.model.service;

public interface ServiceRateRule
{
	public boolean isApplicable(ServiceSubscription subscrptn);

	public void apply(ServiceSubscription subscrptn);
}
