/**
 *
 */
package com.ams.sharedkernel.domain.service.email;

/**
 * @author Raghavendra Badiger
 */
public interface ManageMail
{

	/**
	 * @param mailToParty
	 * @param sourceEmailId
	 * @param message
	 */
	void sendMail(String mailToParty, String sourceEmailId, String message);

}
