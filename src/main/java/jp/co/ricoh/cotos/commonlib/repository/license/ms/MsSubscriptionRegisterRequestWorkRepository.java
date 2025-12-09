package jp.co.ricoh.cotos.commonlib.repository.license.ms;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.ms.MsSubscriptionRegisterRequestWork;

@Repository
public interface MsSubscriptionRegisterRequestWorkRepository extends CrudRepository<MsSubscriptionRegisterRequestWork, Long> {

	public List<MsSubscriptionRegisterRequestWork> findByLicenseInfoId(long licenseInfoId);

	public MsSubscriptionRegisterRequestWork findByCustomerIdAndOfferIdAndLicenseNo(String customerId, String offerId, String licenseNo);
}
