/**
 *
 */
package com.ams.billingandpayment.ports.adapter.email;

import com.ams.sharedkernel.application.api.ManageMail;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Raghavendra Badiger
 */
@Service
public class ManageMailImpl implements ManageMail {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.ams.sharedkernel.service.ManageMail#getMailTemplate(java.lang.String
     * , java.lang.Long, java.math.BigDecimal)
     */
    @Override
    public String getMailTemplate(String string, Long billNumber, BigDecimal paymntAmount) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ams.sharedkernel.service.ManageMail#sendMail(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public void sendMail(String mailToParty, String sourceEmailId, String billPaymntSuccessMessage) {
        // TODO Auto-generated method stub

    }

}
