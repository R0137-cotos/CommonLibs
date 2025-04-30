package jp.co.ricoh.cotos.commonlib.entity.arrangement;

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
public class ArrangementWorkApprovalResultListener {

	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;
	private static DummyUserMasterRepository dummyUserMasterRepository;

	@Autowired
	public void setMvEmployeeMasterRepository(MvEmployeeMasterRepository mvEmployeeMasterRepository) {
		ArrangementWorkApprovalResultListener.mvEmployeeMasterRepository = mvEmployeeMasterRepository;
	}

	@Autowired
	public void setDummyUserMasterRepository(DummyUserMasterRepository dummyUserMasterRepository) {
		ArrangementWorkApprovalResultListener.dummyUserMasterRepository = dummyUserMasterRepository;
	}

	/**
	 * 社員マスタ情報を手配業務承認実績トランザクションに紐づけます。
	 *
	 * @param arrangementWorkApprovalResult
	 */
	@PrePersist
	@Transactional
	public void appendsEmployeeFields(ArrangementWorkApprovalResult arrangementWorkApprovalResult) {
		if (dummyUserMasterRepository.existsByUserId(arrangementWorkApprovalResult.getActualEmpId())) {
			DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(arrangementWorkApprovalResult.getActualEmpId());
			arrangementWorkApprovalResult.setActualUserName(dummyUserMaster.getEmpName());
			arrangementWorkApprovalResult.setActualOrgName(dummyUserMaster.getOrgName());
			return;
		}

		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(arrangementWorkApprovalResult.getActualEmpId());

		if (employeeMaster == null) {
			return;
		}

		if (StringUtils.isBlank(arrangementWorkApprovalResult.getActualUserName())) {
			arrangementWorkApprovalResult.setActualUserName(employeeMaster.getJobname1() + employeeMaster.getJobname2());
		}
		if (StringUtils.isBlank(arrangementWorkApprovalResult.getActualOrgName())) {
			arrangementWorkApprovalResult.setActualOrgName(employeeMaster.getOrgName());
		}
	}
}
