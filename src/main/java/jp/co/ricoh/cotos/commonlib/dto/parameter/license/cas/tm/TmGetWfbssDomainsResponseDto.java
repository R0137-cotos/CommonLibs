package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロWfbssドメイン取得レスポンスDTO
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public class TmGetWfbssDomainsResponseDto extends AbstractTmResponseDto {

	private TmGetSubscriptionResponseDtoCustomers[] customers;

	@Data
	public static class TmGetSubscriptionResponseDtoCustomers {

		/**
		 * ID(String型であることに注意)
		 */
		private String id;

		/**
		 * eID
		 */
		private String eid;

		/**
		 * マジックリンク(インストール用URL)
		 */
		@JsonProperty("magic_link")
		private String magicLink;

		/**
		 * サーバーロケーション
		 */
		@JsonProperty("server_location")
		private String serverLocation;

		/**
		 * サービス名称
		 */
		@JsonProperty("service_name")
		private String serviceName;

		/**
		 * ドメインID
		 */
		@JsonProperty("domain_id")
		private Integer domainId;

		/**
		 * エージェントモードID
		 */
		@JsonProperty("agent_mode_id")
		private Integer agentModeId;

		/**
		 * colo
		 */
		private String colo;

		/**
		 * 会社キー
		 */
		@JsonProperty("company_key")
		private String companyKey;

		/**
		 * サービスID
		 */
		@JsonProperty("service_id")
		private Integer serviceId;

		/**
		 * アクティベーションコード
		 */
		@JsonProperty("activation_code")
		private String activationCode;
	}

}
