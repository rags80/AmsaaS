/**
 * 
 */
package com.ams.sharedkernel.ports.adapter.email;

import org.springframework.stereotype.Component;

import com.ams.sharedkernel.domain.service.email.ManageMail;

/**
 * @author Raghavendra Badiger
 * 
 */
@Component("ManageMail")
public class ManageMailImpl implements ManageMail
{

	@Override
	public void sendMail(String mailToParty, String sourceEmailId, String message)
	{

	}

}
