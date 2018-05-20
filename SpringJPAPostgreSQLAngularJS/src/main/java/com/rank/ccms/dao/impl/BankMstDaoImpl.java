package com.rank.ccms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rank.ccms.dao.BankMstDao;
import com.rank.ccms.entities.BankMst;

@Transactional
@Repository("bankMstDao")
public class BankMstDaoImpl implements BankMstDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<BankMst> getBankDetail() {
		List<BankMst> bankDetail = null;
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(BankMst.class);
		bankDetail = criteria.list();
		return bankDetail;
	}

}
