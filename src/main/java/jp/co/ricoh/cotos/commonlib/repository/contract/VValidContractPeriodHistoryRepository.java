package jp.co.ricoh.cotos.commonlib.repository.contract;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.VValidContractPeriodHistory;

@Repository
public interface VValidContractPeriodHistoryRepository extends JpaRepository<VValidContractPeriodHistory, Long> {

	/**
	 * OSO連携データ ランニング分実績 抽出条件
	 * @param productClassDiv 商品種類区分
	 * @param date 対象日
	 * @return 有効契約のリスト
	 */
	@Query(value = "select * from v_valid_contract_period_history " //
			+ "where product_class_div in :productClassDiv " //
			+ "and :date between contract_date_start and contract_date_end", nativeQuery = true)
	public List<VValidContractPeriodHistory> findByProductClassDivInAndDate(@Param("productClassDiv") List<String> productClassDiv, @Param("date") Date date);

	/**
	 * 従量超過分使用量取込API 抽出条件
	 * @param rjManageNumber RJ管理番号
	 * @param date 対象日
	 * @return 有効契約のリスト
	 */
	@Query(value = "select * from v_valid_contract_period_history " //
			+ "where rj_manage_number in :rjManageNumber " //
			+ "and :date between contract_date_start and contract_date_end ", nativeQuery = true)
	public List<VValidContractPeriodHistory> findByRjManageNumberInAndDate(@Param("rjManageNumber") List<String> rjManageNumber, @Param("date") Date date);
}
