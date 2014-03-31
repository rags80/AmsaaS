package com.ams.email.application.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.email.application.api.ManageMail;

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
