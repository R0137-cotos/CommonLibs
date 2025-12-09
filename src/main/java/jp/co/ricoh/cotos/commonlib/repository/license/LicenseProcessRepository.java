package jp.co.ricoh.cotos.commonlib.repository.license;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseProcess;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseProcess.ProcessStatus;
import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessMaster.OperationDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessPatternMaster.MailDiv;

@Repository
public interface LicenseProcessRepository extends CrudRepository<LicenseProcess, Long> {

	public List<LicenseProcess> findByLicenseInfoIdAndMailDiv(long licenseInfoId, MailDiv mailDiv);

	public List<LicenseProcess> findByArrangementWorkIdInAndProcessStatus(List<Long> arrangementWorkId, ProcessStatus processStatus);

	public List<LicenseProcess> findByLicenseInfoIdAndProcessMasterId(long licenseInfoId, long processMasterId);

	public List<LicenseProcess> findByLicenseInfoIdAndProcessStatus(long licenseInfoId, ProcessStatus processStatus);

	public List<LicenseProcess> findByLicenseInfoIdAndArrangementWorkIdAndProcessStatus(long licenseInfoId, long arrangementWorkId, ProcessStatus processStatus);

	public LicenseProcess findByLicenseInfoIdAndProcessOrderAndProcessMasterIdAndArrangementWorkIdAndOperationDivAndProcessStatus(long licenseInfoId, int processOrder, long processMasterId, long arrangementWorkId, OperationDiv operationDiv, ProcessStatus processStatus);
}