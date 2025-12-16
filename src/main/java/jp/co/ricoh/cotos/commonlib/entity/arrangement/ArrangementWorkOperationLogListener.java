package jp.co.ricoh.cotos.commonlib.entity.arrangement;

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
public class ArrangementWorkOperationLogListener {

	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;
	private static DummyUserMasterRepository dummyUserMasterRepository;

	@Autowired
	public void setMvEmployeeMasterRepository(MvEmployeeMasterRepository mvEmployeeMasterRepository) {
		ArrangementWorkOperationLogListener.mvEmployeeMasterRepository = mvEmployeeMasterRepository;
	}

	@Autowired
	public void setDummyUserMasterRepository(DummyUserMasterRepository dummyUserMasterRepository) {
		ArrangementWorkOperationLogListener.dummyUserMasterRepository = dummyUserMasterRepository;
	}

	/**
	 * 社員マスタ情報を手配業務操作履歴トランザクションに紐づけます。
	 *
	 * @param arrangementWorkOperationLog
	 */
	@PrePersist
	@Transactional
	public void appendsEmployeeFields(ArrangementWorkOperationLog arrangementWorkOperationLog) {
		CotosAuthenticationDetails userInfo = (CotosAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (userInfo.isDummyUser()) {
			DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(userInfo.getMomEmployeeId());
			arrangementWorkOperationLog.setOperatorEmpId(dummyUserMaster.getUserId());
			arrangementWorkOperationLog.setOperatorName(dummyUserMaster.getEmpName());
			arrangementWorkOperationLog.setOperatorOrgName(dummyUserMaster.getOrgName());
			return;
		}

		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(arrangementWorkOperationLog.getOperatorEmpId());

		if (employeeMaster == null) {
			return;
		}

		if (StringUtils.isBlank(arrangementWorkOperationLog.getOperatorName())) {
			arrangementWorkOperationLog.setOperatorName(employeeMaster.getJobname1() + employeeMaster.getJobname2());
		}
		if (StringUtils.isBlank(arrangementWorkOperationLog.getOperatorOrgName())) {
			arrangementWorkOperationLog.setOperatorOrgName(employeeMaster.getOrgName());
		}
	}

}
