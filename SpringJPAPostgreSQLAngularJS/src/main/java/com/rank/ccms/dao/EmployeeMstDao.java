/*package com.rank.futuregenerali.dao;

import com.rank.futuregenerali.dto.EmployeeMstDto;
import com.rank.futuregenerali.dto.OnlineUserDto;
import com.rank.futuregenerali.entities.EmployeeMst;
import com.rank.futuregenerali.entities.EmployeeTypeMst;

import java.util.List;

public interface EmployeeMstDao extends GenericDao<EmployeeMst> {

    List<OnlineUserDto> findSuperAgentFromDifferentEmpTypeMst(Long emp_id,String category);
    
    public List<EmployeeMst> findNonDeletedEmployeeByEmpType(EmployeeTypeMst employeeTypeMst);

    public List<EmployeeMst> findAllNonDeletedEmployee();

    public List<EmployeeMst> findEmployeeByEmpTypeId(Integer employeeTypeId);

    public EmployeeMst findEmployeeInfoForLogin(String userLoginId, String userPassword);

    public EmployeeMst findEmployeeById_FirstName(Integer employeeId, String firstName);
    
    public EmployeeMst checkAgentLoginMobile(String userLoginId, String userPassword );

    public EmployeeMst findEmployeeDetailsByEmployeeNameMobileNum(String custId, String mobileNo);
    
    public EmployeeMstDto findEmpDetailsById(Long empId);
}
*/