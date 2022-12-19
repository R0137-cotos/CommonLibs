package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.RtorFlg;
import lombok.Data;

/**
 * 商品（契約用）拡張項目RtoR固有項目DTO（BPSS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractRtorSpecialBpssDto {

	/**
	 * RtoRフラグ
	 */
	private RtorFlg rtorFlg;

}
