package jp.co.ricoh.cotos.commonlib.repository.license.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmRequestWork.TmRequestStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmTransitionSubscriptionRequestWork;

@Repository
public interface TmTransitionSubscriptionRequestWorkRepository extends CrudRepository<TmTransitionSubscriptionRequestWork, Long> {

	public List<TmTransitionSubscriptionRequestWork> findByRequestStatus(TmRequestStatus requestStatus);

	public List<TmTransitionSubscriptionRequestWork> findByRequestStatusAndIdBetween(TmRequestStatus requestStatus, long from, long to);

}
