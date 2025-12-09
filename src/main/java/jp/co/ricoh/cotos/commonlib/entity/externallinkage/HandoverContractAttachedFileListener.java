package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

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
public class HandoverContractAttachedFileListener {

	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;
	private static DummyUserMasterRepository dummyUserMasterRepository;

	@Autowired
	public void setMvEmployeeMasterRepository(MvEmployeeMasterRepository mvEmployeeMasterRepository) {
		HandoverContractAttachedFileListener.mvEmployeeMasterRepository = mvEmployeeMasterRepository;
	}

	@Autowired
	public void setDummyUserMasterRepository(DummyUserMasterRepository dummyUserMasterRepository) {
		HandoverContractAttachedFileListener.dummyUserMasterRepository = dummyUserMasterRepository;
	}

	/**
	 * 社員マスタ情報を引継ぎ用契約添付ファイルトランザクションに紐づけます。
	 *
	 * @param handoverContractAttachedFile
	 */
	@PrePersist
	@Transactional
	public void appendsEmployeeFields(HandoverContractAttachedFile handoverContractAttachedFile) {
		if (dummyUserMasterRepository.existsByUserId(handoverContractAttachedFile.getAttachedEmpId())) {
			DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(handoverContractAttachedFile.getAttachedEmpId());
			handoverContractAttachedFile.setAttachedEmpName(dummyUserMaster.getEmpName());
			handoverContractAttachedFile.setAttachedOrgName(dummyUserMaster.getOrgName());
			return;
		}

		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(handoverContractAttachedFile.getAttachedEmpId());

		if (employeeMaster == null) {
			return;
		}

		if (StringUtils.isBlank(handoverContractAttachedFile.getAttachedEmpName())) {
			handoverContractAttachedFile.setAttachedEmpName(employeeMaster.getJobname1() + employeeMaster.getJobname2());
		}
		if (StringUtils.isBlank(handoverContractAttachedFile.getAttachedOrgName())) {
			handoverContractAttachedFile.setAttachedOrgName(employeeMaster.getOrgName());
		}
	}

}
