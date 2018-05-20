package com.rank.ccms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rank.ccms.dao.EmployeeLoginDao;
import com.rank.ccms.dto.EmployeeMstDto;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.service.EmployeeLoginService;


@Service("employeeLoginService")
public class EmployeeLoginServiceImpl implements  EmployeeLoginService{
	
	@Autowired
	private EmployeeLoginDao employeeLoginDao;

	@Override
	public EmployeeMstDto doLogin(String loginId, String password) {
		
		return employeeLoginDao.doLogin(loginId, password);
	}

	@Override
	public List<EmployeeMst> getAllEmployee() {
		
		return employeeLoginDao.getAllEmployee();
	}

	
}
