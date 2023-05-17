package com.vivekanandpv.jdbc;

public class Customer {
	private int id;
	private String name;
	private String email;
	private long contact;
	private String accountType;
	
	public Customer() {
		
	}
	
	public Customer(String name, String email, long contact, String accountType) {
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.accountType = accountType;
	}
	
	public Customer(int id, String name, String email, long contact, String accountType) {
		this(name, email, contact, accountType);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID: " + this.getId() + "; Name: " + this.getName() + "; Email: " + this.getEmail() + "; Contact: "
				+ this.getContact() + "; Account Type: " + this.getAccountType();
	}

}