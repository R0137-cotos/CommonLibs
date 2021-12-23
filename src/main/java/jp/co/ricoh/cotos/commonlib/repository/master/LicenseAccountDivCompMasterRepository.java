package jp.co.ricoh.cotos.commonlib.repository.master;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.LicenseAccountDivCompMaster;

@Repository
public interface LicenseAccountDivCompMasterRepository extends CrudRepository<LicenseAccountDivCompMaster, Long> {

	public LicenseAccountDivCompMaster findByLicenseAccountDivMasterId(long licenseAccountDivMasterId);

	public LicenseAccountDivCompMaster findByProductMasterId(long productMasterId);
}
