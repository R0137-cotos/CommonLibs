package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.ArrangementWorkOrderMaster;

@Repository
public interface ArrangementWorkOrderMasterRepository extends CrudRepository<ArrangementWorkOrderMaster, Long> {

	@Query(value = "select * from Arrangement_Work_Order_Master awo "//
			+ "where awo.product_master_id = :productMasterId "//
			+ "and awo.contract_type = :contractType " //
			+ "and awo.disengagement_flg = :disengagementFlg " //
			+ "and awo.arrangement_wk_type_master_id = :arrangementWkTypeMasterId " //
			+ "and awo.check_timing_type = :checkTimingType", nativeQuery = true)
	public List<ArrangementWorkOrderMaster> findByProductMasterIdAndContractTypeAndDisengagementFlgAndArrangementWorkTypeMasterIdAndCheckTimingType(@Param("productMasterId") long productMasterId, @Param("contractType") String contractType, @Param("disengagementFlg") int disengagementFlg, @Param("arrangementWkTypeMasterId") long arrangementWkTypeMasterId, @Param("checkTimingType") String checkTimingType);
}
