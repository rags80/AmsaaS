package com.ams.finance.application.api;

import com.ams.finance.application.api.servicedata.AccountServiceData;
import com.ams.finance.domain.model.Transaction;
import com.ams.sharedkernel.application.api.exception.ServiceException;

import java.util.Set;

public interface ManageAccount {
    /**
     * ***************** ACCOUNT RELATED OPERATIONS ******************************
     */

    public void createAccount(AccountServiceData acntServiceData)
            throws ServiceException;

    /**
     * ***************** ACCOUNT TRANSACTION RELATED OPERATIONS ******************************
     */

    public void createTransaction(Transaction transctn);

    public void deleteAccount(long acntNumber) throws ServiceException;

    public void deleteTransaction(long transNumber);

    public AccountServiceData getAccountDetail(long acntNumber)
            throws ServiceException;

    public Set<AccountServiceData> getAccounts(long persnId)
            throws ServiceException;

    public Transaction getTransactionDetails(long transNumber);

    public Set<Transaction> getTransactions(int acntNumber);

    public void updateAccountDetails(AccountServiceData acntServiceData)
            throws ServiceException;

    public void updateTransactionDetails(Transaction transctn);

}
