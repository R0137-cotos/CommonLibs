package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.ArrayList;

import javax.persistence.PrePersist;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.ApplicationContextProvider;
import jp.co.ricoh.cotos.commonlib.entity.master.DummyUserMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.MvEmployeeMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.provider.UtilProvider;
import jp.co.ricoh.cotos.commonlib.repository.master.DummyUserMasterRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.MvEmployeeMasterRepository;

@Component
public class ContractApprovalResultListener {

	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;
	private static CheckUtil checkUtil;
	private static DummyUserMasterRepository dummyUserMasterRepository;

	/**
	 * 社員マスタ情報を契約承認実績トランザクションに紐づけます。
	 *
	 * @param contractApprovalResult
	 */
	@PrePersist
	@Transactional
	public void appendsEmployeeFields(ContractApprovalResult contractApprovalResult) {
		// Beanの取得
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		mvEmployeeMasterRepository = context.getBean(MvEmployeeMasterRepository.class);
		checkUtil = UtilProvider.getCheckUtil();
		dummyUserMasterRepository = context.getBean(DummyUserMasterRepository.class);

		if (dummyUserMasterRepository.existsByUserId(contractApprovalResult.getActualEmpId())) {
			DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(contractApprovalResult.getActualEmpId());
			contractApprovalResult.setActualUserName(dummyUserMaster.getEmpName());
			contractApprovalResult.setActualOrgName(dummyUserMaster.getOrgName());
			return;
		}

		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(contractApprovalResult.getActualEmpId());

		if (employeeMaster == null) {
			String[] regexList = { "操作者MoM社員ID" };
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "MasterDoesNotExistEmployeeMaster", regexList));
		}

		if (StringUtils.isBlank(contractApprovalResult.getActualUserName())) {
			contractApprovalResult.setActualUserName(employeeMaster.getJobname1() + employeeMaster.getJobname2());
		}
		if (StringUtils.isBlank(contractApprovalResult.getActualOrgName())) {
			contractApprovalResult.setActualOrgName(employeeMaster.getOrgName());
		}
	}
}
