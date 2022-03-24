package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.AttachedFileProductClassCheckMaster;

@Repository
public interface AttachedFileProductClassCheckMasterRepository extends CrudRepository<AttachedFileProductClassCheckMaster, Long> {

	@Query(value = ""//
			+ " select *"//
			+ " from attached_file_product_class_check_master"//
			+ " where"//
			+ "   product_class_div = :PRODUCT_CLASS_DIV"//
			+ "   and domain = :DOMAIN"//
			+ "   and (estimation_contract_type is null or estimation_contract_type = :ESTIMATION_CONTRACT_TYPE)"//
			+ "   and (lifecycle_status is null or lifecycle_status = :LIFECYCLE_STATUS)"//
			, nativeQuery = true)
	public Optional<List<AttachedFileProductClassCheckMaster>> findAttachedFileProductClassCheckList(@Param("PRODUCT_CLASS_DIV") @NotNull String productClassDiv, @Param("DOMAIN") @NotNull String domain, @Param("ESTIMATION_CONTRACT_TYPE") String estimationContractType, @Param("LIFECYCLE_STATUS") String lifecycleStatus);

	@Query(value = ""//
			+ " select *"//
			+ " from attached_file_product_class_check_master"//
			+ " where"//
			+ "   product_class_div = :PRODUCT_CLASS_DIV"//
			+ "   and domain = :DOMAIN"//
			+ "   and (estimation_contract_type is null or estimation_contract_type = :ESTIMATION_CONTRACT_TYPE)"//
			+ "   and (lifecycle_status is null or lifecycle_status = :LIFECYCLE_STATUS)"//
			+ "   and (arrangement_work_type_master_id is null or arrangement_work_type_master_id = :ARRANGEMENT_WORK_TYPE_MASTER_ID)"//
			, nativeQuery = true)
	public Optional<List<AttachedFileProductClassCheckMaster>> findAttachedFileProductClassCheckListByArrangementWorkTypeMasterId(@Param("PRODUCT_CLASS_DIV") @NotNull String productClassDiv, @Param("DOMAIN") @NotNull String domain, @Param("ESTIMATION_CONTRACT_TYPE") String estimationContractType, @Param("LIFECYCLE_STATUS") String lifecycleStatus, @Param("ARRANGEMENT_WORK_TYPE_MASTER_ID") Long arrangementWorkTypeMasterId);

	public List<AttachedFileProductClassCheckMaster> findByProductClassDivAndDomain(String productClassDiv, String domain);
}
