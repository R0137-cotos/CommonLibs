package jp.co.ricoh.cotos.commonlib.repository.license.cas.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.AbstractTmResponseWork.TmLicenceMappedStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.TmCreateCustomerResponseWork;

@Repository
public interface TmCreateCustomerResponseWorkRepository extends CrudRepository<TmCreateCustomerResponseWork, Long> {

	public List<TmCreateCustomerResponseWork> findByLicenceMappedStatus(TmLicenceMappedStatus licenceMappedStatus);

	public List<TmCreateCustomerResponseWork> findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus licenceMappedStatus, long from, long to);
}
