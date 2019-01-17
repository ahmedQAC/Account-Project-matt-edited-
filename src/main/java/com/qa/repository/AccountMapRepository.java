package com.qa.repository;

import java.util.Map;
import static javax.transaction.Transactional.TxType.*;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Alternative
public class AccountMapRepository implements AccountRepository {

	@Inject
	private JSONUtil util;
	
	private Map<Integer, Account> accountMap;
	private int count = 0;
	
	@Override
	@Transactional(REQUIRED)
	public String createAnAccount(String account) {
		accountMap.put(count, util.getObjectForJSON(account, Account.class));
		count++;
		return "{\"message\": \"account has been sucessfully added\"}";
	}
	
	@Override
	@Transactional(REQUIRED)
	public String getAllAccounts() {
		return util.getJSONForObject(accountMap);
	}
	
	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		String output = "{\"message\": \"account not found\"}";
		boolean countExists = accountMap.containsKey(id);
		if (countExists) {
			accountMap.remove(id);
			output = "{\"message\": \"account sucessfully deleted\"}";
		}
		return output;
	}

	@Override
	@Transactional(REQUIRED)
	public String updateAnAccount(Long id, String newAccount) {
		String output = "{\"message\": \"account not found\"}";
		
		boolean countExists = accountMap.containsKey(id);
		if (countExists) {
			accountMap.remove(id);
			createAnAccount(newAccount);
			output = "{\"message\": \"account sucessfully updated\"}";
		}
		return output;
	}


	
	@Override
	public Account findAnAccount(Long id) {
		String output = "{\"message\": \"account not found\"}";
		boolean countExists = accountMap.containsKey(id);
		if (countExists) {
			output = util.getJSONForObject(accountMap.get(id));
		}
		return null;
	}

}
