/*package com.rank.futuregenerali.service.impl;

import com.rank.futuregenerali.config.ccmstablerelation.xmlconfig.CCMSTableRelationUtil;
import com.rank.futuregenerali.dao.AuditTrailDao;
import com.rank.futuregenerali.dao.EmployeeMstDao;
import com.rank.futuregenerali.dto.EmployeeMstDto;
import com.rank.futuregenerali.dto.OnlineUserDto;
import com.rank.futuregenerali.entities.AuditTrail;
import com.rank.futuregenerali.entities.EmployeeMst;
import com.rank.futuregenerali.entities.EmployeeTypeMst;
import com.rank.futuregenerali.service.EmployeeMstService;
import com.rank.futuregenerali.util.CustomConvert;
import com.rank.futuregenerali.util.EntityMetaData;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeMstService")
public class EmployeeMstServiceImpl implements EmployeeMstService {
    
    private static final long serialVersionUID = 8555162933788856275L;
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private EmployeeMstDao employeeMstDao;
    @Autowired
    private CCMSTableRelationUtil ccmsTableRelationUtil;
    @Autowired
    private AuditTrailDao auditTrailDao;
    
    @Transactional
    @Override
    public EmployeeMst findEmployeeMstById(Long id) {
        return employeeMstDao.findById(id);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<EmployeeMst> findAllEmployeeMsts() {
        return new ArrayList<>(employeeMstDao.findAll());
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<EmployeeMst> findAllNonDeletedEmployeeMsts() {
        return new ArrayList<>(employeeMstDao.findAllNonDeletedEmployee());
    }
    
    @Transactional
    @Override
    public EmployeeMst saveEmployeeMst(EmployeeMst employeemst, EmployeeMst employeeMst, String ipAddrStr) {
        AuditTrail auditTrail = new AuditTrail();
        EntityMetaData entityMetaData = employeeMstDao.getEntityMetaData();
        auditTrail.setTableName(entityMetaData.getRootTableName());
        try {
            auditTrail.setDateTime(CustomConvert.javaDateToTimeStamp(new Date()));
        } catch (ParseException e) {
            logger.info(" ParseException " + e);
        }
        if (employeemst.getId() == null) {
            try {
                employeemst = employeeMstDao.saveRow(employeemst);
                auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + employeemst.getId().toString());
                auditTrail.setCreatedById(employeeMst.getId());
                auditTrail.setCreatedOn(CustomConvert.javaDateToTimeStamp(new Date()));
                auditTrail.setReason("Inserted/Created First Time");
                auditTrail.setIpAddress(ipAddrStr);
            } catch (Exception ex) {
                
                logger.error("1.Date Parsing Exception:" + ex.getMessage(), ex);
            }
        } else {
            EmployeeMst existingEmployeeMst = employeeMstDao.findById(employeemst.getId());
            if (existingEmployeeMst != null) {
                if (existingEmployeeMst != employeemst) {
                    existingEmployeeMst.setId(employeemst.getId());
                    existingEmployeeMst.setFirstName(employeemst.getFirstName());
                    existingEmployeeMst.setMidName(employeemst.getMidName());
                    existingEmployeeMst.setLastName(employeemst.getLastName());
                    existingEmployeeMst.setLoginId(employeemst.getLoginId());
                    existingEmployeeMst.setLoginPasswd(employeemst.getLoginPasswd());
                    existingEmployeeMst.setEmpTypId(employeemst.getEmpTypId());
                    existingEmployeeMst.setEmpDob(employeemst.getEmpDob());
                    existingEmployeeMst.setAddrsLine1(employeemst.getAddrsLine1());
                    existingEmployeeMst.setAddrsLine2(employeemst.getAddrsLine2());
                    existingEmployeeMst.setCity(employeemst.getCity());
                    existingEmployeeMst.setStateNm(employeemst.getStateNm());
                    existingEmployeeMst.setPin(employeemst.getPin());
                    existingEmployeeMst.setCountry(employeemst.getCountry());
                    existingEmployeeMst.setEmail(employeemst.getEmail());
                    existingEmployeeMst.setOfficePhone(employeemst.getOfficePhone());
                    existingEmployeeMst.setHomePhone(employeemst.getHomePhone());
                    existingEmployeeMst.setCellPhone(employeemst.getCellPhone());
                    existingEmployeeMst.setActiveFlg(employeemst.getActiveFlg());
                    existingEmployeeMst.setDeleteFlg(employeemst.getDeleteFlg());
                    existingEmployeeMst.setDeactivateFlg(employeemst.getDeactivateFlg());
                }
                try {
                    if (existingEmployeeMst.getDeleteFlg() == true) {
                        auditTrail.setDeletedById(employeeMst.getId());
                        auditTrail.setDeletedOn(CustomConvert.javaDateToTimeStamp(new Date()));
                        auditTrail.setReason("Deleted");
                        auditTrail.setIpAddress(ipAddrStr);
                    } else if (existingEmployeeMst.getDeactivateFlg()== true) {
                        auditTrail.setDisabledById(employeeMst.getId());
                        auditTrail.setDisabledOn(CustomConvert.javaDateToTimeStamp(new Date()));
                        auditTrail.setReason("De-Activated");
                        auditTrail.setIpAddress(ipAddrStr);
                    } 
                    else if (existingEmployeeMst.getDeactivateFlg()== false) {
                        auditTrail.setDisabledById(employeeMst.getId());
                        auditTrail.setDisabledOn(CustomConvert.javaDateToTimeStamp(new Date()));
                        auditTrail.setReason("Activated");
                        auditTrail.setIpAddress(ipAddrStr);
                    } 
                    else {
                        auditTrail.setUpdatedById(employeeMst.getId());
                        auditTrail.setUpdatedOn(CustomConvert.javaDateToTimeStamp(new Date()));
                        auditTrail.setReason("Updated");
                        auditTrail.setIpAddress(ipAddrStr);
                    }
                } catch (Exception e) {
                    
                    logger.error("2.Date Parsing Exception:" + e.getMessage(), e);
                }
                employeemst = employeeMstDao.saveRow(existingEmployeeMst);
                auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + existingEmployeeMst.getId().toString());
            } else {
                try {
                    employeemst = employeeMstDao.saveRow(employeemst);
                    auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + employeemst.getId().toString());
                    auditTrail.setCreatedById(employeeMst.getId());
                    auditTrail.setCreatedOn(CustomConvert.javaDateToTimeStamp(new Date()));
                    auditTrail.setReason("Created/Inserted first time");
                    auditTrail.setIpAddress(ipAddrStr);
                } catch (Exception ex) {
                    
                    logger.error("3.Date Parsing Exception:" + ex.getMessage(), ex);
                }
            }
        }
        auditTrail = auditTrailDao.saveRow(auditTrail);
        if (null == auditTrail) {
            return null;
        }
        return employeemst;
    }
    
    @Transactional
    @Override
    public List<OnlineUserDto> findSuperAgentFromDifferentEmpTypeMst(Long emp_id, String category) {
        return employeeMstDao.findSuperAgentFromDifferentEmpTypeMst(emp_id, category);
    }
    
    @Transactional
    @Override
    public void deleteEmployeeMst(EmployeeMst employeemst, EmployeeMst employeeMst) {
        EntityMetaData entityMetaData = employeeMstDao.getEntityMetaData();
        try {
            if (entityMetaData.getRootTableName().equalsIgnoreCase(ccmsTableRelationUtil.deleteChildofParent(entityMetaData.getRootTableName(), employeemst.getId()))) {
                logger.info("Cross Deletion Successful..");
            } else {
                System.err.println("Table Relations for " + entityMetaData.getRootTableName() + " not Maintained and Error while de-activating the childs.");
                logger.error("Table Relations for " + entityMetaData.getRootTableName() + " not Maintained and Error while de-activating the childs.");
            }
        } catch (Exception ex) {
            
            logger.error("Error:" + ex.getMessage() + ": while Cross De-activation the Childs for PKId:" + employeemst.getId().toString(), ex);
        }
        employeeMstDao.deleteRow(employeemst);
        
    }
    
    @Transactional(readOnly = true)
    @Override
    public Integer countEmployeeMsts() {
        return (Integer) (employeeMstDao.createQuerySingleResult("select count(o) from EmployeeMst o")).get(0);
    }
    
    @Transactional(readOnly = true)
    @Override
    public EmployeeMst findEmployeeInfoForLogin(String userLoginId, String userPassword) {
        return employeeMstDao.findEmployeeInfoForLogin(userLoginId, userPassword);
        
    }
    
    @Transactional(readOnly = true)
    @Override
    public EmployeeMst findEmployeeByUserId(String userLoginId) {
        EmployeeMst employeeMst = null;
        List<EmployeeMst> employeeMasterList = employeeMstDao.createQuerySingleResult(
                "from EmployeeMst as model where model.loginId=? and deleteFlg=?", userLoginId, false);
        for (EmployeeMst custMst : employeeMasterList) {
            employeeMst = custMst;
        }
        return employeeMst;
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<EmployeeMst> findEmployeeByEmpTypeId(EmployeeTypeMst employeeTypeMst) {
        List<EmployeeMst> employeeMasterList = employeeMstDao.createQuerySingleResult(
                "from EmployeeMst as model where model.empTypId.id=? and model.deleteFlg=?", employeeTypeMst.getId(), false);
        return employeeMasterList;
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<EmployeeMst> findNonDeletedEmployeeByEmpTypeId(EmployeeTypeMst employeeTypeMst) {
        List<EmployeeMst> employeeMasterList = employeeMstDao.findNonDeletedEmployeeByEmpType(employeeTypeMst);
        return employeeMasterList;
    }
    
    @Transactional(readOnly = true)
    @Override
    public EmployeeMst findAllEmployeeByUserId(String userLoginId) {
        EmployeeMst employeeMst = null;
        List<EmployeeMst> employeeMasterList = employeeMstDao.createQuerySingleResult(
                "from EmployeeMst as model where model.loginId=?", userLoginId);
        for (EmployeeMst custMst : employeeMasterList) {
            employeeMst = custMst;
        }
        return employeeMst;
    }
    
    @Transactional(readOnly = true)
    @Override
    public EmployeeMst findAllEmployeeByEmailId(String emailId) {
        EmployeeMst employeeMst = null;
        List<EmployeeMst> employeeMasterList = employeeMstDao.createQuerySingleResult(
                "from EmployeeMst as model where model.email=?", emailId);
        for (EmployeeMst custMst : employeeMasterList) {
            employeeMst = custMst;
        }
        return employeeMst;
    }
    
    @Transactional(readOnly = true)
    @Override
    public EmployeeMst findEmployeeById_FirstName(Integer employeeId, String firstName) {
        return employeeMstDao.findEmployeeById_FirstName(employeeId, firstName);
    }
    
    @Override
    public EmployeeMst checkAgentLoginMobile(String userLoginId, String userPassword) {
        return employeeMstDao.checkAgentLoginMobile(userLoginId, userPassword);
    }
    
    @Override
    public EmployeeMst findEmployeeDetailsByEmployeeNameMobileNum(String custId, String mobileNo) {
        return employeeMstDao.findEmployeeDetailsByEmployeeNameMobileNum(custId, mobileNo);
    }

	@Override
	public EmployeeMstDto findEmpDetailsById(Long empId) {
		return employeeMstDao.findEmpDetailsById(empId);
	}
    
}
*/