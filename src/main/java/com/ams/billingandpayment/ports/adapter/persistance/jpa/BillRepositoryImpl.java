package com.ams.billingandpayment.ports.adapter.persistance.jpa;

import com.ams.billingandpayment.domain.model.bill.Bill;
import com.ams.billingandpayment.domain.model.bill.Payment;
import com.ams.billingandpayment.domain.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Repository("BillRepository")
public class BillRepositoryImpl implements BillRepository {

    @Autowired
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Override
    public Bill createBill(Bill bill) {
        entityManager.persist(bill);
        return bill;
    }

    @Override
    public void createBills(List<Bill> bills) {
    }

    @Override
    public long deleteBill(long billNumber) {
        entityManager.remove(entityManager.find(Bill.class, billNumber));
        return 0;
    }

    @Override
    public void deleteBills(Set<Bill> bills) {
    }

    @Override
    public Bill findBill(long billNumber) {
        return entityManager.find(Bill.class, billNumber);
    }

    @Override
    public Set<Bill> findBills(long userId) {
        return null;
    }

    @Override
    public List<Bill> findBillsByPaymentStatus() {
        Query query = entityManager.createQuery("select b from Bill b where b.billPaymentRegister.billPaymentStatus = 'UNPAID'");
        List billList = query.getResultList();
        // CriteriaQuery<Bill> cq =
        // entityManager.getCriteriaBuilder().createQuery(Bill.class);
        // cq.from(Bill.class)
        return billList;
    }

    @Override
    public Payment findPayment(long paymntNumber) {
        return null;
    }

    @Override
    public Set<Payment> findPayments(long billNumber) {
        return null;
    }

    @Override
    public long updateBill(Bill bill) {
        entityManager.persist(bill);
        return 0;
    }

    @Override
    public void updateBills(Set<Bill> bills) {
    }

}
