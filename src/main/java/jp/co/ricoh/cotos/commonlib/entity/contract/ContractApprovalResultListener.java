package jp.co.ricoh.cotos.commonlib.entity.contract;

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
public class ContractApprovalResultListener {

	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;
	private static DummyUserMasterRepository dummyUserMasterRepository;

	@Autowired
	public void setMvEmployeeMasterRepository(MvEmployeeMasterRepository mvEmployeeMasterRepository) {
		ContractApprovalResultListener.mvEmployeeMasterRepository = mvEmployeeMasterRepository;
	}

	@Autowired
	public void setDummyUserMasterRepository(DummyUserMasterRepository dummyUserMasterRepository) {
		ContractApprovalResultListener.dummyUserMasterRepository = dummyUserMasterRepository;
	}

	/**
	 * 社員マスタ情報を契約承認実績トランザクションに紐づけます。
	 *
	 * @param contractApprovalResult
	 */
	@PrePersist
	@Transactional
	public void appendsEmployeeFields(ContractApprovalResult contractApprovalResult) {
		if (dummyUserMasterRepository.existsByUserId(contractApprovalResult.getActualEmpId())) {
			DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(contractApprovalResult.getActualEmpId());
			contractApprovalResult.setActualUserName(dummyUserMaster.getEmpName());
			contractApprovalResult.setActualOrgName(dummyUserMaster.getOrgName());
			return;
		}

		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(contractApprovalResult.getActualEmpId());

		if (employeeMaster == null) {
			return;
		}

		if (StringUtils.isBlank(contractApprovalResult.getActualUserName())) {
			contractApprovalResult.setActualUserName(employeeMaster.getJobname1() + employeeMaster.getJobname2());
		}
		if (StringUtils.isBlank(contractApprovalResult.getActualOrgName())) {
			contractApprovalResult.setActualOrgName(employeeMaster.getOrgName());
		}
	}
}
