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
public class ContractAssignmentAttachedFileListener {

	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;
	private static DummyUserMasterRepository dummyUserMasterRepository;

	@Autowired
	public void setMvEmployeeMasterRepository(MvEmployeeMasterRepository mvEmployeeMasterRepository) {
		ContractAssignmentAttachedFileListener.mvEmployeeMasterRepository = mvEmployeeMasterRepository;
	}

	@Autowired
	public void setDummyUserMasterRepository(DummyUserMasterRepository dummyUserMasterRepository) {
		ContractAssignmentAttachedFileListener.dummyUserMasterRepository = dummyUserMasterRepository;
	}

	/**
	 * 社員マスタ情報を契約業務添付ファイルトランザクションに紐づけます。
	 *
	 * @param contractAssignmentAttachedFile
	 */
	@PrePersist
	@Transactional
	public void appendsEmployeeFields(ContractAssignmentAttachedFile contractAssignmentAttachedFile) {
		if (dummyUserMasterRepository.existsByUserId(contractAssignmentAttachedFile.getAttachedEmpId())) {
			DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(contractAssignmentAttachedFile.getAttachedEmpId());
			contractAssignmentAttachedFile.setAttachedEmpName(dummyUserMaster.getEmpName());
			contractAssignmentAttachedFile.setAttachedOrgName(dummyUserMaster.getOrgName());
			return;
		}

		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(contractAssignmentAttachedFile.getAttachedEmpId());

		if (employeeMaster == null) {
			return;
		}

		if (StringUtils.isBlank(contractAssignmentAttachedFile.getAttachedEmpName())) {
			contractAssignmentAttachedFile.setAttachedEmpName(employeeMaster.getJobname1() + employeeMaster.getJobname2());
		}
		if (StringUtils.isBlank(contractAssignmentAttachedFile.getAttachedOrgName())) {
			contractAssignmentAttachedFile.setAttachedOrgName(employeeMaster.getOrgName());
		}
	}
}
