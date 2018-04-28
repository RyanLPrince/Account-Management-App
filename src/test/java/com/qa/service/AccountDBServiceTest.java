package com.qa.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.business.repository.AccountDBRepository;
import com.qa.persistence.domain.Account;

import util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class AccountDBServiceTest {

	@InjectMocks 
	private AccountDBRepository repo;
	
	@Mock
	private EntityManager manager;
	
	@Mock 
	Query query;
	
	private JSONUtil jsonUtil;
	
	private static final String MockObject="{\"firstName\":\"Joe\",\"surname\":\"Bloggs\",\"accountNumber\":1}";
	private static final String MockArray="[{\"firstName\":\"Joe\",\"surname\":\"Bloggs\",\"accountNumber\":1,\"generateAccountNumber\":false}]";

	
	@Before
	public void testInit() {
		jsonUtil=new JSONUtil();
		repo.setManager(manager);
		repo.setUtil(jsonUtil);
	}

	@Test
	public void addAccountTest() {
		String expected=repo.addAccount(MockObject);
		assertEquals(expected,"{\"message\":\"Account has been succesfully added. \"}");		
	}
	
	@Test
	public void deleteAccountTest() {
		String expected=repo.deleteAccount(1L);
		assertEquals(expected,"{\"message\":\"No such account exists. Request denied! \"}");		
	}
	
	@Test
	public void updateAccountTest() {
		String expected=repo.updateAccount(1L,MockObject);
		assertEquals(expected,"{\"message\":\"No such account exists. Request denied! \"}");
	}
	
	@Test
	public void getAllAccounts() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Account> accounts=new ArrayList<Account>();
		accounts.add(jsonUtil.getObjectForJSON(repo.createAccount(MockObject), Account.class));
		Mockito.when(query.getResultList()).thenReturn(accounts);
		assertEquals(MockArray,repo.getAllAccounts());
		
	}
	
}
