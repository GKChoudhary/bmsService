/*package com.rank.futuregenerali.service.impl;

import com.rank.futuregenerali.config.ccmstablerelation.xmlconfig.CCMSTableRelationUtil;
import com.rank.futuregenerali.dao.EmployeeProficiencyMapDao;
import com.rank.futuregenerali.dto.OnlineUserDto;
import com.rank.futuregenerali.entities.EmployeeMst;
import com.rank.futuregenerali.entities.EmployeeProficiencyMap;
import com.rank.futuregenerali.service.EmployeeProficiencyMapService;
import com.rank.futuregenerali.util.EntityMetaData;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeProficiencyMapService")
public class EmployeeProficiencyMapServiceImpl implements EmployeeProficiencyMapService {

    Logger logger = Logger.getLogger(this.getClass());


    @Autowired
    private EmployeeProficiencyMapDao employeeProficiencyMapDao;

    @Autowired
    private CCMSTableRelationUtil ccmsTableRelationUtil;

    @Transactional
    @Override
    public EmployeeProficiencyMap saveEmployeeProficiency(EmployeeProficiencyMap employeeProficiencyMap, EmployeeMst employeeMst) {
      
        EntityMetaData entityMetaData = employeeProficiencyMapDao.getEntityMetaData();
        

        if (employeeProficiencyMap.getId() == null) {
            employeeProficiencyMap = employeeProficiencyMapDao.saveRow(employeeProficiencyMap);
        } else {
            EmployeeProficiencyMap existingemployeeProficiencyMap = employeeProficiencyMapDao.findById(employeeProficiencyMap.getId());
            if (existingemployeeProficiencyMap != null) {
                if (existingemployeeProficiencyMap != employeeProficiencyMap) {
                    existingemployeeProficiencyMap.setId(employeeProficiencyMap.getId());
                    existingemployeeProficiencyMap.setEmpTypId(employeeProficiencyMap.getEmpTypId());
                    existingemployeeProficiencyMap.setEmpId(employeeProficiencyMap.getEmpId());
                    existingemployeeProficiencyMap.setActiveFlg(employeeProficiencyMap.getActiveFlg());
                    existingemployeeProficiencyMap.setDeleteFlg(employeeProficiencyMap.getDeleteFlg());
                    existingemployeeProficiencyMap.setPrimarySkill(employeeProficiencyMap.getPrimarySkill());
                    existingemployeeProficiencyMap.setSecondarySkill(employeeProficiencyMap.getSecondarySkill());
                    existingemployeeProficiencyMap.setSkillId(employeeProficiencyMap.getSkillId());
                    existingemployeeProficiencyMap.setType(employeeProficiencyMap.getType());
                }
                if (existingemployeeProficiencyMap.getDeleteFlg()) {
                    
                    try {
                        if (entityMetaData.getRootTableName().equals(ccmsTableRelationUtil.deleteChildofParent(entityMetaData.getRootTableName(), existingemployeeProficiencyMap.getId()))) {
                            logger.info("Cross Deletion Successful..");
                        } else {
                            System.err.println("Table Relations for " + entityMetaData.getRootTableName() + " not Maintained and Error while de-activating the childs.");
                            logger.error("Table Relations for " + entityMetaData.getRootTableName() + " not Maintained and Error while de-activating the childs.");
                        }
                    } catch (Exception ex) {
                        
                        logger.error("Error:" + ex.getMessage() + ": while Cross De-activation the Childs for PKId:" + existingemployeeProficiencyMap.getId().toString(), ex);
                    }
                } else {
                    
                }
                employeeProficiencyMap = employeeProficiencyMapDao.mergeRow(existingemployeeProficiencyMap);
            } else {
                employeeProficiencyMap = employeeProficiencyMapDao.saveRow(employeeProficiencyMap);
            }
        }
       
        return employeeProficiencyMap;
    }
    
    @Override
    public List<OnlineUserDto> findAllNonDeletedEmployeeProficiencyWithSkillIdList(Long emp_id) {
        return employeeProficiencyMapDao.findAllNonDeletedEmployeeProficiencyWithSkillIdList(emp_id);
    }

    @Override
    public List<OnlineUserDto> findEmployeeFromDifferentCategory(Long emp_id,String category){
        return employeeProficiencyMapDao.findEmployeeFromDifferentCategory(emp_id,category);
    }
    
    
    @Transactional
    @Override
    public List<EmployeeProficiencyMap> findAllNonDeletedEmployeeProficiencyMaps() {
        return new ArrayList<>(employeeProficiencyMapDao.findAllNonDeleted());
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeProficiencyMap> findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(Long empPkId) {
        return new ArrayList<>(employeeProficiencyMapDao.findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(empPkId));
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeProficiencyMap> findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(Long empPkId, Long empTypPkId) {
        return new ArrayList<>(employeeProficiencyMapDao.findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(empPkId, empTypPkId));
    }

    @Transactional
    @Override
    public List<EmployeeProficiencyMap> findEmployeeByEmpId(EmployeeMst employeeMst) {
        List<EmployeeProficiencyMap> employeeproficiencyMapList = employeeProficiencyMapDao.createQuerySingleResult(
                "from EmployeeProficiencyMap as model where model.empId.id=? and model.deleteFlg=false", employeeMst.getId());

        return employeeproficiencyMapList;
    }

    @Override
    public EmployeeProficiencyMap findNonDeletedEmployeeProficiencyById(Long id) {
        return employeeProficiencyMapDao.findNonDeletedById(id);
    }

    @Override
    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapListBySkillId(String typeName, Long id) {
        return employeeProficiencyMapDao.findEmployeeProficiencyMapsBySkillId(typeName, id);
    }

    @Override
    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapsBySkillId(String type, Integer skillId, Integer empTypPkId) {
        return employeeProficiencyMapDao.findEmployeeProficiencyMapsBySkillId(type, skillId, empTypPkId);
    }

    @Override
    public List<EmployeeProficiencyMap> findActiveEmployeeProficiencyMapListBySkillId(Long skillId) {
       return employeeProficiencyMapDao.findActiveEmployeeProficiencyMapsBySkillId(skillId);
    }

}
*/