package jp.co.ricoh.cotos.commonlib.repository.contract;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail;

@Repository
public interface ContractDetailRepository extends CrudRepository<ContractDetail, Long> {

	public List<ContractDetail> findByContractId(long contractId);
}
