
package com.rank.ccms.serviceImpl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rank.ccms.entities.EmployeeActivityDtl;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.service.EmployeeActivityDtlService;


@Service("employeeActivityDtlService")
public class EmployeeActivityDtlServiceImpl implements  EmployeeActivityDtlService{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8890869030622453684L;

	@Override
	public EmployeeActivityDtl findAgentWithMostIdleAfterHangUp(Long empPkId, String activity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeActivityDtl save(EmployeeActivityDtl employeeActivityDtl, EmployeeMst employeeMst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeActivityDtl findLastNonEndedActivityByType(Long empPkId, String activity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeActivityDtl> findAllNonEndedActivity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeActivityDtl> findListNonEndedActivityByType(Long empPkId, String activity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeActivityDtl save(EmployeeActivityDtl employeeActivityDtl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeActivityDtl> findAllActivityDtl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeActivityDtl> findLastEmployeeActivityIdOfAgentByAgentId(Long empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeActivityDtl findAgentStatus(Long empPkId, String activity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeActivityDtl> findStatusByAgent(Long empPkId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeActivityDtl> findAllEndedActivityBetweenDate(Timestamp fromDateTime, Timestamp toDateTime,
			String activity, Long empPkId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeActivityDtl> allAgentEndedActivityBetweenDate(Timestamp fromDateTime, Timestamp toDateTime,
			String activity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeActivityDtl> findStatusByAgentTypeTimeRange(Long empPkId, String activity, Timestamp startTime,
			Timestamp endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeActivityDtl> findAllLoggedInEmployeeList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long findMaxLoginIdByEmpId(Long empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long findMaxIdByEmpId(Long empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long findMaxCallStartedIdByEmpId(Long empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeActivityDtl> findbyActivityReasonCode(String activity, String reasonCd, Timestamp startDate,
			Timestamp endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeActivityDtl findByActivityId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeActivityDtl findLoginByEndTime(Timestamp endTime, Long empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeActivityDtl> findListNonEndedActivityByActivity(String activity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeActivityDtl> findListNonEndedActivityByActivites(String activity, String activity2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeActivityDtl> findAgentEndedActivityByActivity(Timestamp startDate, Timestamp endDate,
			Long employeeId, Long empTypeId, String activity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeActivityDtl> findEndedLogoutData(Timestamp startDate, Timestamp endDate, Long referencedLoginId,
			Long empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeActivityDtl> findAgentEndedActivityByActivity(Timestamp startDate, Timestamp endDate,
			List<Integer> employeeIdList, String activity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}




