package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeO365.TransferFlg;
import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値DTO（O365）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqReturnValueO365Dto {

	/**
	 * 転入フラグ
	 */
	private TransferFlg transferFlg;
}
