package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.LicenseDivMaster;

@Repository
public interface LicenseDivMasterRepository extends CrudRepository<LicenseDivMaster, Long> {
	public List<LicenseDivMaster> findAllByOrderByIdAsc ();

	public LicenseDivMaster findByLicenseClassDiv(String licenseClassDiv);
}
