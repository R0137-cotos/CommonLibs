package jp.co.ricoh.cotos.commonlib.repository.lisence.cas.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.AbstractTmResponseWork.TmLicenceMappedStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.TmSuspendSubscriptionResponseWork;

@Repository
public interface TmSuspendSubscriptionResponseWorkRepository extends CrudRepository<TmSuspendSubscriptionResponseWork, Long> {

	public List<TmSuspendSubscriptionResponseWork> findByLicenceMappedStatus(TmLicenceMappedStatus licenceMappedStatus);

	public List<TmSuspendSubscriptionResponseWork> findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus licenceMappedStatus, long from, long to);
}