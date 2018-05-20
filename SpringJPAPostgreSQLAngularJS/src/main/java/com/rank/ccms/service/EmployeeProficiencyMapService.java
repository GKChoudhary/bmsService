/*package com.rank.ccms.service;

import com.rank.futuregenerali.dto.OnlineUserDto;
import com.rank.futuregenerali.entities.EmployeeMst;
import com.rank.futuregenerali.entities.EmployeeProficiencyMap;
import java.io.Serializable;
import java.util.List;

public interface EmployeeProficiencyMapService extends Serializable {

    List<OnlineUserDto> findAllNonDeletedEmployeeProficiencyWithSkillIdList(Long emp_id);

    List<OnlineUserDto> findEmployeeFromDifferentCategory(Long emp_id, String category);

    EmployeeProficiencyMap saveEmployeeProficiency(EmployeeProficiencyMap employeeProficiencyMap, EmployeeMst employeeMst);

    List<EmployeeProficiencyMap> findAllNonDeletedEmployeeProficiencyMaps();

    public List<EmployeeProficiencyMap> findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(Long empPkId);

    List<EmployeeProficiencyMap> findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(Long empPkId, Long empTypPkId);

    public List<EmployeeProficiencyMap> findEmployeeByEmpId(EmployeeMst employeeMst);

    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapListBySkillId(String typeName, Long id);

    EmployeeProficiencyMap findNonDeletedEmployeeProficiencyById(Long id);

    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapsBySkillId(String type, Integer skillId, Integer empTypPkId);

    public List<EmployeeProficiencyMap> findActiveEmployeeProficiencyMapListBySkillId(Long skillId);

}
*/