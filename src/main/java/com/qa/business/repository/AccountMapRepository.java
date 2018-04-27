package com.qa.business.repository;

import java.util.HashMap;
import java.util.Map;

import com.qa.persistence.domain.Account;

import util.JSONUtil;

public class AccountMapRepository implements IAccountRepository {
	
	private JSONUtil jsonUtil;
	
	Map<Long, Account> accountMap;
	
	public AccountMapRepository() {
		accountMap=new HashMap<Long,Account>();
	}
	
	public Account createAccount(String accountAsJSON) {
		return jsonUtil.getObjectForJSON(accountAsJSON, Account.class);
	}

	public String addAccount(String accountAsJSON) {
		Account account=jsonUtil.getObjectForJSON(accountAsJSON, Account.class);
		if (account.getAccountNumber()==null && account.getGenerateAccountNumber()==true) {
			for (Long i=1L;;i++) {
				if (accountMap.containsKey(i)){
					continue;
				}
				else {
					account.setAccountNumber(i);
					Long accountNumber=account.getAccountNumber();
					accountMap.put(accountNumber, account);
					return "{\"message\":\"Account has been succesfully added. \"}";
				}
			}
		}

		Long accountNumber=account.getAccountNumber();		
		if (!accountMap.containsKey(accountNumber)){
			accountMap.put(accountNumber, account);
			return "{\"message\":\"Account has been succesfully added. \"}";
		}
		else {
			return "{\"message\":\"An account with this Account number already exists! Request denied. \"}";
		}
	}
	public String addAccount(Account account) {
		if (account.getAccountNumber()==null && account.getGenerateAccountNumber()==true) {
			for (Long i=1L;;i++) {
				if (accountMap.containsKey(i)){
					continue;
				}
				else {
					account.setAccountNumber(i);
					Long accountNumber=account.getAccountNumber();
					accountMap.put(accountNumber, account);
					return "{\"message\":\"Account has been succesfully added. \"}";
				}
			}
		}

		Long accountNumber=account.getAccountNumber();		
		if (!accountMap.containsKey(accountNumber)){
			accountMap.put(accountNumber, account);
			return "{\"message\":\"Account has been succesfully added. \"}";
		}
		else {
			return "{\"message\":\"An account with this Account number already exists! Request denied. \"}";
		}
	}
	public String deleteAccount(Long accountNumber) {
		if (accountMap.containsKey(accountNumber)) {
			accountMap.remove(accountNumber);
			return "{\"message\":\"Account has been removed \"}";
		}
		else {
			return "{\"message\":\"No such account exists. Request denied! \"}";
		}
		
	}	

	public Map<Long,Account> getAccountMap() {
		return this.accountMap;
	}

}
