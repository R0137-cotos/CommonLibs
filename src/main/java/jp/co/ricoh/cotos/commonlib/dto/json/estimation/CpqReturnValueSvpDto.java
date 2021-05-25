package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.EstimationTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.BillingTypeSvp;
import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値DTO（SVP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqReturnValueSvpDto {

	/**
	 * 見積種別詳細
	 */
	private EstimationTypeDetails estimationTypeDetails;

	/**
	 * 請求方法区分
	 */
	private BillingTypeSvp billingType;

	/**
	 * 監視運用オプション品種コード
	 */
	private String monitoringOpeItemCode;

	/**
	 * 監視運用オプション導入希望月
	 */
	private String monitoringOpeIntPreferredMonth;

	/**
	 * 見積機種リスト
	 */
	@JsonProperty("estimationEquipment")
	private List<CpqEstimationEquipmentSvpDto> productEstimationCpqEstimationEquipmentSvpDtoList;
}
