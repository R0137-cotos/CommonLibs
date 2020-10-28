package jp.co.ricoh.cotos.commonlib.repository.lisence.cas;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.cas.CasLicenseDetailInfo;

@Repository
public interface CasLicenseDetailInfoRepository extends CrudRepository<CasLicenseDetailInfo, Long> {

}
