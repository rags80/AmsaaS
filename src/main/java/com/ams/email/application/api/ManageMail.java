package com.ams.email.application.api;

import java.math.BigDecimal;

public interface ManageMail
{

	public void sendMail(String toEmailId, String fromEmailId, String message);

	public String getMailTemplate(String msgTemplateName, long billNumber,
							BigDecimal paymntAmount);

}
