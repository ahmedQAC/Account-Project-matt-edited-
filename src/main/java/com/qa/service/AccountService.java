package com.qa.service;

import com.qa.domain.Account;

public interface AccountService {
	
	String getAllAccounts();
	
	Account findAnAccount(Long id);
	
	String createAnAccount(String account);
	
	String updateAnAccount(Long id, String account);
	
	String deleteAccount(Long id);
	
//	void createAccount(Account userAccount);
//	
//	void removeAccount(Integer accountToRemove);
//	
//	String getAllAccounts( String firstNameOfAccount);
}
