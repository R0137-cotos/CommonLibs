package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.ArrayList;

import javax.persistence.PrePersist;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.master.DummyUserMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.MvEmployeeMaster;
import jp.co.ricoh.cotos.commonlib.exception.ErrorCheckException;
import jp.co.ricoh.cotos.commonlib.exception.ErrorInfo;
import jp.co.ricoh.cotos.commonlib.logic.check.CheckUtil;
import jp.co.ricoh.cotos.commonlib.repository.master.DummyUserMasterRepository;
import jp.co.ricoh.cotos.commonlib.repository.master.MvEmployeeMasterRepository;

@Component
public class ShippingAddressListener {

	private static MvEmployeeMasterRepository mvEmployeeMasterRepository;
	private static CheckUtil checkUtil;
	private static DummyUserMasterRepository dummyUserMasterRepository;

	@Autowired
	public void setMvEmployeeMasterRepository(MvEmployeeMasterRepository mvEmployeeMasterRepository) {
		ShippingAddressListener.mvEmployeeMasterRepository = mvEmployeeMasterRepository;
	}

	@Autowired
	public void setCheckUtil(CheckUtil checkUtil) {
		ShippingAddressListener.checkUtil = checkUtil;
	}

	@Autowired
	public void setDummyUserMasterRepository(DummyUserMasterRepository dummyUserMasterRepository) {
		ShippingAddressListener.dummyUserMasterRepository = dummyUserMasterRepository;
	}

	/**
	 * 社員マスタ情報を配送先トランザクションに紐づけます。
	 *
	 * @param shippingAddress
	 */
	@PrePersist
	@Transactional
	public void appendsEmployeeFields(ShippingAddress shippingAddress) {
		if (dummyUserMasterRepository.existsByUserId(shippingAddress.getMomEmployeeId())) {
			DummyUserMaster dummyUserMaster = dummyUserMasterRepository.findByUserId(shippingAddress.getMomEmployeeId());
			shippingAddress.setEmployeeName(dummyUserMaster.getEmpName());
			return;
		}

		MvEmployeeMaster employeeMaster = mvEmployeeMasterRepository.findByMomEmployeeId(shippingAddress.getMomEmployeeId());

		ContractType contractType = shippingAddress.getContract().getContractType();
		if (employeeMaster == null && ContractType.新規 == contractType) {
			String[] regexList = { "配送先" };
			throw new ErrorCheckException(checkUtil.addErrorInfo(new ArrayList<ErrorInfo>(), "MasterDoesNotExistEmployeeMaster", regexList));
		}

		if (employeeMaster == null) {
			return;
		}

		BeanUtils.copyProperties(employeeMaster, shippingAddress, "orgName", "salesCompanyName", "orgPhoneNumber", "salesDepartmentName", "employeeName", "postNumber", "phoneNumber", "prefectures", "cityStreet", "buildingName");
		if (StringUtils.isBlank(shippingAddress.getOrgName())) {
			shippingAddress.setOrgName(employeeMaster.getOrgName());
		}
		if (StringUtils.isBlank(shippingAddress.getSalesCompanyName())) {
			shippingAddress.setSalesCompanyName(employeeMaster.getHanshSeiskNm());
		}
		if (StringUtils.isBlank(shippingAddress.getOrgPhoneNumber())) {
			shippingAddress.setOrgPhoneNumber(employeeMaster.getOrgPhoneNumber());
		}
		if (StringUtils.isBlank(shippingAddress.getSalesDepartmentName())) {
			shippingAddress.setSalesDepartmentName(employeeMaster.getSalesDepartmentName());
		}
		if (StringUtils.isBlank(shippingAddress.getEmployeeName())) {
			shippingAddress.setEmployeeName(employeeMaster.getJobname1() + employeeMaster.getJobname2());
		}
	}
}
