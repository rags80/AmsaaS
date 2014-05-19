package com.ams.billingandpayment.domain.model.bill;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.ams.sharedkernel.domain.model.measuresandunits.Money;

/**
 * @author Raghavendra Badiger
 * 
 */
@Entity
public class BillPaymentRegister implements Serializable
{

	public enum Status
	{
		PAID, UNPAID, PARTIALLY_PAID, OVERLY_PAID, NOT_APPLICABLE
	}

	private static final long	serialVersionUID	= 1L;
	@Id
	private long				personId;
	private Money				billCurrentBalance;
	private Money				billAmountPaid;
	private Status				billPaymentStatus;

	public BillPaymentRegister(long persnId)
	{
		this.personId = persnId;
		this.billCurrentBalance = Money.ZERO;
		this.billAmountPaid = Money.ZERO;
		this.billPaymentStatus = Status.NOT_APPLICABLE;

	}

	/*
	 * 
	 * BILL DOMAIN FUNCTIONS
	 */

	void updateCurrentBalance(Bill bill)
	{
		this.billCurrentBalance = bill.getBillNetAmount();
		if (!this.billCurrentBalance.equals(Money.ZERO))
		{
			this.billPaymentStatus = Status.UNPAID;
		}
	}

	void updateOnBillPayment(Bill bill, Payment paymnt)
	{

		this.billAmountPaid = this.billAmountPaid.add(paymnt.getPaymntAmount());
		this.billCurrentBalance = this.billCurrentBalance.subtract(this.billAmountPaid);
		paymnt.setPaymntBalance(this.billCurrentBalance.getAmount());
		this.updateBillPaymentStatus(bill);

	}

	private void updateBillPaymentStatus(Bill bill)
	{

		this.billPaymentStatus = (this.billCurrentBalance.compareTo(Money.ZERO) == 0) ? Status.PAID : (this.billCurrentBalance.compareTo(bill.getBillNetAmount()) == 0 ? Status.UNPAID : (this.billCurrentBalance.compareTo(Money.ZERO) < 0) ? Status.OVERLY_PAID : Status.PARTIALLY_PAID);
	}

	/*
	 * ACCESSORS AND MUTATORS
	 */

	public Money getBillAmountPaid()
	{
		return this.billAmountPaid;
	}

	public Status getBillPaymentStatus()
	{
		return this.billPaymentStatus;
	}

	public Money getBillCurrentBalance()
	{
		return this.billCurrentBalance;
	}

}
