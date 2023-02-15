package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.EstimationTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.RtorFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.RtorMailFlg;
import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値DTO（BPSS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqReturnValueBpssDto {

	/**
	 * 見積種別詳細
	 */
	private EstimationTypeDetails estimationTypeDetails;

	/**
	 * メールアドレスRtoRフラグ
	 */
	private RtorMailFlg rtorMailFlg;

	/**
	 * RtoRフラグ
	 */
	private RtorFlg rtorFlg;
}
