package jp.co.ricoh.cotos.commonlib.repository.license;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseKeyInfo;

@Repository
public interface LicenseKeyInfoRepository extends CrudRepository<LicenseKeyInfo, Long> {

}
