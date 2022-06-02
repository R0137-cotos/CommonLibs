package jp.co.ricoh.cotos.commonlib.repository.contract.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.order.OrderBasicInfo;

@Repository
public interface OrderBasicInfoRepository extends JpaRepository<OrderBasicInfo, Long> {

	/**
	 * 注文番号をキーにエラー終了していない注文基本情報を取得する
	 * @param orderNumber 注文番号
	 * @return 注文基本情報リスト
	 */
	@Query(value = "SELECT obi.* FROM "
			+ "ORDER_BASIC_INFO obi "
			+ "INNER JOIN ORDER_MANAGEMENT_INFO omi ON obi.id = omi.order_basic_info_id "
			+ "WHERE omi.contract_capture_status not in ('E') "
			+ "AND obi.orderer_number = :ordererNumber ", nativeQuery = true)
	public List<OrderBasicInfo> findByNoErrorAndOrderNumber(@Param("ordererNumber") String ordererNumber);

	/**
	 * [o365]希望サブドメインをキーにエラー終了、契約破棄していない新規注文基本情報を取得する
	 * @param subDomain 希望サブドメイン
	 * @return 注文基本情報リスト
	 */
	@Query(value = "SELECT obi.* FROM "
			+ "ORDER_BASIC_INFO obi "
			+ "INNER JOIN ORDER_MANAGEMENT_INFO omi ON obi.id = omi.order_basic_info_id "
			+ "INNER JOIN ORDER_SERVICE_INNER_INFO osii ON obi.id = osii.order_basic_info_id "
			+ "LEFT JOIN CONTRACT c ON omi.CONTRACT_ID = c.ID "
			+ "WHERE obi.orderer_type = '1' "
			+ "AND obi.product_type = '3' "
			+ "AND omi.contract_capture_status not in ('E') "
			+ "AND osii.item4 = :subDomain "
			+ "AND (c.lifecycle_status IS null OR c.lifecycle_status <> '4') ", nativeQuery = true)
	public List<OrderBasicInfo> findByNoErrorAndSubDomainForO365(@Param("subDomain") String subDomain);

	/**
	 * [o365]キャンセル用IDをキーにキャンセル注文基本情報を取得する
	 * @param cancelContractId キャンセル用ID
	 * @return 注文基本情報リスト
	 */
	@Query(value = "SELECT obi.* FROM "
			+ "ORDER_BASIC_INFO obi "
			+ "INNER JOIN ORDER_SERVICE_INNER_INFO osii ON obi.id = osii.order_basic_info_id "
			+ "WHERE obi.orderer_type = '4' "
			+ "AND obi.product_type = '3' "
			+ "AND osii.item5 = :cancelContractId ", nativeQuery = true)
	public List<OrderBasicInfo> findByCancelContractIdForO365(@Param("cancelContractId") String cancelContractId);

}