package com.rank.ccms.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rank.ccms.dao.EmployeeLoginDao;
import com.rank.ccms.dto.EmployeeMstDto;
import com.rank.ccms.entities.EmployeeMst;

@Transactional
@Repository("employeeLoginDao")
public class EmployeeLoginDaoImpl implements EmployeeLoginDao{

	private static final Logger logger = Logger.getLogger(EmployeeLoginDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public EmployeeMstDto doLogin(String loginId, String password) {
		Session session = null;
		EmployeeMstDto employeeMstDto = null;

		try{
			session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(EmployeeMst.class,"EMP");
			criteria.createAlias("EMP.empTypId", "EMPTYPE");
			criteria.add(Restrictions.eq("EMP.loginId", loginId))
			.add(Restrictions.eq("EMP.loginPasswd", password))

			.setProjection(Projections.projectionList()
					.add(Projections.property("EMP.id"),"empId")
					.add(Projections.property("EMP.loginId"),"empLoginId")
					.add(Projections.property("EMP.firstName"),"empFirstName")
					.add(Projections.property("EMP.lastName"),"emplastName")
					.add(Projections.property("EMPTYPE.typeName"),"empTypeName"))

			.setResultTransformer(Transformers.aliasToBean(EmployeeMstDto.class));
			employeeMstDto =  (EmployeeMstDto) criteria.uniqueResult();
		}catch(Exception e)
		{
			logger.info("doLogin Error :"+e.getMessage());
			return null;
		}
		return  employeeMstDto;
	}

	@Override
	public List<EmployeeMst> getAllEmployee() {
		List<EmployeeMst>  employeeMstList = null;
		Session session = this.sessionFactory.getCurrentSession();
		Criteria  criteria = session.createCriteria(EmployeeMst.class);
		employeeMstList = criteria.list();
		return employeeMstList;
	}




}
