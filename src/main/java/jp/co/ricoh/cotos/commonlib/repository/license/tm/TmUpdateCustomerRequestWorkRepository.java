package jp.co.ricoh.cotos.commonlib.repository.license.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateCustomerRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmRequestWork.TmRequestStatus;

@Repository
public interface TmUpdateCustomerRequestWorkRepository extends CrudRepository<TmUpdateCustomerRequestWork, Long> {

	public List<TmUpdateCustomerRequestWork> findByRequestStatus(TmRequestStatus requestStatus);

	public List<TmUpdateCustomerRequestWork> findByRequestStatusAndIdBetween(TmRequestStatus requestStatus, long from, long to);

}
