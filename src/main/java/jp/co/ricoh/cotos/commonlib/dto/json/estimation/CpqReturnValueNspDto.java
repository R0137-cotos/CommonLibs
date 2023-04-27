package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.EstimationTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.OtherCorpProviderFlg;
import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値DTO（NSP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqReturnValueNspDto {

	/**
	 * 見積種別詳細
	 */
	private EstimationTypeDetails estimationTypeDetails;

	/**
	 * スポーク拠点数
	 */
	private Integer spokeNumber;

	/**
	 * 他社プロバイダーフラグ
	 */
	private OtherCorpProviderFlg otherCorpProviderFlg;
}
