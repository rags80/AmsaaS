package com.ams.billingandpayment.domain.repository;

import java.util.List;
import java.util.Set;

import com.ams.billingandpayment.domain.model.bill.Bill;
import com.ams.billingandpayment.domain.model.bill.Payment;

public interface BillRepository
{

	public Bill createBill(Bill bill);

	public void createBills(List<Bill> bills);

	public long deleteBill(long billNumber);

	public void deleteBills(Set<Bill> bills);

	public Bill findBill(long billNumber);

	public Set<Bill> findBills(long userId);

	public List<Bill> findBillsByPaymentStatus();

	public Payment findPayment(long paymntNumber);

	public Set<Payment> findPayments(long billNumber);

	public long updateBill(Bill bill);

	public void updateBills(Set<Bill> bills);

}
