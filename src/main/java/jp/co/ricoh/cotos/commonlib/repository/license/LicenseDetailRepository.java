package jp.co.ricoh.cotos.commonlib.repository.license;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail;

@Repository
public interface LicenseDetailRepository extends CrudRepository<LicenseDetail, Long> {

	@Query(value = "SELECT ld.* FROM license_detail ld  INNER JOIN license_info li ON ld.license_info_id = li.id INNER JOIN license_div_master ldm ON li.license_div_master_id = ldm.id WHERE ldm.license_class_div = :licenseClassDiv AND li.working_operation_div = :workingOperationDiv AND li.contract_number = :contractNumber AND ld.seq_number = :seqNumber;", nativeQuery = true)
	public List<LicenseDetail> findByLicenseClassDivAndWorkingOperationDivAndContractNumberAndSeqNumber(@Param("licenseClassDiv") String licenseClassDiv, @Param("workingOperationDiv") String workingOperationDiv, @Param("contractNumber") String contractNumber, @Param("seqNumber") int seqNumber);
}
