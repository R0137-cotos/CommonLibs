package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（契約用）拡張項目RtoR固有項目DTO
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractRtoRSpecialDto {

	/**
	 * RtoRフラグ
	 */
	private String rtorFlg;

	/**
	 * オプションRtoR区分
	 */
	private String optionRtoRDiv;

	/**
	 * ホスティングRtoRフラグ
	 */
	private String hostingRtoRFlg;

}
