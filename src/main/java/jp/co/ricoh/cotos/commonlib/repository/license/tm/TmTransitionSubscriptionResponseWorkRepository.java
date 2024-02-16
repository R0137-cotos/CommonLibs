package jp.co.ricoh.cotos.commonlib.repository.license.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmResponseWork.TmLicenceMappedStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmTransitionSubscriptionResponseWork;

@Repository
public interface TmTransitionSubscriptionResponseWorkRepository extends CrudRepository<TmTransitionSubscriptionResponseWork, Long> {

	public List<TmTransitionSubscriptionResponseWork> findByLicenceMappedStatus(TmLicenceMappedStatus licenceMappedStatus);

	public List<TmTransitionSubscriptionResponseWork> findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus licenceMappedStatus, long from, long to);
}
