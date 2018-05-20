/*package com.rank.futuregenerali.dao;

import com.rank.futuregenerali.dto.OnlineUserDto;
import com.rank.futuregenerali.entities.EmployeeProficiencyMap;
import java.util.List;

public interface EmployeeProficiencyMapDao extends GenericDao<EmployeeProficiencyMap> {

    List<OnlineUserDto> findAllNonDeletedEmployeeProficiencyWithSkillIdList(Long emp_id);
    
    List<OnlineUserDto> findEmployeeFromDifferentCategory(Long emp_id,String category);
    
    public List<EmployeeProficiencyMap> findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(Long empPkId);

    public List<EmployeeProficiencyMap> findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(Long empPkId, Long empTypPkId);

    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapsByEmpPkId(Long empPkId);

    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapsBySkillId(String type, Long skillId);

    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapsBySkillId(String type, Integer skillId, Integer empTypPkId);
    
    public List<EmployeeProficiencyMap> findActiveEmployeeProficiencyMapsBySkillId(Long skillId);

}
*/