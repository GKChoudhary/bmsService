/*package com.rank.futuregenerali.dao.impl;

import com.rank.futuregenerali.dao.EmployeeProficiencyMapDao;
import com.rank.futuregenerali.dto.OnlineUserDto;
import com.rank.futuregenerali.entities.EmployeeProficiencyMap;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Repository("employeeProficiencyMapDao")
@Transactional
public class EmployeeProficiencyMapDaoImpl extends GenericDaoImpl<EmployeeProficiencyMap> implements EmployeeProficiencyMapDao {

    private static final long serialVersionUID = 546107304975173827L;

    @Override
    public List<EmployeeProficiencyMap> findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(Long empPkId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeProficiencyMap.class)
                .add(Restrictions.eq("empId.id", empPkId))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .addOrder(Order.asc("type"));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeProficiencyMap>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeProficiencyMap> findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(Long empPkId, Long empTypPkId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeProficiencyMap.class)
                .add(Restrictions.eq("empId.id", empPkId))
                .add(Restrictions.eq("empTypId", empTypPkId))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .addOrder(Order.asc("type"));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeProficiencyMap>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapsByEmpPkId(Long empPkId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeProficiencyMap.class)
                .add(Restrictions.eq("empId.id", empPkId))
                .addOrder(Order.asc("type"));
        return (List<EmployeeProficiencyMap>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapsBySkillId(String type, Long skillId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeProficiencyMap.class)
                .add(Restrictions.eq("type", type).ignoreCase())
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("skillId", skillId));
        return (List<EmployeeProficiencyMap>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapsBySkillId(String type, Integer skillId, Integer empTypPkId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeProficiencyMap.class)
                .add(Restrictions.eq("type", type.toLowerCase()).ignoreCase())
                .add(Restrictions.eq("empTypId", empTypPkId))
                .add(Restrictions.eq("skillId", skillId))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .addOrder(Order.asc("type"))
                .addOrder(Order.asc("empId.id"));
        return (List<EmployeeProficiencyMap>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<OnlineUserDto> findAllNonDeletedEmployeeProficiencyWithSkillIdList(Long emp_id1) {

        org.hibernate.Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("  SELECT r.emp_id,\n" +
"		   MAX(CASE WHEN r.type='Category' and r.primary_skill=1 THEN r.skill_id_list ELSE NULL END) \"primary_category\",\n" +
"         MAX(CASE WHEN r.type='Category' and r.secondary_skill=1 THEN r.skill_id_list ELSE NULL END) \"secondary_category\",\n" +
"         MAX(CASE WHEN r.type = 'Language' and r.primary_skill=1 THEN r.skill_id_list ELSE NULL END) \"primary_language\",\n" +
"         MAX(CASE WHEN r.type = 'Language' and r.secondary_skill=1 THEN r.skill_id_list ELSE NULL END) \"secondary_language\",\n" +
"         MAX(CASE WHEN r.type = 'Service' and r.primary_skill=1 THEN r.skill_id_list ELSE NULL END) \"primary_service\",\n" +
"          MAX(CASE WHEN r.type = 'Service' and r.secondary_skill=1 THEN r.skill_id_list ELSE NULL END) \"secondary_service\"\n" +
"                         FROM (select distinct on (type) *, string_agg(cast(skill_id as varchar), ',') over (partition by type) skill_id_list \n" +
"                       from employee_proficiency_map where emp_id=:emp_id  and active_flg=true and delete_flg=false) r\n" +
"                       JOIN employee_proficiency_map q ON q.emp_id = r.emp_id\n" +
"                        GROUP BY r.emp_id;").setParameter("emp_id", emp_id1)
                .setResultTransformer(Transformers.aliasToBean(OnlineUserDto.class));
        List<OnlineUserDto> results = query.list();
        return results;

    }

    @Override
    public List<OnlineUserDto> findEmployeeFromDifferentCategory(Long emp_id1, String category) {
        org.hibernate.Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("select count(*) \"emp_count\" from EMPLOYEE_PROFICIENCY_MAP epm,CATEGORY_MST "
                        + "cm where epm.SKILL_ID=cm.ID and epm.emp_id=:emp_id and epm.active_flg=true "
                        + "and epm.delete_flg=false and epm.type='Category' and cm.CATG_CD=:category ").setParameter("emp_id", emp_id1).setParameter("category", category)
                .setResultTransformer(Transformers.aliasToBean(OnlineUserDto.class));
        List<OnlineUserDto> results = query.list();

        return results;
    }

    @Override
    public List<EmployeeProficiencyMap> findActiveEmployeeProficiencyMapsBySkillId(Long skillId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeProficiencyMap.class)
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("skillId", skillId));
        return (List<EmployeeProficiencyMap>) findByCriteria(detachedCriteria);
    }

}
*/