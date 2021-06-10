package jp.co.ricoh.cotos.commonlib.repository.license;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseRemainingNumber;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseRemainingNumber.AllocationDiv;

@Repository
public interface LicenseRemainingNumberRepository extends CrudRepository<LicenseRemainingNumber, Long> {

	public List<LicenseRemainingNumber> findByLicenseDivMasterIdAndAllocationDiv(Long licenseDivMasterId, AllocationDiv allocationDiv);

	public List<LicenseRemainingNumber> findByRjManageNumber(String rjManageNumber);

	public LicenseRemainingNumber findByLicenseDivMasterIdAndLicenseKey(Long licenceDivMasterId, String licenseKey);

	public List<LicenseRemainingNumber> findByRjManageNumberAndAllocationDiv(String rjManageNumber, AllocationDiv allocationDiv);
}