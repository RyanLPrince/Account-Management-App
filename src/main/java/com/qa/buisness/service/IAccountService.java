package com.qa.buisness.service;

public interface IAccountService {
	
	String getAllAccounts();	
	String findAccount(Long accountNumber);
	String createAccount(String accountAsJSON);
	String deleteAccount(Long accountNumber);
	String addAccount(String accountAsJSON);
	String updateAccount(Long accountNumber, String accountAsJSON);
}
