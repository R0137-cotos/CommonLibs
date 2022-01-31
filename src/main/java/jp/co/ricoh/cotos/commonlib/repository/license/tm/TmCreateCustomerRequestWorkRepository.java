package jp.co.ricoh.cotos.commonlib.repository.license.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateCustomerRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmRequestWork.TmRequestStatus;

@Repository
public interface TmCreateCustomerRequestWorkRepository extends CrudRepository<TmCreateCustomerRequestWork, Long> {

	public List<TmCreateCustomerRequestWork> findByRequestStatus(TmRequestStatus requestStatus);

	public List<TmCreateCustomerRequestWork> findByRequestStatusAndIdBetween(TmRequestStatus requestStatus, long from, long to);
}
