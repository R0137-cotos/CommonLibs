package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.EstimationTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.MailRtorFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.RtorFlg;
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
	private MailRtorFlg mailRtorFlg;

	/**
	 * RtoRフラグ
	 */
	private RtorFlg rtorFlg;
}
