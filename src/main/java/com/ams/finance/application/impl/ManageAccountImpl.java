package com.ams.finance.application.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.finance.application.api.ManageAccount;
import com.ams.finance.application.api.datamapper.AccountServiceDataAssembler;
import com.ams.finance.application.api.servicedata.AccountServiceData;
import com.ams.finance.domain.model.Account;
import com.ams.finance.domain.model.Transaction;
import com.ams.finance.domain.repository.AccountRepository;
import com.ams.sharedkernel.domain.service.exception.ServiceException;
import com.ams.users.domain.model.Person;
import com.ams.users.domain.repository.PersonRepository;

@Transactional
@Service("ManageAccountService")
public class ManageAccountImpl implements ManageAccount
{

	@Autowired
	private AccountRepository			accountRepository;
	@Autowired
	private PersonRepository				personRepository;
	@Autowired
	private AccountServiceDataAssembler	accountServiceDataAssembler;

	/**
	 * *************** ACCOUNT RELATED BUSINESS FUNCTIONS
	 * ***********************
	 */

	@Override
	public void createAccount(AccountServiceData acntServiceData)
														throws ServiceException
	{

		Person p = this.personRepository
									.findById(acntServiceData.getPersonId());
		if (p == (null))
		{
			throw new ServiceException("The User with id :"
					+ acntServiceData.getPersonId()
					+ ", to whom account is to be set up, doesn't exist");
		}

		Account acnt = this.accountServiceDataAssembler.getAccount(
														acntServiceData, p);
		this.accountRepository.create(acnt);
	}

	/**
	 * *************** ACCOUNT TRANSACTION RELATED BUSINESS FUNCTIONS
	 * ***********************
	 */

	@Override
	public void createTransaction(Transaction transctn)
	{

		this.accountRepository.createTransaction(transctn);
	}

	@Override
	public void deleteAccount(long acntNumber) throws ServiceException
	{

		this.accountRepository.delete(acntNumber);

	}

	@Override
	public void deleteTransaction(long transNumber)
	{
		this.accountRepository.deleteTransaction(transNumber);
	}

	@Override
	public AccountServiceData getAccountDetail(long acntNumber)
													throws ServiceException
	{

		Account acnt = this.accountRepository.findById(acntNumber);
		if (acnt == null)
		{
			throw new ServiceException("The requested Account: " + acntNumber
					+ " doesn't exist");
		}

		AccountServiceData asd = this.accountServiceDataAssembler
														.getAccountServiceData(acnt);

		return asd;
	}

	@Override
	public Set<AccountServiceData> getAccounts(long persnId)
													throws ServiceException
	{

		@SuppressWarnings("unchecked")
		Set<Account> acnts = (Set<Account>) this.accountRepository
														.findAccountsByCustomerId(persnId);

		if (acnts == null)
		{
			throw new ServiceException(
									"There are no accounts set up for the User with id: "
											+ persnId);
		}

		Set<AccountServiceData> asds = this.accountServiceDataAssembler
															.getAccountServiceDataSet(acnts);
		return asds;
	}

	@Override
	public Transaction getTransactionDetails(long transNumber)
	{

		return this.accountRepository.findTransactionById(transNumber);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Transaction> getTransactions(int acntNumber)
	{
		return (Set<Transaction>) this.accountRepository
												.findTransactionsByAcntNumber(acntNumber);
	}

	@Override
	public void updateAccountDetails(AccountServiceData acntServiceData)
															throws ServiceException
	{
		/****** CAN THIS BE COMBINED WITH CREATE OPERATION?? ***********/

		Person p = this.personRepository
									.findById(acntServiceData.getPersonId());
		if (p == null)
		{
			throw new ServiceException(
									"The User with id :"
											+ acntServiceData.getPersonId()
											+ ",whose account details are to be modified, doesn't exist");
		}

		Account acnt = this.accountServiceDataAssembler.getAccount(
														acntServiceData, p);
		this.accountRepository.update(acnt);

	}

	@Override
	public void updateTransactionDetails(Transaction transctn)
	{
		this.accountRepository.updateTransaction(transctn);
	}

}
