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
import com.rank.ccms.service.EmployeeLoginService;
import com.rank.ccms.util.GenerateToken;


@RestController
public class EmployeeLoginController {
	
	private static final Logger logger = Logger.getLogger(EmployeeLoginController.class);

	@Autowired
	EmployeeLoginService employeeLoginService;

	@RequestMapping(value="/doLogin",method=RequestMethod.POST,produces = {"application/json"},consumes={"application/json"})
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

	 employeeMstDto = employeeLoginService.doLogin(employeeMst.getLoginId(), employeeMst.getLoginPasswd());
		if(employeeMstDto!= null )
		{
			Long number = (long) Math.floor(Math.random() * 9000000L) + 1000000L;
			String token = GenerateToken.getToken();
			String room = employeeMstDto.getEmpLoginId() + number;
			
			employeeLoginResponse.setId(employeeMstDto.getEmpId());
			employeeLoginResponse.setLoginId(employeeMstDto.getEmpLoginId());
			employeeLoginResponse.setFirstName(employeeMstDto.getEmpFirstName());
			employeeLoginResponse.setLastname(employeeMstDto.getEmplastName());
			employeeLoginResponse.setEmpType(employeeMstDto.getEmpTypeName());
			employeeLoginResponse.setRoomId(room);
			employeeLoginResponse.setToken(token);
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


	@RequestMapping(value = "/getAllEmployee", method = RequestMethod.GET,produces = {"application/json"})
	public List<EmployeeMst> getAllEmployee() {
		System.out.println("hi");

		return employeeLoginService.getAllEmployee();

	}


}

