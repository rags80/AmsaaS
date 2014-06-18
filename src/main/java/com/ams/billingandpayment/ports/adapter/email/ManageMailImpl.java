package com.ams.billingandpayment.ports.adapter.email;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ams.sharedkernel.domain.service.email.ManageMail;

/**
 * @author Raghavendra Badiger
 */
@Service
public class ManageMailImpl implements ManageMail
{

	@Override
	public String getMailTemplate(String string, Long billNumber, BigDecimal paymntAmount)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendMail(String mailToParty, String sourceEmailId, String billPaymntSuccessMessage)
	{

	}

}
