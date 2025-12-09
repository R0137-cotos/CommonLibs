package jp.co.ricoh.cotos.commonlib.dto.json.master;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 品種ライセンス用設定マスタ拡張項目DTO（CLE）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemLicenseSettingMasterExtendsParameterCleDto {

	/**
	 * ライセンスサービス名
	 */
	private String licenseServiceName;

	/**
	 * 品種名
	 */
	private String itemName;
}
