package com.qa.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AccountMapServiceTest {

	private Account joeBloggs;
	private Account joeBloggs2;
	private Account janeDoe;
	private AccountMapService service;
	
	@Before
	public void testInit() {
		joeBloggs=new Account("Joe","Bloggs",1);
		joeBloggs2=new Account("Joe","Bloggs",2);
		janeDoe=new Account("Jane","Doe","1");
		service=new AccountMapService();
	}
	
	@Test
	public void addAccountTest() {
		service.addAccount(joeBloggs);
		assertEquals(service.getAccountMap().size(),1);
	}
	
	@Test
	public void noDuplicateAccountNumberTest() {
		service.addAccount(joeBloggs);
		service.addAccount(joeBloggs2);
		service.addAccount(janeDoe);
		assertEquals(service.getAccountMap().size(),2);
		boolean mapContents=service.getAccountMap().contains(joeBloggs)&&service.getAccountMap().contains(joeBloggs2)&&
				!service.getAccountMap().contains(janeDoe);
		assertTrue(mapContents);
	}
	
	@Test
	public void deleteAccount() {
		service.addAccount(joeBloggs);
		service.addAccount(janeDoe);
		service.deleteAccount(joeBloggs);
		assertEquals(service.getAccountMap().size(),1);
		assertTrue(service.getAccountMap().contains(janeDoe));
		
	}

}
