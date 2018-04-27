package com.qa.business.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import com.qa.persistence.domain.Account;

import util.JSONUtil;

@ApplicationScoped
@Alternative
public class AccountMapRepository implements IAccountRepository {
	
	private JSONUtil jsonUtil;
	
	Map<Long, Account> accountMap;
	
	public AccountMapRepository() {
		accountMap=new HashMap<Long,Account>();
		jsonUtil=new JSONUtil();
	}
	
	public String createAccount(String accountAsJSON) {
		return "{\"message\":\"Account has been succesfully created.\"}";
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

	public String findAccount(Long accountNumber) {
		if (accountMap.containsKey(accountNumber)) {
			return jsonUtil.getJSONForObject(accountMap.get(accountNumber));
		}
		else {
			return "{\"message\":\"No such account exists. Request denied! \"}";
		}
		
	}

	public String updateAccount(Long accountNumber, String updateAccountAsJSON) {
		
		if (accountMap.containsKey(accountNumber)) {
			Account oldAccount =(accountMap.get(accountNumber));			
			return updateFields(oldAccount,jsonUtil.getObjectForJSON(updateAccountAsJSON, Account.class) );
		}
		else {
			return "{\"message\":\"No such account exists. Request denied! \"}";
		}
	}

	private String updateFields(Account oldAccount,Account updateAccount ) {
		if (updateAccount.getFirstName()!=null) {oldAccount.setFirstName(updateAccount.getFirstName());}
		if (updateAccount.getSurname()!=null) {oldAccount.setSurname(updateAccount.getSurname());}
		return jsonUtil.getJSONForObject(oldAccount);				
	}

	public String getAllAccounts() {
		
		return jsonUtil.getJSONForObject(accountMap);
	}
	

}
