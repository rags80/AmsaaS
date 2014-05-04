package com.ams.billingandpayment.domain.model.bill;

import com.ams.billingandpayment.domain.model.bill.BillSpecification.status;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
class BillPaymentRegister implements Serializable {

    private static final long serialVersionUID = 1L;
    private Money billCurrentBalance;
    private Money billAmountPaid;
    private status billPaymentStatus;

    public BillPaymentRegister(Money currentBalance) {
        this.billCurrentBalance = currentBalance;
        this.billAmountPaid = Money.ZERO;
        this.billPaymentStatus = status.UNPAID;
    }

	/*
     * BILL DOMAIN FUNCTIONS
	 */

    void updateBillPayment(Bill bill, Payment paymnt) {

        this.billAmountPaid = this.billAmountPaid.add(paymnt.getPaymntAmount());
        this.billCurrentBalance = bill.getBillNetAmount().subtract(this.billAmountPaid);
        paymnt.setPaymntBalance(this.billCurrentBalance.getAmount());
        this.updateBillPaymentStatus(bill);

    }

    void updateBillPaymentStatus(Bill bill) {

        this.billPaymentStatus = (this.billCurrentBalance.compareTo(Money.ZERO) <= 0) ? status.PAID : (this.billCurrentBalance.compareTo(bill.getBillNetAmount()) == 0 ? status.UNPAID : status.PARTIALLY_PAID);
    }

	/*
	 * ACCESSORS AND MUTATORS
	 */

    public Money getBillAmountPaid() {
        return this.billAmountPaid;
    }

    @Enumerated(EnumType.STRING)
    public status getBillPaymentStatus() {
        return this.billPaymentStatus;
    }

    public Money getBillCurrentBalance() {
        return this.billCurrentBalance;
    }

}
