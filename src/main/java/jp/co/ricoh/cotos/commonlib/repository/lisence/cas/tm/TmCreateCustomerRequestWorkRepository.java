package jp.co.ricoh.cotos.commonlib.repository.lisence.cas.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.AbstractTmRequestWork.TmRequestStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.TmCreateCustomerRequestWork;

@Repository
public interface TmCreateCustomerRequestWorkRepository extends CrudRepository<TmCreateCustomerRequestWork, Long> {

	public List<TmCreateCustomerRequestWork> findByRequestStatus(TmRequestStatus requestStatus);

	public List<TmCreateCustomerRequestWork> findByRequestStatusAndIdBetween(TmRequestStatus requestStatus, long from, long to);
}
