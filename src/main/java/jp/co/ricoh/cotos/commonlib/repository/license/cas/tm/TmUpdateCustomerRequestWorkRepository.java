package jp.co.ricoh.cotos.commonlib.repository.license.cas.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.AbstractTmRequestWork.TmRequestStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.TmUpdateCustomerRequestWork;

@Repository
public interface TmUpdateCustomerRequestWorkRepository extends CrudRepository<TmUpdateCustomerRequestWork, Long> {

	public List<TmUpdateCustomerRequestWork> findByRequestStatus(TmRequestStatus requestStatus);

	public List<TmUpdateCustomerRequestWork> findByRequestStatusAndIdBetween(TmRequestStatus requestStatus, long from, long to);

}
