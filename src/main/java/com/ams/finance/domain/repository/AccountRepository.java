package com.ams.finance.domain.repository;

import com.ams.finance.domain.model.Account;
import com.ams.finance.domain.model.Transaction;
import com.ams.sharedkernel.domain.repository.Repository;

import java.util.List;

public interface AccountRepository extends Repository<Account, Long>
{

	public void createTransaction(Transaction transaction);

	public void deleteTransaction(Long transNumber);

	public List<Account> findAccountsByCustomerId(Long custId);

	public Transaction findTransactionById(long transNumber);

	public List<Transaction> findTransactionsByAcntNumber(long acntNumber);

	public void updateTransaction(Transaction transaction);

}
