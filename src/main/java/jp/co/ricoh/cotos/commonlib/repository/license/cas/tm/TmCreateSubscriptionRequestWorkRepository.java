package jp.co.ricoh.cotos.commonlib.repository.license.cas.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.AbstractTmRequestWork.TmRequestStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.TmCreateSubscriptionRequestWork;

@Repository
public interface TmCreateSubscriptionRequestWorkRepository extends CrudRepository<TmCreateSubscriptionRequestWork, Long> {

	public List<TmCreateSubscriptionRequestWork> findByRequestStatus(TmRequestStatus requestStatus);

	public List<TmCreateSubscriptionRequestWork> findByRequestStatusAndIdBetween(TmRequestStatus requestStatus, long from, long to);

}
