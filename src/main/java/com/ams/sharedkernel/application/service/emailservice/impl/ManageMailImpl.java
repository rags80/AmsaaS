package com.ams.sharedkernel.application.service.emailservice.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.sharedkernel.application.service.emailservice.ManageMail;

@Transactional
@Service("ManageMailService")
public class ManageMailImpl implements ManageMail
{

	public void sendMail(String toEmailId, String fromEmailId, String message)
	{

	}

	public String getMailTemplate(String msgTemplateName, long billNumber,
							BigDecimal paymntAmount)
	{
		return null;
	}

}
