package jp.co.ricoh.cotos.commonlib.repository.contract;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long> {

	public Contract findByIdAndAppIdIn(Long id, List<String> appId);

	public Contract findByIdAndAppIdNotIn(Long id, List<String> appId);

	public List<Contract> findByContractNumber(String contractNumber);

	public List<Contract> findByContractNumberAndContractBranchNumber(String contractNumber, int contractBranchNumber);

	public List<Contract> findByOriginContractId(Long originContractId);

	@Query(value = "SELECT * FROM CONTRACT WHERE LIFECYCLE_STATUS = '8' AND CANCEL_SCHEDULED_DATE < :opDate", nativeQuery = true)
	public List<Contract> findByLifecycleAndCancelScheduledDate(@Param("opDate") String opDate);

	public List<Contract> findByRjManageNumber(String rjManageNumber);

	public List<Contract> findByWebOrderNumber(String webOrderNumber);

	public List<Contract> findByEstimationIdOrderByContractBranchNumberAsc(long estimationId);

	@Query(value = "SELECT * FROM CONTRACT WHERE CONTRACT_TYPE = '3' AND LIFECYCLE_STATUS = '5' AND CHANGE_PREFERRED_DATE <= :changePreferredDate", nativeQuery = true)
	public List<Contract> findByContractTypeAndChangePreferredDate(@Param("changePreferredDate") Date changePreferredDate);

	@Query(value = "SELECT * FROM CONTRACT WHERE (CONTRACT_TYPE = '3' AND LIFECYCLE_STATUS = '5' AND CHANGE_PREFERRED_DATE <= :preferredDate) OR ((CONTRACT_TYPE = '1' OR CONTRACT_TYPE = '2') AND LIFECYCLE_STATUS = '11' AND CONCLUSION_PREFERRED_DATE <= :preferredDate)", nativeQuery = true)
	public List<Contract> findByContractTypeAndPreferredDate(@Param("preferredDate") Date preferredDate);

	
	/**
	 * 自動更新対象の契約情報リストを取得(通常商品のみ)
	 *
	 * ■条件①：
	 *   「契約.ﾗｲﾌｻｲｸﾙ状態」 ＝ 「締結中」
	 * ■条件②：
	 *   条件①の契約に対する 「締結待ち」「予定日待ち」 の契約が存在しないこと
	 * ■条件③：
	 *   「契約自動更新マスタ.契約更新方式区分」 ＝ 「自動更新」 の契約明細（＝品種コード）を含む契約であること
	 * ■条件④：
	 *   「契約.サービス終了日」 ＜ 起動引数 「処理年月日」
	 * ■条件⑤：
	 *   契約自動更新マスタ.商品更新区分=「未設定:通常商品」の品種が含まれる契約
	 * @param preferredDate 実行日時
	 * @return List<Contract> 契約情報リスト
	 */
	@Query(value = "SELECT c.* "
			+ "FROM CONTRACT c, "
			+ "  CONTRACT_DETAIL d, "
			+ "  ITEM_CONTRACT e, "
			+ "  CONTRACT_AUTO_UPDATE_MASTER a "
			+ "WHERE c.LIFECYCLE_STATUS = '6'"
			+ "  AND c.id = d.contract_id"
			+ "  AND d.id = e.contract_detail_id"
			+ "  AND e.item_master_id = a.item_master_id"
			+ "  AND a.contract_update_type = '1'"
			+ "  AND a.product_update_type IS NULL"
			+ "  AND c.IMMUTABLE_CONT_IDENT_NUMBER NOT IN ("
			+ "    SELECT ct.IMMUTABLE_CONT_IDENT_NUMBER"
			+ "    FROM CONTRACT ct"
			+ "    WHERE (ct.LIFECYCLE_STATUS = '5' OR ct.LIFECYCLE_STATUS = '11'))"
			+ "  AND c.SERVICE_TERM_END < :preferredDate",
			nativeQuery = true)
	public List<Contract> findByAutoUpdaterecord(@Param("preferredDate") Date preferredDate);

	@Query(value = "SELECT * FROM CONTRACT WHERE WORKFLOW_STATUS = '7' AND LIFECYCLE_STATUS = '2' AND CONTRACT_TYPE IN ('1', '2') AND PRODUCT_GRP_MASTER_ID IN (:productGrpMasterIdList)", nativeQuery = true)
	public List<Contract> findByProductGrpMasterId(@Param("productGrpMasterIdList") List<Long> productGrpMasterIdList);

}
