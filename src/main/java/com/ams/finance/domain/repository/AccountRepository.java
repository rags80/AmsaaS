package com.ams.finance.domain.repository;

import java.util.List;

import com.ams.finance.domain.model.Account;
import com.ams.finance.domain.model.Transaction;
import com.ams.sharedkernel.domain.repository.Repository;

public interface AccountRepository extends Repository<Account, Long>
{

	public List<Account> findAccountsByCustomerId(Long custId);

	public void createTransaction(Transaction transaction);

	public void updateTransaction(Transaction transaction);

	public void deleteTransaction(Long transNumber);

	public Transaction findTransactionById(long transNumber);

	public List<Transaction> findTransactionsByAcntNumber(long acntNumber);

}
