package com.qa.business.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
		return "{\"message\":\"Account has been succesfully created.\"}";
	}
	
	@Transactional(REQUIRED)
	public String addAccount(String accountAsJSON) {
		Account account = jsonUtil.getObjectForJSON(accountAsJSON, Account.class);
		manager.persist(account);
		return "{\"message\":\"Account has been succesfully added. \"}";
	}
	
	@Transactional(REQUIRED)
	public String deleteAccount(Long accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public String findAccount(Long accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(REQUIRED)
	public String updateAccount(Long accountNumber, String accountAsJSON) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setManager(EntityManager manager) {
		this.manager=manager;
		
	}
	public void setUtil(JSONUtil jsonUtil) {
		this.jsonUtil=jsonUtil;
	}

}
