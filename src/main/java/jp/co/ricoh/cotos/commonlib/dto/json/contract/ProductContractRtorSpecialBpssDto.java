package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.RtorFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.RtorMailFlg;
import lombok.Data;

/**
 * 商品（契約用）拡張項目RtoR固有項目DTO（BPSS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractRtorSpecialBpssDto {

	/**
	 * RtoRメールフラグ
	 */
	private RtorMailFlg rtorMailFlg;

	/**
	 * RtoRフラグ
	 */
	private RtorFlg rtorFlg;
}
