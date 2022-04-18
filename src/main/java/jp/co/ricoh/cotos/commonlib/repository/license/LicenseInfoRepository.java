package jp.co.ricoh.cotos.commonlib.repository.license;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo;

@Repository
public interface LicenseInfoRepository extends CrudRepository<LicenseInfo, Long> {

	public LicenseInfo findByContractIdAndDisengagementFlg(long contractId, int disengagementFlg);

	public List<LicenseInfo> findByRjManageNumber(String rjManageNumber);

	public List<LicenseInfo> findByContractNumber(String contractNumber);

	public List<LicenseInfo> findByRjManageNumberAndContractNumberAndDisengagementFlg(String rjManageNumber, String contractNumber, int disengagementFlg);

}
