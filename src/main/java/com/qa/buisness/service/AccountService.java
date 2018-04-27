package com.qa.buisness.service;

import javax.inject.Inject;

import com.qa.business.repository.IAccountRepository;

public class AccountService implements IAccountService {
	
	@Inject
	private IAccountRepository repo;
	
	
	public String getAllAccounts() {
		return repo.getAllAccounts();
	}

	public String findAccount(Long accountNumber) {
		return repo.findAccount(accountNumber);
	}

	public String createAccount(String accountAsJSON) {
		return repo.createAccount(accountAsJSON);
	}

	public String deleteAccount(Long accountNumber) {
		return repo.deleteAccount(accountNumber);
	}

	public String addAccount(String movieAsJSON) {
		return repo.addAccount(movieAsJSON);
	}

	public String updateAccount(Long accountNumber,String accountAsJSON) {
		return repo.updateAccount(accountNumber, accountAsJSON);
	}

}
