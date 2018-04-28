package com.qa.business.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Account;

import util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AccountDBRepository implements IAccountRepository{
	
	private static final Logger LOGGER = Logger.getLogger(AccountDBRepository.class);
	
	@PersistenceContext(unitName="primary")
	private EntityManager manager; 
	
	@Inject
	private JSONUtil jsonUtil;
	
	
	public String createAccount(String accountAsJSON) {
		jsonUtil.getObjectForJSON(accountAsJSON, Account.class);
		return accountAsJSON;
	}
	
	@Transactional(REQUIRED)
	public String addAccount(String accountAsJSON) {
		Account account = jsonUtil.getObjectForJSON(accountAsJSON, Account.class);
		manager.persist(account);
		return "{\"message\":\"Account has been succesfully added. \"}";
	}
	
	@Transactional(REQUIRED)
	public String deleteAccount(Long accountNumber) {
		String accountAsJSON= findAccount(accountNumber);
		if (accountAsJSON!=null) {
			manager.remove(accountNumber);
			return "{\"message\":\"Account has been removed \"}";
		}
		return "{\"message\":\"No such account exists. Request denied! \"}";
		
	}

	public String findAccount(Long accountNumber) {
		Account account =manager.find(Account.class, accountNumber);
		return jsonUtil.getJSONForObject(account);
	}
	
	@Transactional(REQUIRED)
	public String updateAccount(Long accountNumber, String accountAsJSON) {
		Account update = jsonUtil.getObjectForJSON(accountAsJSON, Account.class);
		Account old=jsonUtil.getObjectForJSON(findAccount(accountNumber), Account.class);
		if (update!=null&&old!=null) {
			old=updateFields(old,update);
			manager.merge(old);
			return "{\"message\":\" Account has been updated successfully \"}";
		}
		
		return "{\"message\":\"No such account exists. Request denied! \"}";
	}
	
	private Account updateFields(Account old,Account update ) {
		if (update.getFirstName()!=null) {old.setFirstName(update.getFirstName());}
		if (update.getSurname()!=null) {old.setSurname(update.getSurname());}
		return old;				
	}

	public String getAllAccounts() {
		Query query =manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts=(Collection<Account>)query.getResultList();
		return jsonUtil.getJSONForObject(accounts);
	}

	public void setManager(EntityManager manager) {
		this.manager=manager;
		
	}
	public void setUtil(JSONUtil jsonUtil) {
		this.jsonUtil=jsonUtil;
	}

}
