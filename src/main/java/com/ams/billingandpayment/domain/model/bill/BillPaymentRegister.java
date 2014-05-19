package com.ams.billingandpayment.domain.model.bill;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ams.sharedkernel.domain.model.measuresandunits.Money;

/**
 * 
 * @author Raghavendra Badiger
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "T_BILLPAYMENT_REGISTER")
public class BillPaymentRegister implements Serializable
{

	public enum Status
	{
		PAID, UNPAID, PARTIALLY_PAID, OVERLY_PAID, NOT_APPLICABLE
	}

	private static final long	serialVersionUID	= 1L;

	@Id
	private long				personId;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "CurrentBalance_Amount")),
			@AttributeOverride(name = "currency",column = @Column(name = "CurrentBalance_Currency"))
	})
	private Money				billCurrentBalance;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "AmountPaid_Amount")),
			@AttributeOverride(name = "currency",column = @Column(name = "AmountPaid_Currency"))
	})
	private Money				billAmountPaid;

	@Enumerated(EnumType.STRING)
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
		this.updateBillPaymentStatus(bill);
	}

	void updateOnBillPayment(Bill bill, Payment paymnt)
	{

		this.billAmountPaid = this.billAmountPaid.add(paymnt.getPaymntAmount());
		this.billCurrentBalance = this.billCurrentBalance.subtract(this.billAmountPaid);
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
