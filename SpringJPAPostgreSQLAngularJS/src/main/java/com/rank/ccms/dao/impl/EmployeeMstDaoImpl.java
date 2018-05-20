/*package com.rank.futuregenerali.dao.impl;

import com.rank.futuregenerali.dao.EmployeeMstDao;
import com.rank.futuregenerali.dto.EmployeeMstDto;
import com.rank.futuregenerali.dto.OnlineUserDto;
import com.rank.futuregenerali.dto.ScheduleCallDto;
import com.rank.futuregenerali.entities.EmployeeMst;
import com.rank.futuregenerali.entities.EmployeeTypeMst;
import com.rank.futuregenerali.entities.ScheduleCall;

import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Repository("employeeMstDao")
@Transactional
public class EmployeeMstDaoImpl extends GenericDaoImpl<EmployeeMst> implements EmployeeMstDao {

    *//**
     * serialVersionUID
     *//*
    private static final long serialVersionUID = 3738468212831845251L;

    EmployeeMstDaoImpl() {
    }

    @Override
    public EmployeeMst findNonDeletedById(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "employee");
        detachedCriteria.setFetchMode("employee.empTypId", FetchMode.JOIN);
        List<EmployeeMst> employeeMstList = (List<EmployeeMst>) findByCriteria(detachedCriteria);
        if (null == employeeMstList || employeeMstList.isEmpty()) {
            return null;
        }
        return (EmployeeMst) employeeMstList.get(0);
    }

    @Override
    public List<EmployeeMst> findNonDeletedEmployeeByEmpType(EmployeeTypeMst employeeTypeMst) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "employee")
                .add(Restrictions.eq("employee.empTypId.id", employeeTypeMst.getId()))
                .add(Restrictions.eq("employee.activeFlg", true))
                .add(Restrictions.eq("employee.deleteFlg", false));
        detachedCriteria.setFetchMode("employee.empTypId", FetchMode.JOIN);
        detachedCriteria.addOrder(Order.asc("firstName"));
        detachedCriteria.addOrder(Order.asc("lastName"));
        return (List<EmployeeMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeMst> findAllNonDeletedEmployee() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "escm");
        detachedCriteria.setFetchMode("escm.empTypId", FetchMode.SELECT)
                .add(Restrictions.eq("escm.deleteFlg", false))
                .createCriteria("escm.empTypId", JoinType.LEFT_OUTER_JOIN)
                .addOrder(Order.asc("escm.id"));
        return (List<EmployeeMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeMst> findEmployeeByEmpTypeId(Integer employeeTypeId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "employee")
                .add(Restrictions.eq("employee.empTypId.id", employeeTypeId));
        return (List<EmployeeMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public EmployeeMst findEmployeeInfoForLogin(String userLoginId, String userPassword) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "escm");
        detachedCriteria.setFetchMode("escm.empTypId", FetchMode.SELECT)
                .add(Restrictions.eq("escm.deleteFlg", false))
                .add(Restrictions.eq("escm.loginId", userLoginId))
                .add(Restrictions.eq("escm.loginPasswd", userPassword))
                .createCriteria("escm.empTypId", JoinType.LEFT_OUTER_JOIN);
        List<EmployeeMst> empMstList = (List<EmployeeMst>) findByCriteria(detachedCriteria);
        if (!empMstList.isEmpty()) {
            return (EmployeeMst) empMstList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public EmployeeMst findEmployeeById_FirstName(Integer employeeId, String firstName) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "employee")
                .createCriteria("employee.empTypId", JoinType.INNER_JOIN)
                .add(Restrictions.eq("employee.id", employeeId));
        if (firstName == null || firstName.equals("")) {
        } else {
            detachedCriteria.add(Restrictions.ilike("employee.firstName", firstName));
        }
        detachedCriteria.setFetchMode("employee.empTypId", FetchMode.SELECT);
        List<EmployeeMst> employeeMstList = (List<EmployeeMst>) findByCriteria(detachedCriteria);
        if (null == employeeMstList || employeeMstList.isEmpty()) {
            return null;
        }
        return employeeMstList.get(0);
    }

    @Override
    public List<OnlineUserDto> findSuperAgentFromDifferentEmpTypeMst(Long emp_id1, String category) {
        org.hibernate.Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("select em.id \"emp_id\",case when em.mid_name is null or em.mid_name ='' then em.first_name ||' '|| em.last_name else em.first_name||' '||em.mid_name ||' '||em.last_name End  \"empFullName\"\n"
                        + "from employee_mst em,employee_type_mst etm where em.id=:emp_id  and etm.id=em.EMP_TYP_ID and etm.TYPE_NAME=:category")
                .setParameter("emp_id", emp_id1).setParameter("category", category)
                .setResultTransformer(Transformers.aliasToBean(OnlineUserDto.class));
        List<OnlineUserDto> results = query.list();

        return results;
    }

    @Override
    public EmployeeMst checkAgentLoginMobile(String userLoginId, String userPassword) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "EMPLOYEE");
        detachedCriteria.add(Restrictions.eq("EMPLOYEE.loginId", userLoginId))
                .add(Restrictions.eq("EMPLOYEE.loginPasswd", userPassword));

        List<EmployeeMst> employeeMst = (List<EmployeeMst>) findByCriteria(detachedCriteria);

        if (!employeeMst.isEmpty()) {
            return employeeMst.get(0);
        } else {
            return null;
        }

    }

    @Override
    public EmployeeMst findEmployeeDetailsByEmployeeNameMobileNum(String custId, String mobileNo) {
        DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeMst.class, "empDtl");

        if (custId != null || !"".equals(custId)) {
            criteria.add(Restrictions.eq("empDtl.loginId", custId).ignoreCase());
        }
        if (mobileNo != null && !"".equals(mobileNo)) {
            criteria.add(Restrictions.eq("empDtl.cellPhone", mobileNo));
        }

        List<EmployeeMst> listCustomerDtl = (List<EmployeeMst>) findByCriteria(criteria);

        if (!listCustomerDtl.isEmpty()) {
            return listCustomerDtl.get(0);
        } else {
            return null;
        }
    }

	@Override
	public EmployeeMstDto findEmpDetailsById(Long empId) {
		
		EmployeeMstDto employeeMstDto = null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "EMP");
		detachedCriteria.add(Restrictions.eq("id", empId));
		    
		ProjectionList projectTionlist = Projections.projectionList();
		projectTionlist.add(Projections.property("EMP.firstName"), "firstName")
		.add(Projections.property("EMP.midName"), "midName")
		.add(Projections.property("EMP.lastName"), "lastName");

		detachedCriteria.setProjection(projectTionlist);
		detachedCriteria.setResultTransformer(Transformers.aliasToBean(EmployeeMstDto.class));
		List<EmployeeMstDto> employeeMstDtoList =(List<EmployeeMstDto>) findByCriteria(detachedCriteria);
		if(!employeeMstDtoList.isEmpty() || employeeMstDtoList.size()>0)
		{
			employeeMstDto = employeeMstDtoList.get(0);
		}
		return employeeMstDto;

	}



}
*/