package com.qa.persistence.domain;

public class Account {
	
	private String firstName;
	private String surname;
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
}
