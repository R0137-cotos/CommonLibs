package jp.co.ricoh.cotos.commonlib.repository.license.ms;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.license.ms.MsCustomerRegisterRequestWork;

@Repository
public interface MsCustomerRegisterRequestWorkRepository extends CrudRepository<MsCustomerRegisterRequestWork, Long> {

	public MsCustomerRegisterRequestWork findByContractId(long contractId);
}
