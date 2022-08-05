package jp.co.ricoh.cotos.commonlib.repository.license.ms;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.ms.AbstractMsResponseWork.MsResponseMappedStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.ms.MsCustomerRegisterResponseWork;

@Repository
public interface MsCustomerRegisterResponseWorkRepository extends CrudRepository<MsCustomerRegisterResponseWork, Long> {

	public List<MsCustomerRegisterResponseWork> findByProcessStatus(MsResponseMappedStatus processStatus);

	public MsCustomerRegisterResponseWork findByContractId(long contractId);

}
