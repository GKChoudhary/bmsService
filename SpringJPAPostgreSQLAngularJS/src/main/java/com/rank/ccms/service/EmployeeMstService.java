/*package com.rank.ccms.service;

import com.rank.futuregenerali.dto.EmployeeMstDto;
import com.rank.futuregenerali.dto.OnlineUserDto;
import com.rank.futuregenerali.entities.EmployeeMst;
import com.rank.futuregenerali.entities.EmployeeTypeMst;

import java.io.Serializable;
import java.util.List;

public interface EmployeeMstService extends Serializable {

    List<OnlineUserDto> findSuperAgentFromDifferentEmpTypeMst(Long emp_id, String category);

    EmployeeMst findEmployeeMstById(Long id);

    Integer countEmployeeMsts();

    void deleteEmployeeMst(EmployeeMst employeemst, EmployeeMst employeeMst);

    List<EmployeeMst> findAllNonDeletedEmployeeMsts();

    List<EmployeeMst> findAllEmployeeMsts();

    EmployeeMst saveEmployeeMst(EmployeeMst employeemst, EmployeeMst loginEmployeeMst, String ipAddress);

    public EmployeeMst findEmployeeInfoForLogin(String userLoginId, String userPassword);

    public EmployeeMst findEmployeeByUserId(String userLoginId);

    public List<EmployeeMst> findEmployeeByEmpTypeId(EmployeeTypeMst employeeTypeMst);

    public List<EmployeeMst> findNonDeletedEmployeeByEmpTypeId(EmployeeTypeMst employeeTypeMst);

    public EmployeeMst findAllEmployeeByUserId(String userLoginId);

    public EmployeeMst findAllEmployeeByEmailId(String emailId);

    public EmployeeMst findEmployeeById_FirstName(Integer employeeId, String firstName);

    public EmployeeMst checkAgentLoginMobile(String userLoginId, String userPassword);

    public EmployeeMst findEmployeeDetailsByEmployeeNameMobileNum(String custId, String mobileNo);
    
    public EmployeeMstDto findEmpDetailsById(Long empId);

}
*/