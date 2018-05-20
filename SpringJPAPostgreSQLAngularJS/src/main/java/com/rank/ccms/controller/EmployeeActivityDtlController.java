package com.rank.ccms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rank.ccms.dto.EmployeeMstDto;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.response.CommonResponse;
import com.rank.ccms.response.EmployeeLoginResponse;
import com.rank.ccms.service.EmployeeActivityDtlService;
import com.rank.ccms.service.EmployeeLoginService;


@RestController
public class EmployeeActivityDtlController {
	
	private static final Logger logger = Logger.getLogger(EmployeeActivityDtlController.class);

	@Autowired
	EmployeeActivityDtlService employeeActivityDtlService;  

	@RequestMapping(value="/doLogin1",method=RequestMethod.POST,produces = {"application/json"},consumes={"application/json"})
	public ResponseEntity<?> doLogin(@RequestBody EmployeeMst employeeMst) {

		CommonResponse commonResponse = new CommonResponse();
		EmployeeLoginResponse employeeLoginResponse = new EmployeeLoginResponse(); 
		EmployeeMstDto employeeMstDto = null;
		
		try{

		if(employeeMst.getLoginId()=="" || employeeMst.getLoginId().isEmpty() || employeeMst.getLoginId()==null )
		{
			commonResponse.setErrMsg("Login id can not be Blank");
			return new ResponseEntity <CommonResponse > (commonResponse, HttpStatus.NOT_ACCEPTABLE);
		}
		
		if(employeeMst.getLoginPasswd()=="" || employeeMst.getLoginPasswd().isEmpty() || employeeMst.getLoginPasswd()==null )
		{
			commonResponse.setErrMsg("Login password can not be Blank");
			return new ResponseEntity <CommonResponse > (commonResponse, HttpStatus.NOT_ACCEPTABLE);
		}

	 //employeeMstDto = employeeLoginService.doLogin(employeeMst.getLoginId(), employeeMst.getLoginPasswd());
		if(employeeMstDto!= null )
		{
			employeeLoginResponse.setId(employeeMstDto.getEmpId());
			employeeLoginResponse.setLoginId(employeeMstDto.getEmpLoginId());
			employeeLoginResponse.setFirstName(employeeMstDto.getEmpFirstName());
			employeeLoginResponse.setLastname(employeeMstDto.getEmplastName());
			employeeLoginResponse.setEmpType(employeeMstDto.getEmpTypeName());
			return new ResponseEntity <EmployeeLoginResponse> (employeeLoginResponse, HttpStatus.OK);
		}else{
			
			commonResponse.setErrMsg("Invalid login Details");
			return new ResponseEntity <CommonResponse > (commonResponse, HttpStatus.OK);
		}
		}catch(Exception e)
		{
			commonResponse.setErrMsg("Internal server error");
			return new ResponseEntity <CommonResponse > (commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(value = "/getAllEmployee1", method = RequestMethod.GET,produces = {"application/json"})
	public List<EmployeeMst> getAllEmployee() {
		return null;

		//return employeeLoginService.getAllEmployee();

	}


}

