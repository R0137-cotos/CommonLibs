package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.LicenseServiceCompMaster;

@Repository
public interface LicenseServiceCompMasterRepository extends CrudRepository<LicenseServiceCompMaster, Long> {

	public List<LicenseServiceCompMaster> findByLicenseServiceMasterId(long licenseServiceMasterId);

	public List<LicenseServiceCompMaster> findByItemMasterId(long itemMasterId);
}
