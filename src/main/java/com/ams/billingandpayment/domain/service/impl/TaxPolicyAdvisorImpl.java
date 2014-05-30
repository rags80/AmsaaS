/**
 * 
 */
package com.ams.billingandpayment.domain.service.impl;

import com.ams.billingandpayment.domain.model.bill.policy.TaxPolicy;
import com.ams.billingandpayment.domain.model.serviceportfolio.Service;
import com.ams.billingandpayment.domain.service.TaxPolicyAdvisor;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.users.domain.model.Person;

/**
 * @author Raghavendra Badiger
 * 
 */
@org.springframework.stereotype.Service("TaxPolicyAdvisor")
public class TaxPolicyAdvisorImpl implements TaxPolicyAdvisor
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ams.billingandpayment.domain.service.TaxPolicyAdvisor#
	 * adviseServiceTaxPolicy
	 * (com.ams.billingandpayment.domain.model.serviceportfolio.Service)
	 */
	@Override
	public TaxPolicy adviseServiceTaxPolicy(Service service)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ams.billingandpayment.domain.service.TaxPolicyAdvisor#
	 * adviseBillTaxPolicy(com.ams.users.domain.model.Person,
	 * com.ams.sharedkernel.domain.model.measuresandunits.Period)
	 */
	@Override
	public TaxPolicy adviseBillTaxPolicy(Person billedPerson, Period billPeriod)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
