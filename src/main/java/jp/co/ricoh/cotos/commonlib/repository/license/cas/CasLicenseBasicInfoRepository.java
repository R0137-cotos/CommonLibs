package jp.co.ricoh.cotos.commonlib.repository.license.cas;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.CasLicenseStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.CasLicenseBasicInfo;

@Repository
public interface CasLicenseBasicInfoRepository extends CrudRepository<CasLicenseBasicInfo, Long> {

	public List<CasLicenseBasicInfo> findByCustomerId(String customerId);

	public List<CasLicenseBasicInfo> findByCustomerIdAndLicenseStatus(String customerId, CasLicenseStatus licenseStatus);

	public List<CasLicenseBasicInfo> findByMvbAccount(String mvbAccount);
}
