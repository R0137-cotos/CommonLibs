package jp.co.ricoh.cotos.commonlib.repository.master;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.LicenseKeyCompMaster;

@Repository
public interface LicenseKeyCompMasterRepository extends CrudRepository<LicenseKeyCompMaster, Long> {

	public LicenseKeyCompMaster findByLicenseServiceMasterId(long licenseServiceMasterId);

	public LicenseKeyCompMaster findByItemCodeMasterId(long itemCodeMasterId);
}
