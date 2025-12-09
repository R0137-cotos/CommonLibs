package jp.co.ricoh.cotos.commonlib.repository.license;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo;

@Repository
public interface LicenseInfoRepository extends CrudRepository<LicenseInfo, Long> {

	public LicenseInfo findByContractIdAndDisengagementFlg(long contractId, int disengagementFlg);

	public List<LicenseInfo> findByRjManageNumber(String rjManageNumber);

	public List<LicenseInfo> findByContractNumber(String contractNumber);

	public List<LicenseInfo> findByRjManageNumberAndContractNumberAndDisengagementFlg(String rjManageNumber, String contractNumber, int disengagementFlg);

	@Query(value = "SELECT * FROM LICENSE_INFO WHERE CONTRACT_ID = :contractId and DISENGAGEMENT_FLG = :disengagementFlg", nativeQuery = true)
	public List<LicenseInfo> findByContractIdAndDisengagementFlgReturnList(@Param("contractId")long contractId, @Param("disengagementFlg")int disengagementFlg);
}
