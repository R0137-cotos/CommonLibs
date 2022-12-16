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
	 * RtoRフラグ
	 */
	private String rtorFlg;

	/**
	 * オプションRtoR区分
	 */
	private OptionRtorDiv optionRtorDiv;

	/**
	 * ホスティングRtoRフラグ
	 */
	private String hostingRtorFlg;

}
