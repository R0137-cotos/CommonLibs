package jp.co.ricoh.cotos.commonlib.dto.json.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.license.LicenseDetailExtendsParameterGspDto.ReceptionStatusFlg;
import lombok.Data;

/**
 * ライセンス明細拡張項目DTO（MITS）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseDetailExtendsParameterMitsDto {

	/**
	 * 受付状況フラグ
	 */
	private ReceptionStatusFlg receptionStatusFlg;

	/**
	 * 不受理理由
	 */
	private String notAcceptReason;

}
