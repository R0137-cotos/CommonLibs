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
public class EstimationAttachedFileListener {

	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;
	private static DummyUserMasterRepository dummyUserMasterRepository;

	@Autowired
	public void setMvEmployeeMasterRepository(MvEmployeeMasterRepository mvEmployeeMasterRepository) {
		EstimationAttachedFileListener.mvEmployeeMasterRepository = mvEmployeeMasterRepository;
	}

	@Autowired
	public void setDummyUserMasterRepository(DummyUserMasterRepository dummyUserMasterRepository) {
		EstimationAttachedFileListener.dummyUserMasterRepository = dummyUserMasterRepository;
	}

	/**
	 * 社員マスタ情報を見積添付ファイルトランザクションに紐づけます。
	 *
	 * @param estimationAttachedFile
	 */
	@PrePersist
	@Transactional
	public void appendsEmployeeFields(EstimationAttachedFile estimationAttachedFile) {
		if (dummyUserMasterRepository.existsByUserId(estimationAttachedFile.getAttachedEmpId())) {
			DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(estimationAttachedFile.getAttachedEmpId());
			estimationAttachedFile.setAttachedEmpName(dummyUserMaster.getEmpName());
			estimationAttachedFile.setAttachedOrgName(dummyUserMaster.getOrgName());
			return;
		}

		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(estimationAttachedFile.getAttachedEmpId());

		if (employeeMaster == null) {
			return;
		}

		if (StringUtils.isBlank(estimationAttachedFile.getAttachedEmpName())) {
			estimationAttachedFile.setAttachedEmpName(employeeMaster.getJobname1() + employeeMaster.getJobname2());
		}
		if (StringUtils.isBlank(estimationAttachedFile.getAttachedOrgName())) {
			estimationAttachedFile.setAttachedOrgName(employeeMaster.getOrgName());
		}
	}

}
