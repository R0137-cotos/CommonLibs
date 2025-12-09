package jp.co.ricoh.cotos.commonlib.dto.json.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeO365.OrderDiv;
import lombok.Data;

/**
 * ライセンス情報拡張項目DTO（O365）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseInfoExtendsParameterO365Dto {

	/**
	 * アドオン有無フラグ
	 */
	private String addOnFlg;

	/**
	 * オンサイト有無フラグ
	 */
	private String onsiteFlg;

	/**
	 * ユーザー登録有無フラグ
	 */
	private String userRegisterFlg;

	/**
	 * 独自ドメイン有無フラグ
	 */
	private String originalDomainFlg;

	/**
	 * 受注区分
	 */
	private OrderDiv orderDiv;

}
