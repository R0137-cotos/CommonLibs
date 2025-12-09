package jp.co.ricoh.cotos.commonlib.entity.contract;

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
public class ContractOperationLogListener {

	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;
	private static DummyUserMasterRepository dummyUserMasterRepository;

	@Autowired
	public void setMvEmployeeMasterRepository(MvEmployeeMasterRepository mvEmployeeMasterRepository) {
		ContractOperationLogListener.mvEmployeeMasterRepository = mvEmployeeMasterRepository;
	}

	@Autowired
	public void setDummyUserMasterRepository(DummyUserMasterRepository dummyUserMasterRepository) {
		ContractOperationLogListener.dummyUserMasterRepository = dummyUserMasterRepository;
	}

	/**
	 * 社員マスタ情報を契約操作履歴トランザクションに紐づけます。
	 * バッチユーザーの場合は固定値を設定します。
	 *
	 * @param contractOperationLog
	 */
	@PrePersist
	@Transactional
	public void appendsEmployeeFields(ContractOperationLog contractOperationLog) {

		CotosAuthenticationDetails userInfo = (CotosAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (userInfo.isDummyUser()) {
			DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(userInfo.getMomEmployeeId());
			contractOperationLog.setOperatorEmpId(dummyUserMaster.getUserId());
			contractOperationLog.setOperatorName(dummyUserMaster.getEmpName());
			contractOperationLog.setOperatorOrgName(dummyUserMaster.getOrgName());
			return;
		}

		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(contractOperationLog.getOperatorEmpId());
		if (employeeMaster == null) {
			return;
		}

		if (StringUtils.isBlank(contractOperationLog.getOperatorName())) {
			contractOperationLog.setOperatorName(employeeMaster.getJobname1() + employeeMaster.getJobname2());
		}
		if (StringUtils.isBlank(contractOperationLog.getOperatorOrgName())) {
			contractOperationLog.setOperatorOrgName(employeeMaster.getOrgName());
		}
	}

}
