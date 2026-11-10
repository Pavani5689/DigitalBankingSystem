package com.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {
	private int id;
	private long accountNumber;
	private String type;
	private BigDecimal amount;
	private BigDecimal balanceAfter;
	private String description;
	private Timestamp txnDate;

	public Transaction() {
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public long getAccountNumber() { return accountNumber; }
	public void setAccountNumber(long accountNumber) { this.accountNumber = accountNumber; }
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }
	public BigDecimal getAmount() { return amount; }
	public void setAmount(BigDecimal amount) { this.amount = amount; }
	public BigDecimal getBalanceAfter() { return balanceAfter; }
	public void setBalanceAfter(BigDecimal balanceAfter) { this.balanceAfter = balanceAfter; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	public Timestamp getTxnDate() { return txnDate; }
	public void setTxnDate(Timestamp txnDate) { this.txnDate = txnDate; }
}
