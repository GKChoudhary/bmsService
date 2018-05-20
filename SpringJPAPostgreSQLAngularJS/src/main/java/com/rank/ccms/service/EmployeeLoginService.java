package com.rank.ccms.service;

import java.util.List;

import com.rank.ccms.dto.EmployeeMstDto;
import com.rank.ccms.entities.EmployeeMst;

public interface EmployeeLoginService {

	EmployeeMstDto doLogin(String loginId,String password); 
	
	List<EmployeeMst> getAllEmployee();
}
