package jp.co.ricoh.cotos.commonlib.repository.license;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseKeyInfo;

@Repository
public interface LicenseKeyInfoRepository extends CrudRepository<LicenseKeyInfo, Long> {

	public List<LicenseKeyInfo> findByLicenseAccountId(Long licenseAccountId);
}
