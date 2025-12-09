package jp.co.ricoh.cotos.commonlib.repository.contract;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAssignment;

@Repository
public interface ContractAssignmentRepository extends CrudRepository<ContractAssignment, Long> {
	public ContractAssignment findByContractId(Long contractId);
}
