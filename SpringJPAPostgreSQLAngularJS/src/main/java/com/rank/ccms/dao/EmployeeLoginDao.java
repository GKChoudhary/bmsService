package com.rank.ccms.dao;

import java.util.List;

import com.rank.ccms.dto.EmployeeMstDto;
import com.rank.ccms.entities.EmployeeMst;
public interface EmployeeLoginDao  {
	
	EmployeeMstDto doLogin(String loginId,String password); 
	
	List<EmployeeMst> getAllEmployee();

}
