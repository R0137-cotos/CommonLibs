package jp.co.ricoh.cotos.commonlib.repository.license.ms;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.ms.MsSubscriptionRegisterRequestWork;

@Repository
public interface MsSubscriptionRegisterRequestWorkRepository extends CrudRepository<MsSubscriptionRegisterRequestWork, Long> {

}
