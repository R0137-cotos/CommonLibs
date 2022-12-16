package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRtor.HostingRtorFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRtor.OptionRtorDiv;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRtor.RtorFlg;
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
	private RtorFlg rtorFlg;

	/**
	 * オプションRtoR区分
	 */
	private OptionRtorDiv optionRtorDiv;

	/**
	 * ホスティングRtoRフラグ
	 */
	private HostingRtorFlg hostingRtorFlg;

}
