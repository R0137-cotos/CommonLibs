package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Intra-martのISP設定情報取得API戻り値DTO
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IspSettingDto {

	/**
	 * メールアドレス
	 */
	@JsonProperty("MAIL_ADDRESS")
	private String mailAddress;

	/**
	 * 動的、固定IPの場合のユーザID
	 */
	@JsonProperty("USER_ID")
	private String userId;

	/**
	 * 動的、固定IPの場合のパスワード
	 */
	@JsonProperty("USER_PASSWORD")
	private String userPassword;

	/**
	 * 接続ID
	 */
	@JsonProperty("CONNECT_PREFIX")
	private String connectPrefix;

	/**
	 * 回線種別
	 */
	@JsonProperty("CIRCUIT_TYPE")
	private String circuitType;

	/**
	 * ドメインの場合のドメイン名
	 */
	@JsonProperty("DOMAIN_NAME")
	private String domainName;

	/**
	 * ホスティングの場合のサイト管理ID
	 */
	@JsonProperty("SITE_ID")
	private String siteId;

	/**
	 * ドメインの場合のサブドメイン名１
	 */
	@JsonProperty("SUB_DOMAIN_NAME1")
	private String subDomainName1;

	/**
	 * ドメインの場合のサブドメイン名２
	 */
	@JsonProperty("SUB_DOMAIN_NAME2")
	private String subDomainName2;

	/**
	 * ドメインの場合のサブドメイン名３
	 */
	@JsonProperty("SUB_DOMAIN_NAME3")
	private String subDomainName3;

	/**
	 * ドメインの場合のサブドメイン名４
	 */
	@JsonProperty("SUB_DOMAIN_NAME4")
	private String subDomainName4;
}
