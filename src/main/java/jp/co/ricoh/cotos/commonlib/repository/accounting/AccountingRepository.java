package jp.co.ricoh.cotos.commonlib.repository.accounting;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.accounting.Accounting;

@Repository
public interface AccountingRepository extends CrudRepository<Accounting, Long> {

	@Query(value = "select * from Accounting ac "//
			+ "where ac.cost_type = :costType "//
			+ "and ac.ffm_flag = :ffmFlag", nativeQuery = true)
	public List<Accounting> findByCostTypeAndFfmFlag(@Param("costType") String costType, @Param("ffmFlag") int ffmFlag);

	@Modifying
	@Query(value = "update accounting ac set "//
			+ "ac.ffm_flag = 1 "//
			+ "where ac.cost_type = :costType "//
			+ "and ac.ffm_flag = :ffmFlag", nativeQuery = true)
	public int updateFfmFlagByCostTypeAndFfmFlag(@Param("costType") String costType, @Param("ffmFlag") int ffmFlag);

	@Query(value = "select * from accounting ac "//
			+ "where ac.costType = :costType and ac.ffmFlag = :ffmFlag "//
			+ "and ac.product_type_cd in (select ricoh_item_code from item_master where product_master_id in "//
			+ "(select pm.id from product_master pm left join product_comp_master pcm on pm.id = pcm.product_master_id "//
			+ "where product_grp_master_id = :productGrpMasterId))", nativeQuery = true)
	public List<Accounting> findByProductGrpMasterIdAndCostTypeAndFfmFlag(@Param("productGrpMasterId") String productGrpMasterId, @Param("costType") String costType, @Param("ffmFlag") int ffmFlag);

}
