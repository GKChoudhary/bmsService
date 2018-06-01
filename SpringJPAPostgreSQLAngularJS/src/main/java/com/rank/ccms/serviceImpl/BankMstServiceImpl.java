/**
 * 
 */
package com.rank.ccms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rank.ccms.dao.BankMstDao;
import com.rank.ccms.entities.BankMst;
import com.rank.ccms.service.BankMstService;

/**
 * @author govind
 *
 */
@Service
public class BankMstServiceImpl implements BankMstService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rank.ccms.service.BankMstService#getBankDetail()
	 */
	@Autowired
	BankMstDao bankMstDao;

	public List<BankMst> getBankDetail() {

		return bankMstDao.getBankDetail();
	}

	@Override
	public int create(BankMst bankMst) {
		// TODO Auto-generated method stub
		return bankMstDao.create(bankMst);
	}

	@Override
	public void update(BankMst bankMst) {
		// TODO Auto-generated method stub
		bankMstDao.update(bankMst);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		bankMstDao.delete(id);
	}

}
