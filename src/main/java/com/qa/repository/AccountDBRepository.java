package com.qa.repository;

import java.util.Collection;
import static javax.transaction.Transactional.TxType.*;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AccountDBRepository implements AccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;
	
	@Override
	public String getAllAccounts() {
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}

	@Override
	@Transactional(REQUIRED)
	public String createAnAccount(String account) {
		Account aAccount = util.getObjectForJSON(account, Account.class);
		manager.persist(aAccount);
		return "{\"message\": \"account has been sucessfully added\"}";
	}
	
	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		Account accountInDB = findAnAccount(id);
		if (accountInDB != null) {
			manager.remove(accountInDB);
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}
	
	@Override
	public Account findAnAccount(Long id) {
		return manager.find(Account.class, id);
	}


	@Override
	public String updateAnAccount(Long id, String newAccount) {
		String output = "{\"message\": \"account not found\"}";
		Account accountInDB = findAnAccount(id);
		if (accountInDB != null) {
			deleteAccount(id);
			createAnAccount(newAccount);
			output = "{\"message\": \"account sucessfully updated\"}";
		}
		return output;
	}
	
	public void setUtil(JSONUtil util) {
		this.util = util;
	}
		
}
