package com.rank.ccms.dto;

import java.io.Serializable;

public class BankMstDto implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7857091723711599077L;
	private int id;
	private String bankName;
	private String accountName;
	private String accountNumber;
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
