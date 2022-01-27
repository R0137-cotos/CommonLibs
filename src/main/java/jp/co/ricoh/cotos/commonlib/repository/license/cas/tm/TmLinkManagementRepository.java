package jp.co.ricoh.cotos.commonlib.repository.license.cas.tm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateCustomerRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmCreateSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmLinkManagement;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmSuspendSubscriptionRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateCustomerRequestWork;
import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmUpdateSubscriptionRequestWork;

@Repository
public interface TmLinkManagementRepository extends CrudRepository<TmLinkManagement, Long> {
	
	public List<TmLinkManagement> findByIdBetween(long from, long to);

	public List<TmLinkManagement> findByTmCreateCustomerRequestWork(TmCreateCustomerRequestWork tmCreateCustomerRequestWork);

	public List<TmLinkManagement> findByTmCreateSubscriptionRequestWork(TmCreateSubscriptionRequestWork tmCreateSubscriptionRequestWork);

	public List<TmLinkManagement> findByTmUpdateSubscriptionRequestWork(TmUpdateSubscriptionRequestWork tmUpdateSubscriptionRequestWork);

	public List<TmLinkManagement> findByTmSuspendSubscriptionRequestWork(TmSuspendSubscriptionRequestWork tmSuspendSubscriptionRequestWork);
	
	public List<TmLinkManagement> findByTmUpdateCustomerRequestWork(TmUpdateCustomerRequestWork tmUpdateCustomerRequestWork);
}
