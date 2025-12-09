package jp.co.ricoh.cotos.commonlib.dto.json.master;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 品種ライセンス用設定マスタ拡張項目DTO（GSP）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemLicenseSettingMasterExtendsParameterGspDto {

	/**
	 * 商品区分
	 */
	private String productType;

	/**
	 * サービス種別
	 */
	private String serviceType;

	/**
	 * SCSK対象フラグ
	 */
	private String scskFlg;
}
