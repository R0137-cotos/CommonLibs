package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.ArrayList;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.DummyCodeValue;
import jp.co.ricoh.cotos.commonlib.entity.master.VKjbMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.VKjbMaster.DepartmentDiv;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.repository.master.VKjbMasterRepository;

@Component
public class CustomerContractListener {
	private static VKjbMasterRepository vKjbMasterRepository;
	private static CheckUtil checkUtil;

	@Autowired
	public void setVkjbMasterRepository(VKjbMasterRepository vKjbMasterRepository) {
		CustomerContractListener.vKjbMasterRepository = vKjbMasterRepository;
	}

	@Autowired
	public void setCheckUtil(CheckUtil checkUtil) {
		CustomerContractListener.checkUtil = checkUtil;
	}

	@PrePersist
	@Transactional
	public void appendsCustomerContractFields(CustomerContract customerContract) {

		if (DummyCodeValue.Dummy_Mcl_MoM_Rel_Id.toString().equals(customerContract.getMomKjbSystemId())) {
			return;
		}

		VKjbMaster vKjbMaster = vKjbMasterRepository.findByMclMomRelId(customerContract.getMomKjbSystemId());
		if (vKjbMaster == null) {
			String[] regexList = { "顧客（契約用）" };
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "MasterDoesNotExistKjbMaster", regexList));
		}

	}

	@PreUpdate
	@Transactional
	public void updateCustomerContractFields(CustomerContract customerContract) {
		customerContract.setCustomerName(this.convertJoinedCustomerName(customerContract));
	}

	private String convertJoinedCustomerName(CustomerContract customerContract) {

		StringBuilder sb = new StringBuilder();

		sb.append(StringUtils.defaultIfEmpty(customerContract.getCompanyName(), StringUtils.EMPTY));
		sb.append(StringUtils.defaultIfEmpty(customerContract.getOfficeName(), StringUtils.EMPTY));

		if (DepartmentDiv.企事部.equals(customerContract.getDepartmentDiv())) {
			sb.append(StringUtils.defaultIfEmpty(customerContract.getDepartmentName(), StringUtils.EMPTY));
		}

		return sb.toString();
	}
}
