package jp.co.ricoh.cotos.commonlib.repository.contract;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAttachedFile;

@Repository
public interface ContractAttachedFileRepository extends CrudRepository<ContractAttachedFile, Long> {
	public List<ContractAttachedFile> findByContractId(Long contractId);

	@Modifying
	@Query(value = "update contract_attached_file set contract_id = -:contractId, updated_user_id = :updatedUserId, updated_at = sysdate where id = :id", nativeQuery = true)
	public Integer deleteLogicalContractAttachedFile(@Param("id") Long id, @Param("contractId") Long contractId, @Param("updatedUserId") String updatedUserId);
}
