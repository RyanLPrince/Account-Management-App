package com.qa.business.repository;

import com.qa.persistence.domain.Account;

public interface IAccountRepository {
	
	Account createAccount(String accountAsJSON);
	String addAccount(String accounAsJSON);
	String deleteAccount(Long accountNumber);
	
}
