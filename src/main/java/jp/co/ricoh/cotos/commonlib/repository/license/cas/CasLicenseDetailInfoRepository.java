package jp.co.ricoh.cotos.commonlib.repository.license.cas;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.cas.CasLicenseDetailInfo;

@Repository
public interface CasLicenseDetailInfoRepository extends CrudRepository<CasLicenseDetailInfo, Long> {

	public List<CasLicenseDetailInfo> findBySubscriptionId(String subscriptionId);
}
