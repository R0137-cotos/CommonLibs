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
			+ " select m1.*"//
			+ " from attached_file_product_class_check_master m1"//
			+ "   inner join ("//
			+ "     select * from attached_file_product_class_check_master"//
			+ "       where (estimation_contract_type = :ESTIMATION_CONTRACT_TYPE and lifecycle_status = :LIFECYCLE_STATUS)"//
			+ "       or    (estimation_contract_type = :ESTIMATION_CONTRACT_TYPE and lifecycle_status is null)"//
			+ "       or    (estimation_contract_type is null and lifecycle_status = :LIFECYCLE_STATUS)"//
			+ "       or    (estimation_contract_type is null and lifecycle_status is null)) m2"//
			+ "   on m2.id = m1.id"//
			+ " where m1.product_class_div = :PRODUCT_CLASS_DIV"//
			+ " and   m1.domain = :DOMAIN", nativeQuery = true)
	public Optional<List<AttachedFileProductClassCheckMaster>> findAttachedFileProductClassCheckList(@Param("PRODUCT_CLASS_DIV") @NotNull String productClassDiv, @Param("DOMAIN") @NotNull String domain, @Param("ESTIMATION_CONTRACT_TYPE") String estimationContractType, @Param("LIFECYCLE_STATUS") String lifecycleStatus);

}
