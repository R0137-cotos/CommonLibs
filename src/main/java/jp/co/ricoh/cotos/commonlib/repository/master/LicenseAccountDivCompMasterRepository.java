package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.LicenseAccountDivCompMaster;

@Repository
public interface LicenseAccountDivCompMasterRepository extends CrudRepository<LicenseAccountDivCompMaster, Long> {

	public List<LicenseAccountDivCompMaster> findByLicenseAccountDivMasterId(long licenseAccountDivMasterId);

	public List<LicenseAccountDivCompMaster> findByProductMasterId(long productMasterId);
}
