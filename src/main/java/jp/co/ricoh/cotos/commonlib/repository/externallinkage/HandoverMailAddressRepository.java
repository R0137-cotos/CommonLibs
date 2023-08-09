package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.HandoverMailAddress;

@Repository
public interface HandoverMailAddressRepository extends CrudRepository<HandoverMailAddress, Long> {

	public List<HandoverMailAddress> findByContractId(long contractId);
}
