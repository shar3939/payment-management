/**
 * 
 */
package com.payments.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author shar939
 *
 */
public class AccountTrx implements Serializable {

	private static final long serialVersionUID = 1L;

	private long accountId;

	private String firstName;
	private String lastName;
	private BigDecimal balance;
	private String address;
	private String currency;
	private boolean accountStatus;
	
	private long toAccountID; 
	private BigDecimal transferAmnt; 
	private String toCurrency; 
	
	

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(boolean accountStatus) {
		this.accountStatus = accountStatus;
	}

	public AccountTrx(long accountId, String firstName, String lastName, BigDecimal balance, String address,
			boolean accountStatus, String currency) {
		super();
		this.accountId = accountId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
		this.address = address;
		this.accountStatus = accountStatus;
		this.currency = currency;
	}
	
	

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public AccountTrx() {
		super();
	}

	public long getToAccountID() {
		return toAccountID;
	}

	public void setToAccountID(long toAccountID) {
		this.toAccountID = toAccountID;
	}

	public BigDecimal getTransferAmnt() {
		return transferAmnt;
	}

	public void setTransferAmnt(BigDecimal transferAmnt) {
		this.transferAmnt = transferAmnt;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}
	
	

}
