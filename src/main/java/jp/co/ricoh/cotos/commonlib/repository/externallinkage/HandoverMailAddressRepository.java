package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.HandoverMailAddress;

@Repository
public interface HandoverMailAddressRepository extends CrudRepository<HandoverMailAddress, Long> {

	public HandoverMailAddress findByContractId(long contractId);
}
