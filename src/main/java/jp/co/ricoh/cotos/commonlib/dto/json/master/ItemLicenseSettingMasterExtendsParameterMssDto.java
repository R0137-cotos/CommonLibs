package jp.co.ricoh.cotos.commonlib.dto.json.master;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 品種ライセンス用設定マスタ拡張項目DTO（MSS）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemLicenseSettingMasterExtendsParameterMssDto {

	/**
	 * 出力対象区分
	 */
	private String outoutTargetType;

	/**
	 * CSV用ライセンス種別
	 */
	private String csvLicenseType;
}
