package com.ams.finance.application.api;

import java.util.Set;

import com.ams.finance.application.api.servicedata.AccountServiceData;
import com.ams.finance.domain.model.Transaction;
import com.ams.sharedkernel.exception.ServiceException;

public interface ManageAccount
{
	/******************** ACCOUNT RELATED OPERATIONS *******************************/

	public void createAccount(AccountServiceData acntServiceData)
														throws ServiceException;

	public void updateAccountDetails(AccountServiceData acntServiceData)
															throws ServiceException;

	public void deleteAccount(long acntNumber) throws ServiceException;

	public AccountServiceData getAccountDetail(long acntNumber)
													throws ServiceException;

	public Set<AccountServiceData> getAccounts(long persnId)
													throws ServiceException;

	/******************** ACCOUNT TRANSACTION RELATED OPERATIONS *******************************/

	public void createTransaction(Transaction transctn);

	public void updateTransactionDetails(Transaction transctn);

	public void deleteTransaction(long transNumber);

	public Transaction getTransactionDetails(long transNumber);

	public Set<Transaction> getTransactions(int acntNumber);

}
