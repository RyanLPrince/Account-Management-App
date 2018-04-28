package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
	
	private String firstName;
	private String surname;
	@Id
	private Long accountNumber; 
	private boolean generateAccountNumber=false;
	
	public Account() {
		
	}
	
	public Account(String firstName, String surname, Long accountNumber) {
		this.firstName=firstName;
		this.surname=surname;
		this.accountNumber=accountNumber;
	}
	
	public Account(String firstName,String surname) {
		this.firstName=firstName;
		this.surname=surname;
		this.accountNumber=null;
		this.generateAccountNumber=true;
	}

	public Long getAccountNumber() {
		return this.accountNumber;
	}
	
	public boolean getGenerateAccountNumber() {
		return this.generateAccountNumber;
	}
	
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber=accountNumber;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname=surname;
	}
}
