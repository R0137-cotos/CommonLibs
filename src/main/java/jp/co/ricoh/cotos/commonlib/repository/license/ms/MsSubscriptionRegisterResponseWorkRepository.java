package jp.co.ricoh.cotos.commonlib.repository.license.ms;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.ms.AbstractMsResponseWork.MsResponseMappedStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.ms.MsSubscriptionRegisterResponseWork;

@Repository
public interface MsSubscriptionRegisterResponseWorkRepository extends CrudRepository<MsSubscriptionRegisterResponseWork, Long> {

	public List<MsSubscriptionRegisterResponseWork> findByProcessStatus(MsResponseMappedStatus processStatus);

	public MsSubscriptionRegisterResponseWork findByLicenseNo(String licenseNo);

}
