package com.qa.service;

import javax.inject.Inject;

import com.qa.domain.Account;
import com.qa.repository.AccountRepository;

public class AccountServiceImpl implements AccountService{
	
	@Inject
	private AccountRepository repo;

	@Override
	public String getAllAccounts() {
		return repo.getAllAccounts();
	}

	@Override
	public Account findAnAccount(Long id) {
		return repo.findAnAccount(id);
	}

	@Override
	public String createAnAccount(String account) {
		return repo.createAnAccount(account);
	}

	@Override
	public String updateAnAccount(Long id, String account) {
		return repo.updateAnAccount(id, account);
	}

	@Override
	public String deleteAccount(Long id) {
		return repo.deleteAccount(id);
	}
	
	public void setRepo(AccountRepository repo) {
		this.repo = repo;
	}




}
