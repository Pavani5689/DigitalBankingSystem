package com.model;

import java.math.BigDecimal;

public class Customer {
	private int id;
	private String name;
	private String email;
	private String password;
	private int age;
	private String gender;
	private String mobile;
	private String address;
	private long accountNumber;
	private BigDecimal balance = BigDecimal.ZERO;

	public Customer() {
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }
	public String getGender() { return gender; }
	public void setGender(String gender) { this.gender = gender; }
	public String getMobile() { return mobile; }
	public void setMobile(String mobile) { this.mobile = mobile; }
	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }
	public long getAccountNumber() { return accountNumber; }
	public void setAccountNumber(long accountNumber) { this.accountNumber = accountNumber; }
	public BigDecimal getBalance() { return balance; }
	public void setBalance(BigDecimal balance) { this.balance = balance; }
}
