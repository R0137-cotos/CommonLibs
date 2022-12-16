package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.OptionRtorDiv;
import lombok.Data;

/**
 * 商品（契約用）拡張項目RtoR固有項目DTO
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractRtorSpecialDto {

	/**
	 * RtoRフラグ (0:通常契約 1:RtoR)
	 */
	private String rtorFlg;

	/**
	 * オプションRtoR区分
	 */
	private OptionRtorDiv optionRtorDiv;

	/**
	 * ホスティングRtoRフラグ (0:ホスティングRtoR無し 1:ホスティングRtoRあり)
	 */
	private String hostingRtorFlg;

}
