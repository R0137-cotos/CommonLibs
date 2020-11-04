package jp.co.ricoh.cotos.commonlib.repository.lisence.cas.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.AbstractTmResponseWork.TmLicenceMappedStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.cas.tm.TmUpdateSubscriptionResponseWork;

@Repository
public interface TmUpdateSubscriptionResponseWorkRepository extends CrudRepository<TmUpdateSubscriptionResponseWork, Long> {

	public List<TmUpdateSubscriptionResponseWork> findByLicenceMappedStatus(TmLicenceMappedStatus licenceMappedStatus);

	public List<TmUpdateSubscriptionResponseWork> findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus licenceMappedStatus, long from, long to);
}
