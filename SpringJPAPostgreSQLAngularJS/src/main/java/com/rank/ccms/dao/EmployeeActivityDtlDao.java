package com.rank.ccms.dao;

import com.rank.ccms.entities.EmployeeActivityDtl;
import java.sql.Timestamp;
import java.util.List;

public interface EmployeeActivityDtlDao {
 
    public EmployeeActivityDtl findAgentWithMostIdleAfterHangUp(Long empPkId, String activity);
    
    List<EmployeeActivityDtl> findLastNonEndedActivityByType(Long empPkId, String activity);

    List<EmployeeActivityDtl> findListNonEndedActivityByActivity(String activity);
    
    List<EmployeeActivityDtl> findAllNonEndedActivity();

    List<EmployeeActivityDtl> findListNonEndedActivityByActivites(String activity, String activity2);

    Long findMaxLoginIdByEmpId(Long empId);

    Long findMaxIdByEmpId(Long empId);

    Long findMaxCallStartedIdByEmpId(Long empId);
    
    List<EmployeeActivityDtl> findLastEmployeeActivityIdOfAgentByAgentId(Long empId);

    List<EmployeeActivityDtl> findAgentLogggedIn(Long empPkId, String activity);

    List<EmployeeActivityDtl> findAllEndedActivityBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, String activity, Long empPkId);

    List<EmployeeActivityDtl> findStatusByAgent(Long empPkId);

    List<EmployeeActivityDtl> findStatusByAgentTypeTimeRange(Long empPkId, String activity, Timestamp startTime, Timestamp endTime);

    List<EmployeeActivityDtl> findAllLoggedInEmployeeList();

    List<EmployeeActivityDtl> allAgentEndedActivityBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, String activity);

    List<EmployeeActivityDtl> findbyActivityReasonCode(String activity, String reasonCd, Timestamp startDate, Timestamp endDate);

    EmployeeActivityDtl findByActivityId(Long id);

    EmployeeActivityDtl findLoginByEndTime(Timestamp endTime, Long empId);

    List<EmployeeActivityDtl> findAgentEndedActivityByActivity(Timestamp startDate, Timestamp endDate, Long employeeId, Long empTypeId, String activity);

    List<EmployeeActivityDtl> findEndedLogoutData(Timestamp startDate, Timestamp endDate, Long referencedLoginId, Long empId);

    List<EmployeeActivityDtl> findAgentEndedActivityByActivity(Timestamp startDate, Timestamp endDate, List<Integer> employeeIdList, String activity);

}
