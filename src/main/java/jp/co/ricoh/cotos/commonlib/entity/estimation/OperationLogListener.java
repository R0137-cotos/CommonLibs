package jp.co.ricoh.cotos.commonlib.entity.estimation;

import jakarta.persistence.PrePersist;
import jakarta.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.entity.master.DummyUserMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.MvEmployeeMaster;
import jp.co.ricoh.cotos.commonlib.repository.master.DummyUserMasterRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.MvEmployeeMasterRepository;
import jp.co.ricoh.cotos.commonlib.security.CotosAuthenticationDetails;

@Component
public class OperationLogListener {

	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;
	private static DummyUserMasterRepository dummyUserMasterRepository;

	@Autowired
	public void setMvEmployeeMasterRepository(MvEmployeeMasterRepository mvEmployeeMasterRepository) {
		OperationLogListener.mvEmployeeMasterRepository = mvEmployeeMasterRepository;
	}

	@Autowired
	public void setDummyUserMasterRepository(DummyUserMasterRepository dummyUserMasterRepository) {
		OperationLogListener.dummyUserMasterRepository = dummyUserMasterRepository;
	}

	/**
	 * 社員マスタ情報を見積操作履歴トランザクションに紐づけます。
	 *
	 * @param operationLog
	 */
	@PrePersist
	@Transactional
	public void appendsEmployeeFields(OperationLog operationLog) {
		CotosAuthenticationDetails userInfo = (CotosAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (userInfo.isDummyUser()) {
			DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(userInfo.getMomEmployeeId());
			operationLog.setOperatorEmpId(dummyUserMaster.getUserId());
			operationLog.setOperatorName(dummyUserMaster.getEmpName());
			operationLog.setOperatorOrgName(dummyUserMaster.getOrgName());
			return;
		}

		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(operationLog.getOperatorEmpId());

		if (employeeMaster == null) {
			return;
		}

		if (StringUtils.isBlank(operationLog.getOperatorName())) {
			operationLog.setOperatorName(employeeMaster.getJobname1() + employeeMaster.getJobname2());
		}
		if (StringUtils.isBlank(operationLog.getOperatorOrgName())) {
			operationLog.setOperatorOrgName(employeeMaster.getOrgName());
		}
	}

}
