package jp.co.ricoh.cotos.commonlib.repository.license;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail;

@Repository
public interface LicenseDetailRepository extends CrudRepository<LicenseDetail, Long> {

}
