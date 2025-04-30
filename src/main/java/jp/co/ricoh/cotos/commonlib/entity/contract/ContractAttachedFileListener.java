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
public class ContractAttachedFileListener {

	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;
	private static DummyUserMasterRepository dummyUserMasterRepository;

	@Autowired
	public void setMvEmployeeMasterRepository(MvEmployeeMasterRepository mvEmployeeMasterRepository) {
		ContractAttachedFileListener.mvEmployeeMasterRepository = mvEmployeeMasterRepository;
	}

	@Autowired
	public void setDummyUserMasterRepository(DummyUserMasterRepository dummyUserMasterRepository) {
		ContractAttachedFileListener.dummyUserMasterRepository = dummyUserMasterRepository;
	}

	/**
	 * 社員マスタ情報を契約添付ファイルトランザクションに紐づけます。
	 *
	 * @param contractAttachedFile
	 */
	@PrePersist
	@Transactional
	public void appendsEmployeeFields(ContractAttachedFile contractAttachedFile) {
		if (dummyUserMasterRepository.existsByUserId(contractAttachedFile.getAttachedEmpId())) {
			DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(contractAttachedFile.getAttachedEmpId());
			contractAttachedFile.setAttachedEmpName(dummyUserMaster.getEmpName());
			contractAttachedFile.setAttachedOrgName(dummyUserMaster.getOrgName());
			return;
		}

		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(contractAttachedFile.getAttachedEmpId());

		if (employeeMaster == null) {
			return;
		}

		if (StringUtils.isBlank(contractAttachedFile.getAttachedEmpName())) {
			contractAttachedFile.setAttachedEmpName(employeeMaster.getJobname1() + employeeMaster.getJobname2());
		}
		if (StringUtils.isBlank(contractAttachedFile.getAttachedOrgName())) {
			contractAttachedFile.setAttachedOrgName(employeeMaster.getOrgName());
		}
	}

}
