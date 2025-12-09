package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 引継ぎ用ISP連携を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "handover_isp_linkage")
public class HandoverIspLinkage extends EntityBase {

	@Description(value = "ホスティングサービス区分")
	public enum HostingServiceDiv {

		現状のホスティングサーバーを継続利用("1"), 新規ホスティングサーバーにドメイン名だけ引き継ぐ("2");

		private final String text;

		private HostingServiceDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static HostingServiceDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 引継ぎ用ISP連携ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "handover_isp_linkage_seq")
	@SequenceGenerator(name = "handover_isp_linkage_seq", sequenceName = "handover_isp_linkage_seq", allocationSize = 1)
	@Schema(description = "引継ぎ用ISP連携ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Min(0)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * メールアカウント
	 */
	@Size(max = 255)
	@Schema(description = "メールアカウント", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String mailAccount;

	/**
	 * メール引継ぎ元契約ID
	 */
	@Min(0)
	@Schema(description = "メール引継ぎ元契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long mailHandoverContractId;

	/**
	 * ドメイン名
	 */
	@Size(max = 255)
	@Schema(description = "ドメイン名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String domainName;

	/**
	 * ドメイン引継ぎ元契約ID
	 */
	@Min(0)
	@Schema(description = "ドメイン引継ぎ元契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long domainHandoverContractId;

	/**
	 * ドメイン引継ぎ元RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "ドメイン引継ぎ元RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String domainHandoverRjManageNumber;

	/**
	 * ホスティングサービス区分
	 */
	@Schema(description = "ホスティングサービス区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "現状のホスティングサーバーを継続利用(\"1\"), 新規ホスティングサーバーにドメイン名だけ引き継ぐ(\"2\")")
	private HostingServiceDiv hostingServiceDiv;

	/**
	 * アカウント名
	 */
	@Size(max = 255)
	@Schema(description = "アカウント名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String accountName;

	/**
	 * アカウント引継ぎ元契約ID
	 */
	@Min(0)
	@Schema(description = "アカウント引継ぎ元契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long accountHandoverContractId;

	/**
	 * アカウント引継ぎ元RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "アカウント引継ぎ元RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String accountHandoverRjManageNumber;

	/**
	 * 接続ID
	 */
	@Size(max = 255)
	@Schema(description = "接続ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String connectId;

	/**
	 * 接続ID引継ぎ元契約ID
	 */
	@Min(0)
	@Schema(description = "接続ID引継ぎ元契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long connectHandoverContractId;

	/**
	 * 接続ID引継ぎ元RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "接続ID引継ぎ元RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String connectHandoverRjManageNumber;
}
