package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;
import java.util.Optional;

import jakarta.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.AttachedFileProductGrpCheckMaster;

@Repository
public interface AttachedFileProductGrpCheckMasterRepository extends CrudRepository<AttachedFileProductGrpCheckMaster, Long> {

	@Query(value = ""//
			+ " select *"//
			+ " from attached_file_product_grp_check_master"//
			+ " where"//
			+ "   product_grp_master_id = :PRODUCT_GRP_MASTER_ID"//
			+ "   and domain = :DOMAIN"//
			+ "   and (estimation_contract_type is null or estimation_contract_type = :ESTIMATION_CONTRACT_TYPE)"//
			+ "   and (lifecycle_status is null or lifecycle_status = :LIFECYCLE_STATUS)"//
			+ "   and (item_master_id is null or item_master_id in :ITEM_MASTER_ID_LIST)"//
			, nativeQuery = true)
	public Optional<List<AttachedFileProductGrpCheckMaster>> findAttachedFileProductGrpCheckList(@Param("PRODUCT_GRP_MASTER_ID") @NotNull Long productGrpMasterId, @Param("DOMAIN") @NotNull String domain, @Param("ESTIMATION_CONTRACT_TYPE") String estimationContractType, @Param("LIFECYCLE_STATUS") String lifecycleStatus, @Param("ITEM_MASTER_ID_LIST") List<Long> itemMasterIdList);

	@Query(value = ""//
			+ " select *"//
			+ " from attached_file_product_grp_check_master"//
			+ " where"//
			+ "   product_grp_master_id = :PRODUCT_GRP_MASTER_ID"//
			+ "   and domain = :DOMAIN"//
			+ "   and (estimation_contract_type is null or estimation_contract_type = :ESTIMATION_CONTRACT_TYPE)"//
			+ "   and (lifecycle_status is null or lifecycle_status = :LIFECYCLE_STATUS)"//
			+ "   and (item_master_id is null or item_master_id in :ITEM_MASTER_ID_LIST)"//
			+ "   and (arrangement_work_type_master_id is null or arrangement_work_type_master_id = :ARRANGEMENT_WORK_TYPE_MASTER_ID)"//
			, nativeQuery = true)
	public Optional<List<AttachedFileProductGrpCheckMaster>> findAttachedFileProductGrpCheckListByArrangementWorkTypeMasterId(@Param("PRODUCT_GRP_MASTER_ID") @NotNull Long productGrpMasterId, @Param("DOMAIN") @NotNull String domain, @Param("ESTIMATION_CONTRACT_TYPE") String estimationContractType, @Param("LIFECYCLE_STATUS") String lifecycleStatus, @Param("ITEM_MASTER_ID_LIST") List<Long> itemMasterIdList, @Param("ARRANGEMENT_WORK_TYPE_MASTER_ID") Long arrangementWorkTypeMasterId);

	public List<AttachedFileProductGrpCheckMaster> findByProductGrpMasterIdAndDomain(Long productGrpMasterId, String domain);
}
