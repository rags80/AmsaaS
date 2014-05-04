package com.ams.billingandpayment.domain.model.bill;

import com.ams.billingandpayment.domain.model.bill.policy.DiscountPolicy;
import com.ams.billingandpayment.domain.model.bill.policy.TaxPolicy;
import com.ams.billingandpayment.domain.model.servicecatalog.ServicePrice;
import com.ams.sharedkernel.domain.model.measuresandunits.Money;
import com.ams.sharedkernel.domain.model.measuresandunits.Period;
import com.ams.sharedkernel.domain.model.measuresandunits.Quantity;
import com.ams.users.domain.model.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Raghavendra Badiger
 */

@Entity
@Access(AccessType.FIELD)
@Table(name = "T_BILL")
public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;

    private Person billedPerson;
    private Date billDate;
    private Date billDueDate;
    private Period billPeriod;
    private Money billPreviousBalance;
    private Money billPenaltyAmount;
    private Money billGrossAmount;
    private Tax billTotalTax;
    private Discount billDiscountAmount;
    private Money billNetAmount;
    private BillPaymentRegister billPaymentRegister;

    @ElementCollection
    @CollectionTable(name = "T_BILLITEM", joinColumns = @JoinColumn(name = "Bill_No"))
    private List<BillItem> billItems;
    private List<Payment> billPayments;

    public Bill(Person billedPersn, Date billDate, Date billDueDate, Period billPeriod) {
        this.billedPerson = billedPersn;
        this.billDate = billDate;
        this.billDueDate = billDueDate;
        this.billPeriod = billPeriod;
        this.billPreviousBalance = Money.ZERO;
        this.billPenaltyAmount = Money.ZERO;
        this.billGrossAmount = Money.ZERO;
        this.billTotalTax = Tax.DEFAULT_TAX;
        this.billDiscountAmount = Discount.DEFAULT_DISCOUNT;
        this.billNetAmount = Money.ZERO;
        this.billPaymentRegister = new BillPaymentRegister(this.billNetAmount);
        this.billItems = new ArrayList<BillItem>();
        this.billPayments = new ArrayList<Payment>();
    }

	/*
     * Bill Domain Logic Functions
	 */

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Bill addBillItem(ServicePrice srvcPrice, Quantity qty, DiscountPolicy itemDscntPolicy, TaxPolicy itemTaxPolicy) {

        BillItem item = this.find(srvcPrice);

        if (item == null) {
            this.billItems.add(new BillItem(srvcPrice, qty, itemTaxPolicy, itemDscntPolicy));
        } else {
            item.increaseQuantity(qty, itemTaxPolicy, itemDscntPolicy);
        }

        this.recalculateBillGrossAmount();
        return this;
    }

    private BillItem find(ServicePrice sp) {
        for (BillItem item : this.billItems) {
            if (sp.equals(item.getServicePrice())) {
                return item;
            }
        }

        return null;
    }

    public Bill addBillPreviousBalance(Money prevBal) {

        this.billPreviousBalance = prevBal;
        return this;
    }

    public Bill addBillPenaltyAmount(Money pnlty) {
        this.billPenaltyAmount = pnlty;
        return this;
    }

    private void recalculateBillGrossAmount() {
        for (BillItem item : this.billItems) {
            this.billGrossAmount.add(item.getNetAmount());
        }
    }

    private void calculateBillNetAmount() {

    }

	/*
	 * Bill Accessor Functions
	 */

    public void makePayment(Payment pymnt) {
        // TODO Auto-generated method stub

    }

    public Person getBilledPerson() {
        return this.billedPerson;
    }

    public Date getBillDate() {
        return this.billDate;
    }

    public Date getBillDueDate() {
        return this.billDueDate;
    }

    public Period getBillPeriod() {
        return this.billPeriod;
    }

    public Money getBillPreviousBalance() {
        return this.billPreviousBalance;
    }

    public Money getBillPenaltyAmount() {
        return this.billPenaltyAmount;
    }

    public Collection<BillItem> getBillItems() {
        return this.billItems;
    }

    public Money getBillNetAmount() {
        return this.billNetAmount;
    }

    public Tax getBillTotalTax() {
        return this.billTotalTax;
    }

    public Discount getBillDiscountAmount() {
        return this.billDiscountAmount;
    }

    public Money getBillGrossAmount() {
        return this.billGrossAmount;
    }

    public List<Payment> getBillPayments() {
        return this.billPayments;
    }

    public BillPaymentRegister getBillPaymentRegister() {
        return this.billPaymentRegister;
    }

}
