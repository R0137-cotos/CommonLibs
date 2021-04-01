package jp.co.ricoh.cotos.commonlib.repository.contract;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAttachedFileHistory;

@Repository
public interface ContractAttachedFileHistoryRepository extends CrudRepository<ContractAttachedFileHistory, Long> {

	@Modifying
	@Query(value = "update contract_attached_file_history set contract_id = :contractId, updated_at = sysdate where id=:id", nativeQuery = true)
	public Integer updateContractAttachedFileHistory(@Param("id") Long id, @Param("contractId") Long contractId);
}
