/*package com.rank.futuregenerali.service.impl;

import com.rank.futuregenerali.dao.EmployeeCallStatusDao;
import com.rank.futuregenerali.entities.EmployeeCallStatus;
import com.rank.futuregenerali.entities.EmployeeMst;
import com.rank.futuregenerali.service.EmployeeCallStatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeCallStatusService")
public class EmployeeCallStatusServiceImpl implements EmployeeCallStatusService {

    @Autowired
    private EmployeeCallStatusDao employeeCallStatusDao;
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

    @Override
    public List<EmployeeCallStatus> findFreeOnlineAgents() {
        return employeeCallStatusDao.findOnlineFreeAgents();

    }

    @Override
    public List<EmployeeCallStatus> findAllEmployeeCallStatus() {
        return employeeCallStatusDao.findAll();
    }

    @Override
    public EmployeeCallStatus saveEmployeeCallStatus(EmployeeCallStatus employeeCallStatus) {
        if (employeeCallStatus.getId() == null) {
            employeeCallStatus = employeeCallStatusDao.saveRow(employeeCallStatus);

        } else {
            EmployeeCallStatus existingEmployeeCallStatus = employeeCallStatusDao.findById(employeeCallStatus.getId());
            if (existingEmployeeCallStatus != null) {
                if (existingEmployeeCallStatus != employeeCallStatus) {
                    existingEmployeeCallStatus.setId(employeeCallStatus.getId());
                    existingEmployeeCallStatus.setCallCount(employeeCallStatus.getCallCount());
                    existingEmployeeCallStatus.setEndTime(employeeCallStatus.getEndTime());
                    existingEmployeeCallStatus.setStatus(employeeCallStatus.getStatus());
                    existingEmployeeCallStatus.setEmpId(employeeCallStatus.getEmpId());
                    existingEmployeeCallStatus.setReadyTime(employeeCallStatus.getReadyTime());
                }
                try {
                    employeeCallStatus = employeeCallStatusDao.mergeRow(existingEmployeeCallStatus);
                } catch (Exception e) {
                    logger.info("Error:saveEmployeeCallStatus" + e.getMessage());
                }
            } else {
                employeeCallStatus = employeeCallStatusDao.saveRow(employeeCallStatus);
            }
        }
        return employeeCallStatus;
    }

    @Transactional
    @Override
    public List<EmployeeCallStatus> findEmployeeCallStatusByEmpId(EmployeeMst employeeMst) {
        List<EmployeeCallStatus> employeeCallStatusList = employeeCallStatusDao.createQuerySingleResult(
                "from EmployeeCallStatus as model where model.empId.id=?", employeeMst.getId());
        return employeeCallStatusList;
    }

    @Override
    public boolean updateAllEmplyeeStatusToZero() throws Exception {
        boolean flag;
        try {

//            List<EmployeeCallStatus> employeeCallStatusList = employeeCallStatusDao.createQuerySingleResult(
//                "update EmployeeCallStatus set status=0");
//            flag = employeeCallStatusList!=null&&!employeeCallStatusList.isEmpty();
//            flag = employeeCallStatusDao.updateAllEmplyeeStatusToZero();
            flag=true;
            List<EmployeeCallStatus> lgy = employeeCallStatusDao.findOnlineFreeAgents();
            if (!lgy.isEmpty()) {
                for (EmployeeCallStatus lgy1 : lgy) {
                    lgy1.setStatus(false);
                    employeeCallStatusDao.mergeRow(lgy1);

                }

            }
        } catch (Exception e) {
            flag = false;
            throw new Exception(e);
        }
        return flag;
    }

}
*/