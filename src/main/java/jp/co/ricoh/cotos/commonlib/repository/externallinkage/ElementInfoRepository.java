package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ElementInfo;

@Repository
public interface ElementInfoRepository extends CrudRepository<ElementInfo, Long> {

	public ElementInfo findByContractId(long contractId);
}
