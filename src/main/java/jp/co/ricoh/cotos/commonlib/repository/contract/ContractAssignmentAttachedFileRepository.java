package jp.co.ricoh.cotos.commonlib.repository.contract;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAssignmentAttachedFile;

@Repository
public interface ContractAssignmentAttachedFileRepository extends CrudRepository<ContractAssignmentAttachedFile, Long> {

	@Modifying
	@Query(value = "update contract_assignment_attached_file set contract_assignment_id = -:contractAssignmentId, updated_user_id = :updatedUserId, updated_at = sysdate where id = :id", nativeQuery = true)
	public Integer deleteLogicalContractAssignmentAttachedFile(@Param("id") Long id, @Param("contractAssignmentId") Long contractAssignmentId, @Param("updatedUserId") String updatedUserId);
}
