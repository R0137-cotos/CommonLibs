package jp.co.ricoh.cotos.commonlib.repository.license;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseProcess;
import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessPatternMaster.MailDiv;

@Repository
public interface LicenseProcessRepository extends CrudRepository<LicenseProcess, Long> {

	public List<LicenseProcess> findByLicenseInfoIdAndMailDiv(long licenseInfoId, MailDiv mailDiv);
}
