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

}
