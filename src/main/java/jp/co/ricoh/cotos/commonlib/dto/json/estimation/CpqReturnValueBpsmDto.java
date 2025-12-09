package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.EstimationTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.RtorFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.VendorDiv;
import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値DTO（BPSM）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqReturnValueBpsmDto {

	/**
	 * 見積種別詳細
	 */
	private EstimationTypeDetails estimationTypeDetails;

	/**
	 * ベンダー区分
	 */
	private VendorDiv vendorDiv;

	/**
	 * RtoRフラグ
	 */
	private RtorFlg rtorFlg;

	/**
	 * 減数後台数
	 */
	private Integer subAmount;
}
