package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.ArrayList;

import javax.persistence.PrePersist;
import javax.transaction.Transactional;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.entity.master.MvEmployeeMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.provider.ApplicationContextProvider;
import jp.co.ricoh.cotos.commonlib.provider.UtilProvider;
import jp.co.ricoh.cotos.commonlib.repository.master.DummyUserMasterRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.MvEmployeeMasterRepository;

@Component
public class ContractPicSaEmpListener {
	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;
	private static CheckUtil checkUtil;
	private static DummyUserMasterRepository dummyUserMasterRepository;

	@PrePersist
	@Transactional
	public void appendsEmployeeFields(ContractPicSaEmp contractPicSaEmp) {
		// Beanの取得
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		mvEmployeeMasterRepository = context.getBean(MvEmployeeMasterRepository.class);
		checkUtil = UtilProvider.getCheckUtil();
		dummyUserMasterRepository = context.getBean(DummyUserMasterRepository.class);

		//ダミーユーザーであるかどうかのチェック
		if (dummyUserMasterRepository.existsByUserId(contractPicSaEmp.getMomEmployeeId())) {
			return;
		}

		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(contractPicSaEmp.getMomEmployeeId());

		if (employeeMaster == null) {
			String[] regexList = { "契約担当SA社員" };
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "MasterDoesNotExistEmployeeMaster", regexList));
		}

	}

}
