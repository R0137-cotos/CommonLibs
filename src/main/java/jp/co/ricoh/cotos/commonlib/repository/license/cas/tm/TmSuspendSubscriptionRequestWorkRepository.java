package jp.co.ricoh.cotos.commonlib.repository.license.cas.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmSuspendSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmRequestWork.TmRequestStatus;

@Repository
public interface TmSuspendSubscriptionRequestWorkRepository extends CrudRepository<TmSuspendSubscriptionRequestWork, Long> {

	public List<TmSuspendSubscriptionRequestWork> findByRequestStatus(TmRequestStatus requestStatus);

	public List<TmSuspendSubscriptionRequestWork> findByRequestStatusAndIdBetween(TmRequestStatus requestStatus, long from, long to);

}
