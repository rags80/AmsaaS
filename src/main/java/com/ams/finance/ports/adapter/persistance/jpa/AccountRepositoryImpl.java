package com.ams.finance.ports.adapter.persistance.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ams.finance.domain.model.Account;
import com.ams.finance.domain.model.Transaction;
import com.ams.finance.domain.repository.AccountRepository;
import com.ams.sharedkernel.domain.repository.Page;

@Repository("AccountRepository")
public class AccountRepositoryImpl implements AccountRepository
{
	@Autowired
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager	entityManager;

	@Override
	public Long create(Account account)
	{
		this.entityManager.persist(account);
		return account.getAcntNumber();

	}

	@Override
	public void createTransaction(Transaction transaction)
	{
		this.entityManager.persist(transaction);

	}

	@Override
	public Long delete(Long accountNo)
	{
		Account acnt = this.entityManager.find(Account.class, accountNo);
		this.entityManager.remove(acnt);
		return acnt.getAcntNumber();
	}

	@Override
	public void deleteTransaction(Long transNumber)
	{
		Transaction trn = this.entityManager.find(Transaction.class, transNumber);
		this.entityManager.remove(trn);

	}

	@Override
	public List<Account> findAccountsByCustomerId(Long personId)
	{
		return null;
	}

	@Override
	public List<Account> findAll()
	{
		TypedQuery<Account> query = this.entityManager.createQuery(
														"select A from Account A",
														Account.class);
		return query.getResultList();

	}

	@Override
	public Account findById(Long accountNumber)
	{
		return this.entityManager.find(Account.class, accountNumber);
	}

	@Override
	public Page<Account> findNextPageData(Page<Account> page)
	{

		return null;
	}

	@Override
	public Transaction findTransactionById(long transNumber)
	{
		return this.entityManager.find(Transaction.class, transNumber);

	}

	@Override
	public List<Transaction> findTransactionsByAcntNumber(long acntNumber)
	{
		TypedQuery<Transaction> query = this.entityManager
												.createQuery(
															"select T from Transaction T where Account.acntNumber",
															Transaction.class);
		return query.getResultList();
	}

	@Override
	public Long update(Account account)
	{
		this.entityManager.merge(account);
		this.entityManager.flush();
		return account.getAcntNumber();

	}

	@Override
	public void updateTransaction(Transaction transaction)
	{
		this.entityManager.persist(transaction);

	}

}
