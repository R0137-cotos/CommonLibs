package jp.co.ricoh.cotos.commonlib.repository.license.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateCustomerResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmResponseWork.TmLicenceMappedStatus;

@Repository
public interface TmCreateCustomerResponseWorkRepository extends CrudRepository<TmCreateCustomerResponseWork, Long> {

	public List<TmCreateCustomerResponseWork> findByLicenceMappedStatus(TmLicenceMappedStatus licenceMappedStatus);

	public List<TmCreateCustomerResponseWork> findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus licenceMappedStatus, long from, long to);
}
