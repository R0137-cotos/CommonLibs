package jp.co.ricoh.cotos.commonlib.repository.license.tm;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.tm.TmTransitionSubscriptionRequestWork;

@Repository
public interface TmTransitionSubscriptionRequestWorkRepository extends CrudRepository<TmTransitionSubscriptionRequestWork, Long> {

}
