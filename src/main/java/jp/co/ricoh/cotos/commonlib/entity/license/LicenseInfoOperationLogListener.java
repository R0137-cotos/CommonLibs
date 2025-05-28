package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.ArrayList;

import jakarta.persistence.PrePersist;
import jakarta.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.entity.master.DummyUserMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.MvEmployeeMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.repository.master.DummyUserMasterRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.MvEmployeeMasterRepository;
import jp.co.ricoh.cotos.commonlib.security.CotosAuthenticationDetails;

@Component
public class LicenseInfoOperationLogListener {

	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;

	private static CheckUtil checkUtil;

	private static DummyUserMasterRepository dummyUserMasterRepository;

	@Autowired
	public void setMvEmployeeRepository(MvEmployeeMasterRepository mvEmployeeMasterRepository) {
		LicenseInfoOperationLogListener.mvEmployeeMasterRepository = mvEmployeeMasterRepository;
	}

	@Autowired
	public void setCheckUtil(CheckUtil checkUtil) {
		LicenseInfoOperationLogListener.checkUtil = checkUtil;
	}

	@Autowired
	public void setDummyUserMasterRepository(DummyUserMasterRepository dummyUserMasterRepository) {
		LicenseInfoOperationLogListener.dummyUserMasterRepository = dummyUserMasterRepository;
	}

	/**
	 * 社員マスタ情報を手配業務操作履歴トランザクションに紐づけます。
	 *
	 * @param licenseInfoOperationLog
	 */
	@PrePersist
	@Transactional
	public void appendsEmployeeFields(LicenseInfoOperationLog licenseInfoOperationLog) {

		// ユーザ情報を取得
		CotosAuthenticationDetails userInfo = (CotosAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// ダミーユーザの場合
		if (userInfo.isDummyUser()) {
			DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(userInfo.getMomEmployeeId());
			licenseInfoOperationLog.setOperatorEmpId(dummyUserMaster.getUserId());
			licenseInfoOperationLog.setOperatorName(dummyUserMaster.getEmpName());
			licenseInfoOperationLog.setOperatorOrgName(dummyUserMaster.getOrgName());
			return;
		}

		// RJ社員情報マスタを取得
		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(licenseInfoOperationLog.getOperatorEmpId());

		// RJ社員情報マスタが取得できない場合、エラーとして処理を終了する。
		if (employeeMaster == null) {
			String[] regexList = { "操作者MoM社員ID" };
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "MasterDoesNotExistEmployeeMaster", regexList));
		}

		// 操作者氏名が設定されていない場合、RJ社員情報マスタの氏名を設定
		if (StringUtils.isBlank(licenseInfoOperationLog.getOperatorName())) {
			licenseInfoOperationLog.setOperatorName(employeeMaster.getJobname1() + employeeMaster.getJobname2());
		}

		// 操作者組織名が設定されていない場合、RJ社員情報マスタの組織簡略名称を設定
		if (StringUtils.isBlank(licenseInfoOperationLog.getOperatorOrgName())) {
			licenseInfoOperationLog.setOperatorOrgName(employeeMaster.getOrgName());
		}
	}
}
