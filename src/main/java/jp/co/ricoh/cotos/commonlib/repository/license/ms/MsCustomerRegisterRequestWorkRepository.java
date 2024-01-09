package jp.co.ricoh.cotos.commonlib.repository.license.ms;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.ms.MsCustomerRegisterRequestWork;

@Repository
public interface MsCustomerRegisterRequestWorkRepository extends CrudRepository<MsCustomerRegisterRequestWork, Long> {
	public MsCustomerRegisterRequestWork findByContractId(long contractId);

	public MsCustomerRegisterRequestWork findByLicenseInfoId(long licenseInfoId);

	@Query(value = "SELECT * FROM MS_CUSTOMER_REGISTER_REQUEST_WORK WHERE CUSTOMER_LINKAGE_STATUS IN :CUSTOMER_LINKAGE_STATUS", nativeQuery = true)
	public List<MsCustomerRegisterRequestWork> findByCustomerLinkageStatus(@Param("CUSTOMER_LINKAGE_STATUS") List<String> customerLinkageStatus);

}
