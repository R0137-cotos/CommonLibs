package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.OnSiteOnlyFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSsec.IntActingDivSsec;
import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値DTO（MSS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqReturnValueSsecDto {

	/**
	 * オンサイトのみフラグ
	 */
	private OnSiteOnlyFlg onSiteOnlyFlg;

	/**
	 * 導入代行区分
	 */
	private IntActingDivSsec intActingDiv;
}
