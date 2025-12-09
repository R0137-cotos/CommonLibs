package jp.co.ricoh.cotos.commonlib.dto.json.master;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 品種ライセンス用設定マスタ拡張項目DTO（BPSM）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemLicenseSettingMasterExtendsParameterBpsmDto {

	/**
	 * 端末種別
	 */
	private String terminalType;

	/**
	 * サービスコード(KD)
	 */
	private String serviceCdKd;
}
