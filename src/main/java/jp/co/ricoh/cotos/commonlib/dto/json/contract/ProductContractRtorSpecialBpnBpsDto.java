package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.HostingRtorFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.OptionRtorDiv;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.RtorFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.RtorMailFlg;
import lombok.Data;

/**
 * 商品（契約用）拡張項目RtoR固有項目DTO（BPN・BPS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractRtorSpecialBpnBpsDto {

	/**
	 * RtoRフラグ
	 */
	private RtorFlg rtorFlg;

	/**
	 * RtoRメールフラグ
	 */
	private RtorMailFlg rtorMailFlg;

	/**
	 * オプションRtoR区分
	 */
	private OptionRtorDiv optionRtorDiv;

	/**
	 * ホスティングRtoRフラグ
	 */
	private HostingRtorFlg hostingRtorFlg;

}
