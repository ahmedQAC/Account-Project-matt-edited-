package com.qa.repository;

import com.qa.domain.Account;

public interface AccountRepository {
	
	String getAllAccounts();
	
	Account findAnAccount(Long id);
	
	String createAnAccount(String account);
	
	String updateAnAccount(Long id, String account);
	
	String deleteAccount(Long id);
	
}
