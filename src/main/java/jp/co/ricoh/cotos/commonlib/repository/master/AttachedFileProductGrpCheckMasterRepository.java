package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.AttachedFileProductGrpCheckMaster;

@Repository
public interface AttachedFileProductGrpCheckMasterRepository extends CrudRepository<AttachedFileProductGrpCheckMaster, Long> {

	@Query(value = ""//
			+ " select m1.*"
			+ " from attached_file_product_grp_check_master m1"//
			+ "   inner join ("//
			+ "     select * from attached_file_product_grp_check_master"//
			+ "       where (estimation_contract_type = :ESTIMATION_CONTRACT_TYPE and lifecycle_status = :LIFECYCLE_STATUS)"//
			+ "       or    (estimation_contract_type = :ESTIMATION_CONTRACT_TYPE and lifecycle_status is null)"//
			+ "       or    (estimation_contract_type is null and lifecycle_status = :LIFECYCLE_STATUS)"//
			+ "       or    (estimation_contract_type is null and lifecycle_status is null)) m2"//
			+ "   on m2.id = m1.id"//
			+ " where m1.product_grp_master_id = :PRODUCT_GRP_MASTER_ID"//
			+ " and   m1.domain = :DOMAIN", nativeQuery = true)
	public Optional<List<AttachedFileProductGrpCheckMaster>> findAttachedFileProductGrpCheckList(@Param("PRODUCT_GRP_MASTER_ID") @NotNull Long productGrpMasterId, @Param("DOMAIN") @NotNull String domain, @Param("ESTIMATION_CONTRACT_TYPE") String estimationContractType, @Param("LIFECYCLE_STATUS") String lifecycleStatus);

}
