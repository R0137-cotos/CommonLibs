package jp.co.ricoh.cotos.commonlib.repository.license.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateCustomerResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmResponseWork.TmLicenceMappedStatus;

@Repository
public interface TmUpdateCustomerResponseWorkRepository extends CrudRepository<TmUpdateCustomerResponseWork, Long> {

	public List<TmUpdateCustomerResponseWork> findByLicenceMappedStatus(TmLicenceMappedStatus licenceMappedStatus);

	public List<TmUpdateCustomerResponseWork> findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus licenceMappedStatus, long from, long to);
}
