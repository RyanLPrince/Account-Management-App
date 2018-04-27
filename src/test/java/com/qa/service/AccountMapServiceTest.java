package com.qa.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.qa.business.repository.AccountMapRepository;
import com.qa.persistence.domain.Account;

public class AccountMapServiceTest {

	private Account joeBloggs;
	private Account joeBloggs2;
	private Account janeDoe;
	private AccountMapRepository repo;
	
	@Before
	public void testInit() {
		joeBloggs=new Account("Joe","Bloggs",1L);
		joeBloggs2=new Account("Joe","Bloggs",2L);
		janeDoe=new Account("Jane","Doe",1L);
		repo=new AccountMapRepository();
	}
	
	@Test
	public void addAccountTest() {
		repo.addAccount(joeBloggs);
		assertEquals(repo.getAccountMap().size(),1);
	}
	
	@Test
	public void noDuplicateAccountNumberTest() {
		repo.addAccount(joeBloggs);
		repo.addAccount(joeBloggs2);
		repo.addAccount(janeDoe);
		assertEquals(repo.getAccountMap().size(),2);
		boolean mapContents=repo.getAccountMap().containsValue(joeBloggs)&&repo.getAccountMap().containsValue(joeBloggs2)&&
				!repo.getAccountMap().containsValue(janeDoe);
		assertTrue(mapContents);
	}
	
	@Test
	public void deleteAccount() {
		repo.addAccount(joeBloggs);
		repo.addAccount(joeBloggs2);
		repo.deleteAccount(1L);
		assertEquals(repo.getAccountMap().size(),1);
		assertTrue(repo.getAccountMap().containsValue(joeBloggs2));
		
	}

}
