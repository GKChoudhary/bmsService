/*package com.rank.futuregenerali.service.impl;

import com.rank.futuregenerali.config.ccmstablerelation.xmlconfig.CCMSTableRelationUtil;
import com.rank.futuregenerali.dao.EmployeeTypeMstDao;
import com.rank.futuregenerali.entities.EmployeeMst;
import com.rank.futuregenerali.entities.EmployeeTypeMst;
import com.rank.futuregenerali.service.EmployeeTypeMstService;
import com.rank.futuregenerali.util.EntityMetaData;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeTypeMstService")
public class EmployeeTypeMstServiceImpl implements EmployeeTypeMstService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private EmployeeTypeMstDao employeeTypeMstDao;
    @Autowired
    private CCMSTableRelationUtil ccmsTableRelationUtil;

    @Transactional
    @Override
    public EmployeeTypeMst saveEmployeeTypeMst(EmployeeTypeMst employeeTypeMst, EmployeeMst employeeMst) {
        
        EntityMetaData entityMetaData = employeeTypeMstDao.getEntityMetaData();
        
        if (employeeTypeMst.getId() == null) {
            employeeTypeMst = employeeTypeMstDao.saveRow(employeeTypeMst);
        } else {
            EmployeeTypeMst existingemployeeTypeMst = employeeTypeMstDao.findById(employeeTypeMst.getId());
            if (existingemployeeTypeMst != null) {
                if (existingemployeeTypeMst != employeeTypeMst) {
                    existingemployeeTypeMst.setId(employeeTypeMst.getId());
                    existingemployeeTypeMst.setTypeName(employeeTypeMst.getTypeName());
                    existingemployeeTypeMst.setTypeDesc(employeeTypeMst.getTypeDesc());
                    existingemployeeTypeMst.setDeleteFlg(employeeTypeMst.getDeleteFlg());
                    existingemployeeTypeMst.setActiveFlg(employeeTypeMst.getActiveFlg());
                }
                if (existingemployeeTypeMst.getDeleteFlg() == true) {
                    
                    try {
                        if (entityMetaData.getRootTableName().equalsIgnoreCase(ccmsTableRelationUtil.deleteChildofParent(entityMetaData.getRootTableName(), existingemployeeTypeMst.getId()))) {
                            logger.info("Cross Deletion Successful..");
                        } else {
                            System.err.println("Table Relations for " + entityMetaData.getRootTableName() + " not Maintained and Error while de-activating the childs.");
                            logger.error("Table Relations for " + entityMetaData.getRootTableName() + " not Maintained and Error while de-activating the childs.");
                        }
                    } catch (Exception ex) {
                        
                        logger.error("Error:" + ex.getMessage() + ": while Cross De-activation the Childs for PKId:" + existingemployeeTypeMst.getId().toString(), ex);
                    }
                } else {
                    
                }
                employeeTypeMst = employeeTypeMstDao.mergeRow(existingemployeeTypeMst);
            } else {
                employeeTypeMst = employeeTypeMstDao.saveRow(employeeTypeMst);
            }
        }
       
        return employeeTypeMst;
    }

    @Transactional
    @Override
    public List<EmployeeTypeMst> findAllEmployeeTypeMsts() {
        return new ArrayList<>(employeeTypeMstDao.findAll());
    }

    @Transactional
    @Override
    public List<EmployeeTypeMst> findAllNonDeletedEmployeeTypeMst() {
        return new ArrayList<>(employeeTypeMstDao.findAllNonDeleted());
    }

    @Transactional
    @Override
    public EmployeeTypeMst findNonDeletedEmployeeTypeMstById(Long id) {
        return employeeTypeMstDao.findNonDeletedById(id);
    }

    @Transactional
    @Override
    public EmployeeTypeMst findEmployeeTypeMstById(Long id) {
        return employeeTypeMstDao.findById(id);
    }

    @Transactional
    @Override
    public Integer countEmployeeTypeMsts() {
        return (Integer) (employeeTypeMstDao.createQuerySingleResult(
                "select count(o) from EmployeeTypeMst o where o.deleteFlg=? and o.activeFlg=?", false, true).get(0));
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeTypeMst findEmployeeTypeByEmployeeTypeName(String employeeTypeName) {
        EmployeeTypeMst empMst = null;
        List<EmployeeTypeMst> employeeTypeMstList = employeeTypeMstDao.createQuerySingleResult(
                "from EmployeeTypeMst as model where upper(model.typeName)=upper(?) and model.deleteFlg=?", employeeTypeName, false);
        for (EmployeeTypeMst employeeMst : employeeTypeMstList) {
            empMst = employeeMst;
        }
        return empMst;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeTypeMst> findActivenNonDeletedByProperty(String propertyName, String value) {
        return new ArrayList<>(employeeTypeMstDao.findEqProperty(propertyName, value));
    }

}
*/