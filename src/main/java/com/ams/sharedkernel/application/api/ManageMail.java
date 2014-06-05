/**
 *
 */
package com.ams.sharedkernel.application.api;

import java.math.BigDecimal;

/**
 * @author Raghavendra Badiger
 */
public interface ManageMail
{

	/**
	 * @param string
	 * @param billNumber
	 * @param paymntAmount
	 * @return
	 */
	String getMailTemplate(String string, Long billNumber, BigDecimal paymntAmount);

	/**
	 * @param mailToParty
	 * @param sourceEmailId
	 * @param billPaymntSuccessMessage
	 */
	void sendMail(String mailToParty, String sourceEmailId, String billPaymntSuccessMessage);

}
