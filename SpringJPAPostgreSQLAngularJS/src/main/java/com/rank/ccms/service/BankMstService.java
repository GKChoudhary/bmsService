package com.rank.ccms.service;

import java.util.List;

import com.rank.ccms.entities.BankMst;

public interface BankMstService {
	public List<BankMst> getBankDetail();

	public int create(BankMst bankMst);

	public void update(BankMst bankMst);

	public void delete(int id);
}
