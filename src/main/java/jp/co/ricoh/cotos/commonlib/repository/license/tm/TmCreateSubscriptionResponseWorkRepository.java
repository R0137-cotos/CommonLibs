package jp.co.ricoh.cotos.commonlib.repository.license.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateSubscriptionResponseWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmResponseWork.TmLicenceMappedStatus;

@Repository
public interface TmCreateSubscriptionResponseWorkRepository extends CrudRepository<TmCreateSubscriptionResponseWork, Long> {

	public List<TmCreateSubscriptionResponseWork> findByLicenceMappedStatus(TmLicenceMappedStatus licenceMappedStatus);

	public List<TmCreateSubscriptionResponseWork> findByLicenceMappedStatusAndIdBetween(TmLicenceMappedStatus licenceMappedStatus, long from, long to);
}