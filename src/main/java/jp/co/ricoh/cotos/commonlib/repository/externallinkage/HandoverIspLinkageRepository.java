package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.HandoverIspLinkage;

@Repository
public interface HandoverIspLinkageRepository extends CrudRepository<HandoverIspLinkage, Long> {

	public HandoverIspLinkage findByContractId(long contractId);
}
