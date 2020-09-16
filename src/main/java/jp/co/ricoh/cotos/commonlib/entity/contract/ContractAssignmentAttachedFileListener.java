package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.ArrayList;

import javax.persistence.PrePersist;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.entity.master.DummyUserMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.MvEmployeeMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.provider.ApplicationContextProvider;
import jp.co.ricoh.cotos.commonlib.provider.UtilProvider;
import jp.co.ricoh.cotos.commonlib.repository.master.DummyUserMasterRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.MvEmployeeMasterRepository;

@Component
public class ContractAssignmentAttachedFileListener {

	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;
	private static CheckUtil checkUtil;
	private static DummyUserMasterRepository dummyUserMasterRepository;

	/**
	 * 社員マスタ情報を契約業務添付ファイルトランザクションに紐づけます。
	 *
	 * @param contractAssignmentAttachedFile
	 */
	@PrePersist
	@Transactional
	public void appendsEmployeeFields(ContractAssignmentAttachedFile contractAssignmentAttachedFile) {
		// Beanの取得
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		mvEmployeeMasterRepository = context.getBean(MvEmployeeMasterRepository.class);
		checkUtil = UtilProvider.getCheckUtil();
		dummyUserMasterRepository = context.getBean(DummyUserMasterRepository.class);

		if (dummyUserMasterRepository.existsByUserId(contractAssignmentAttachedFile.getAttachedEmpId())) {
			DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(contractAssignmentAttachedFile.getAttachedEmpId());
			contractAssignmentAttachedFile.setAttachedEmpName(dummyUserMaster.getEmpName());
			contractAssignmentAttachedFile.setAttachedOrgName(dummyUserMaster.getOrgName());
			return;
		}

		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(contractAssignmentAttachedFile.getAttachedEmpId());

		if (employeeMaster == null) {
			String[] regexList = { "添付者MoM社員ID" };
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "MasterDoesNotExistEmployeeMaster", regexList));
		}

		if (StringUtils.isBlank(contractAssignmentAttachedFile.getAttachedEmpName())) {
			contractAssignmentAttachedFile.setAttachedEmpName(employeeMaster.getJobname1() + employeeMaster.getJobname2());
		}
		if (StringUtils.isBlank(contractAssignmentAttachedFile.getAttachedOrgName())) {
			contractAssignmentAttachedFile.setAttachedOrgName(employeeMaster.getOrgName());
		}
	}
}
