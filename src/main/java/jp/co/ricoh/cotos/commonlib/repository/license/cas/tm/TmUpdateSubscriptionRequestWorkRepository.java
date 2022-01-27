package jp.co.ricoh.cotos.commonlib.repository.license.cas.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmRequestWork.TmRequestStatus;

@Repository
public interface TmUpdateSubscriptionRequestWorkRepository extends CrudRepository<TmUpdateSubscriptionRequestWork, Long> {

	public List<TmUpdateSubscriptionRequestWork> findByRequestStatus(TmRequestStatus requestStatus);

	public List<TmUpdateSubscriptionRequestWork> findByRequestStatusAndIdBetween(TmRequestStatus requestStatus, long from, long to);

}
