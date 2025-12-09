package jp.co.ricoh.cotos.commonlib.repository.license.cas;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.cas.CasLicenseManagementInfo;

@Repository
public interface CasLicenseManagementInfoRepository extends CrudRepository<CasLicenseManagementInfo, Long> {
	public List<CasLicenseManagementInfo> findByContractId(long contractId);
}
