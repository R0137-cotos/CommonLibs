package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.LicenseDivCompMaster;

@Repository
public interface LicenseDivCompMasterRepository extends CrudRepository<LicenseDivCompMaster, Long> {

	public LicenseDivCompMaster findByLicenseDivMasterId(long licenseDivMasterId);

	public LicenseDivCompMaster findByProductMasterId(long productMasterId);

	@Query(value = "SELECT * FROM LICENSE_DIV_COMP_MASTER WHERE PRODUCT_MASTER_ID = :productMasterId", nativeQuery = true)
	public List<LicenseDivCompMaster> findByProductMasterIdReturnList(@Param("productMasterId")long productMasterId);
}
