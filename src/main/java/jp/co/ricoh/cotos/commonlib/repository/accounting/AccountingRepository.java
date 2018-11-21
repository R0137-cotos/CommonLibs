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
			+ "and ac.ffm_flg = :ffmFlg", nativeQuery = true)
	public List<Accounting> findByCostTypeAndFfmFlg(@Param("costType") String costType, @Param("ffmFlg") int ffmFlg);

	@Modifying
	@Query(value = "update accounting ac set "//
			+ "ac.ffm_flg = 1 "//
			+ "where ac.ffm_prodact_cd = :ffmProdactCd "//
			+ "and ac.ffm_contract_no = :ffmContractNo", nativeQuery = true)
	public int updateFfmFlgByFfmProdactCdAndFfmContractNo(@Param("ffmProdactCd") String ffmProdactCd, @Param("ffmContractNo") String ffmContractNo);

	@Query(value = "select count(ac.id) from accounting ac where product_type_cd = :PRODUCT_TYPE_CD and billing_date = :BILLING_DATE and exists ( select * from contract co where co.id = ac.contract_id and co.commercial_flow_div in ('2', '3')) order by id asc", nativeQuery = true)
	public long countSubstituteSalesClaim(@Param("PRODUCT_TYPE_CD") String productTypeCd, @Param("BILLING_DATE") String billingDate);

	@Query(value = "select ac.* from accounting ac where rownum >= :ROW_NUM_START and rownum <= :ROW_NUM_END and product_type_cd = :PRODUCT_TYPE_CD and billing_date = :BILLING_DATE and exists ( select * from contract co where co.id = ac.contract_id and co.commercial_flow_div in ('2', '3')) order by id asc", nativeQuery = true)
	public List<Accounting> findSubstituteSalesClaim(@Param("ROW_NUM_START") long rowNumStart, @Param("ROW_NUM_END") long rowNumEnd, @Param("PRODUCT_TYPE_CD") String productTypeCd, @Param("BILLING_DATE") String billingDate);

}
