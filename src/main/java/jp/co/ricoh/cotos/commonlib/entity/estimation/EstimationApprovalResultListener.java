package jp.co.ricoh.cotos.commonlib.entity.estimation;

import jakarta.persistence.PrePersist;
import jakarta.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.entity.master.DummyUserMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.MvEmployeeMaster;
import jp.co.ricoh.cotos.commonlib.repository.master.DummyUserMasterRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.MvEmployeeMasterRepository;

@Component
public class EstimationApprovalResultListener {

	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;
	private static DummyUserMasterRepository dummyUserMasterRepository;

	@Autowired
	public void setMvEmployeeMasterRepository(MvEmployeeMasterRepository mvEmployeeMasterRepository) {
		EstimationApprovalResultListener.mvEmployeeMasterRepository = mvEmployeeMasterRepository;
	}

	@Autowired
	public void setDummyUserMasterRepository(DummyUserMasterRepository dummyUserMasterRepository) {
		EstimationApprovalResultListener.dummyUserMasterRepository = dummyUserMasterRepository;
	}

	/**
	 * 社員マスタ情報を見積承認実績トランザクションに紐づけます。
	 *
	 * @param estimationApprovalResult
	 */
	@PrePersist
	@Transactional
	public void appendsEmployeeFields(EstimationApprovalResult estimationApprovalResult) {
		if (dummyUserMasterRepository.existsByUserId(estimationApprovalResult.getActualEmpId())) {
			DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(estimationApprovalResult.getActualEmpId());
			estimationApprovalResult.setActualUserName(dummyUserMaster.getEmpName());
			estimationApprovalResult.setActualOrgName(dummyUserMaster.getOrgName());
			return;
		}

		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(estimationApprovalResult.getActualEmpId());

		if (employeeMaster == null) {
			return;
		}

		if (StringUtils.isBlank(estimationApprovalResult.getActualUserName())) {
			estimationApprovalResult.setActualUserName(employeeMaster.getJobname1() + employeeMaster.getJobname2());
		}
		if (StringUtils.isBlank(estimationApprovalResult.getActualOrgName())) {
			estimationApprovalResult.setActualOrgName(employeeMaster.getOrgName());
		}
	}
}
