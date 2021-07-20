package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessMaster;

@Repository
public interface LicenseProcessMasterRepository extends CrudRepository<LicenseProcessMaster, Long> {
	public List<LicenseProcessMaster> findByLicenseDivMasterIdOrderById(long licenseDivMasterId);
}
