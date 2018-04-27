package com.qa.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.qa.business.repository.AccountMapRepository;
import com.qa.persistence.domain.Account;

import util.JSONUtil;

public class AccountMapServiceTest {

	private Account joeBloggs;
	private Account joeBloggs2;
	private Account janeDoe;
	private AccountMapRepository repo;
	
	private JSONUtil jsonUtil;
	
	@Before
	public void testInit() {
		joeBloggs=new Account("Joe","Bloggs",1L);
		joeBloggs2=new Account("Joe","Bloggs",2L);
		janeDoe=new Account("Jane","Doe",1L);
		repo=new AccountMapRepository();
		jsonUtil=new JSONUtil();
	}
	
	@Test
	public void addAccountTest() {
		repo.addAccount(joeBloggs);
		assertEquals(repo.getAccountMap().size(),1);
	}
	
	@Test 
	public void JSONTest() {
		String janeDoeAsJSON=("{\"firstName\":\"Jane\",\"surname\":\"Doe\",\"accountNumber\":1,\"generateAccountNumber\":false}");
		String actual=jsonUtil.getJSONForObject(janeDoe);
		assertEquals(janeDoeAsJSON,actual);
		
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
	@Test
	public void autoGenerateAccountNumberTest() {
		repo.addAccount(joeBloggs);
		repo.addAccount("{\"firstName\":\"Jane\",\"surname\":\"Doe\",\"accountNumber\":null,\"generateAccountNumber\":true}");
		assertEquals(repo.getAccountMap().size(),2);
		repo.addAccount(joeBloggs2);
		assertEquals(repo.getAccountMap().size(),2);
	}
	@Test
	public void findAccountTest() {
		repo.addAccount(joeBloggs);
		System.out.println(repo.findAccount(1L));
		assertEquals(repo.findAccount(2L),"{\"message\":\"No such account exists. Request denied! \"}");
		assertEquals(repo.findAccount(1L),"{\"firstName\":\"Joe\",\"surname\":\"Bloggs\",\"accountNumber\":1,\"generateAccountNumber\":false}");
		
	}

}
