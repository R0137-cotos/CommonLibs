package jp.co.ricoh.cotos.commonlib.entity.estimation;

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
public class EstimationAttachedFileListener {

	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;
	private static CheckUtil checkUtil;
	private static DummyUserMasterRepository dummyUserMasterRepository;

	/**
	 * 社員マスタ情報を見積添付ファイルトランザクションに紐づけます。
	 *
	 * @param estimationAttachedFile
	 */
	@PrePersist
	@Transactional
	public void appendsEmployeeFields(EstimationAttachedFile estimationAttachedFile) {
		// Beanの取得
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		mvEmployeeMasterRepository = context.getBean(MvEmployeeMasterRepository.class);
		checkUtil = UtilProvider.getCheckUtil();
		dummyUserMasterRepository = context.getBean(DummyUserMasterRepository.class);

		if (dummyUserMasterRepository.existsByUserId(estimationAttachedFile.getAttachedEmpId())) {
			DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(estimationAttachedFile.getAttachedEmpId());
			estimationAttachedFile.setAttachedEmpName(dummyUserMaster.getEmpName());
			estimationAttachedFile.setAttachedOrgName(dummyUserMaster.getOrgName());
			return;
		}

		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(estimationAttachedFile.getAttachedEmpId());

		if (employeeMaster == null) {
			String[] regexList = { "添付者MoM社員ID" };
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "MasterDoesNotExistEmployeeMaster", regexList));
		}

		if (StringUtils.isBlank(estimationAttachedFile.getAttachedEmpName())) {
			estimationAttachedFile.setAttachedEmpName(employeeMaster.getJobname1() + employeeMaster.getJobname2());
		}
		if (StringUtils.isBlank(estimationAttachedFile.getAttachedOrgName())) {
			estimationAttachedFile.setAttachedOrgName(employeeMaster.getOrgName());
		}
	}

}
