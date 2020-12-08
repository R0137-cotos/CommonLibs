package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.ContractChangeSpanMaster;

@Repository
public interface ContractChangeSpanMasterRepository extends CrudRepository<ContractChangeSpanMaster, Long> {

	@Query(value = ""//
			+ " select m1.*"//
			+ " from contract_change_span_master m1"//
			+ " where (m1.service_category is null or m1.service_category = :service_category)"//
			+ " and   (m1.product_master_id is null or m1.product_master_id = :product_master_id)"//
			+ " and   (m1.contract_type is null or m1.contract_type = :contract_type)"//
			+ " and   (m1.contract_type_detail is null or m1.contract_type_detail = :contract_type_detail)"//
			+ " and   (m1.lifecycle_status is null or m1.lifecycle_status = :lifecycle_status)"//
			+ " and   (m1.workflow_status is null or m1.workflow_status = :workflow_status)", nativeQuery = true)
	public List<ContractChangeSpanMaster> findContractChangeSpanMasterList(@Param("service_category") String serviceCategory,
			@Param("product_master_id") Long productMasterId, @Param("contract_type") String contractType, @Param("contract_type_detail") String contractTypeDetail,
			@Param("lifecycle_status") String lifecycleStatus, @Param("workflow_status") String workflowStatus);
}
