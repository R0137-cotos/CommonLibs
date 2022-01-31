package jp.co.ricoh.cotos.commonlib.repository.license.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmRequestWork.TmRequestStatus;

@Repository
public interface TmCreateSubscriptionRequestWorkRepository extends CrudRepository<TmCreateSubscriptionRequestWork, Long> {

	public List<TmCreateSubscriptionRequestWork> findByRequestStatus(TmRequestStatus requestStatus);

	public List<TmCreateSubscriptionRequestWork> findByRequestStatusAndIdBetween(TmRequestStatus requestStatus, long from, long to);

}
