package jp.co.ricoh.cotos.commonlib.dto.json.master;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 品種ライセンス用設定マスタ拡張項目DTO（RNS）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemLicenseSettingMasterExtendsParameterRnsDto {

	/**
	 * 出力対象区分
	 */
	private String outoutTargetType;

	/**
	 * CSV用ライセンス種別
	 */
	private String csvLicenseType;
}
