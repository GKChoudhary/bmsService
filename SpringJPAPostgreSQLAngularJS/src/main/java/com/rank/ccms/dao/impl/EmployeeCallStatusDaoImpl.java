/*package com.rank.futuregenerali.dao.impl;

import com.rank.futuregenerali.dao.EmployeeCallStatusDao;
import com.rank.futuregenerali.entities.EmployeeCallStatus;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Repository("employeeCallStatusDao")
@Transactional
public class EmployeeCallStatusDaoImpl extends GenericDaoImpl<EmployeeCallStatus> implements EmployeeCallStatusDao {

    private static final long serialVersionUID = -3262237348213723055L;

    @Transactional
    @Override
    public List<EmployeeCallStatus> findOnlineFreeAgents() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallStatus.class)
                .add(Restrictions.eq("status", true));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeCallStatus>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public boolean updateAllEmplyeeStatusToZero() {
        boolean flag = true;
        try {

            org.hibernate.Query query1 = getSessionFactory().getCurrentSession().createSQLQuery("CALL updateStatus()");
            List results1 = query1.list();
            flag = results1 != null && !results1.isEmpty() && results1.size() > 0;

        } catch (Exception e) {

        }
        return flag;
    }

}
*/