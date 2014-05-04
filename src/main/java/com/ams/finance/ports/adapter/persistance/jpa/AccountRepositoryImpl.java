package com.ams.finance.ports.adapter.persistance.jpa;

import com.ams.finance.domain.model.Account;
import com.ams.finance.domain.model.Transaction;
import com.ams.finance.domain.repository.AccountRepository;
import com.ams.sharedkernel.domain.repository.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("AccountRepository")
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Override
    public Long createOrUpdate(Account account) {
        entityManager.persist(account);
        return account.getAcntNumber();
    }

    @Override
    public void createTransaction(Transaction transaction) {
        entityManager.persist(transaction);

    }

    @Override
    public void delete(Long accountNo) {
        Account acnt = entityManager.find(Account.class, accountNo);
        entityManager.remove(acnt);

    }

    @Override
    public void deleteTransaction(Long transNumber) {
        Transaction trn = entityManager.find(Transaction.class, transNumber);
        entityManager.remove(trn);

    }

    @Override
    public List<Account> findAccountsByCustomerId(Long personId) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        TypedQuery<Account> query = entityManager.createQuery(
                "select A from Account A",
                Account.class);
        return query.getResultList();

    }

    @Override
    public Account findById(Long accountNumber) {
        return entityManager.find(Account.class, accountNumber);
    }

    @Override
    public Page<Account> findNextPageData(Page<Account> page) {

        return null;
    }

    @Override
    public Transaction findTransactionById(long transNumber) {
        return entityManager.find(Transaction.class, transNumber);

    }

    @Override
    public List<Transaction> findTransactionsByAcntNumber(long acntNumber) {
        TypedQuery<Transaction> query = entityManager
                .createQuery(
                        "select T from Transaction T where Account.acntNumber",
                        Transaction.class);
        return query.getResultList();
    }

    public Long update(Account account) {
        entityManager.persist(account);
        return account.getAcntNumber();

    }

    @Override
    public void updateTransaction(Transaction transaction) {
        entityManager.persist(transaction);

    }

}
