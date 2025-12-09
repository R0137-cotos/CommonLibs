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
public class ArrangementWorkAttachedFileListener {

	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;
	private static DummyUserMasterRepository dummyUserMasterRepository;

	@Autowired
	public void setMvEmployeeMasterRepository(MvEmployeeMasterRepository mvEmployeeMasterRepository) {
		ArrangementWorkAttachedFileListener.mvEmployeeMasterRepository = mvEmployeeMasterRepository;
	}

	@Autowired
	public void setDummyUserMasterRepository(DummyUserMasterRepository dummyUserMasterRepository) {
		ArrangementWorkAttachedFileListener.dummyUserMasterRepository = dummyUserMasterRepository;
	}

	/**
	 * 社員マスタ情報を手配業務添付ファイルトランザクションに紐づけます。
	 *
	 * @param arrangementWorkAttachedFile
	 */
	@PrePersist
	@Transactional
	public void appendsEmployeeFields(ArrangementWorkAttachedFile arrangementWorkAttachedFile) {
		if (dummyUserMasterRepository.existsByUserId(arrangementWorkAttachedFile.getAttachedEmpId())) {
			DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(arrangementWorkAttachedFile.getAttachedEmpId());
			arrangementWorkAttachedFile.setAttachedEmpName(dummyUserMaster.getEmpName());
			arrangementWorkAttachedFile.setAttachedOrgName(dummyUserMaster.getOrgName());
			return;
		}

		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(arrangementWorkAttachedFile.getAttachedEmpId());

		if (employeeMaster == null) {
			return;
		}

		if (StringUtils.isBlank(arrangementWorkAttachedFile.getAttachedEmpName())) {
			arrangementWorkAttachedFile.setAttachedEmpName(employeeMaster.getJobname1() + employeeMaster.getJobname2());
		}
		if (StringUtils.isBlank(arrangementWorkAttachedFile.getAttachedOrgName())) {
			arrangementWorkAttachedFile.setAttachedOrgName(employeeMaster.getOrgName());
		}
	}

}
