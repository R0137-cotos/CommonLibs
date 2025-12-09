package jp.co.ricoh.cotos.commonlib.repository.contract;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractCheckResult;

@Repository
public interface ContractCheckResultRepository extends CrudRepository<ContractCheckResult, Long> {

	List<ContractCheckResult> findByContractIdAndCheckMatterCode(Long contractId, String checkMatterCode);
}
