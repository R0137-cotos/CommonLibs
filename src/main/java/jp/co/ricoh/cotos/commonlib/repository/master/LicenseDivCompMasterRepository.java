package jp.co.ricoh.cotos.commonlib.repository.master;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.LicenseDivCompMaster;

@Repository
public interface LicenseDivCompMasterRepository extends CrudRepository<LicenseDivCompMaster, Long> {

	public LicenseDivCompMaster findByLicenseDivMasterId(long licenseDivMasterId);

	public LicenseDivCompMaster findByProductMasterId(long productMasterId);
}
