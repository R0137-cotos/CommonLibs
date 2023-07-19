package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "引継ぎ用ISP連携ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = false, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * メールアカウント
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メールアカウント", required = false, position = 3, allowableValues = "range[0,255]")
	private String mailAccount;

	/**
	 * メール引継ぎ元契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "メール引継ぎ元契約ID", required = false, position = 4, allowableValues = "range[0,9223372036854775807]")
	private Long mailHandoverContractId;

	/**
	 * ドメイン名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ドメイン名", required = false, position = 5, allowableValues = "range[0,255]")
	private String domainName;

	/**
	 * ドメイン引継ぎ元契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "ドメイン引継ぎ元契約ID", required = false, position = 6, allowableValues = "range[0,9223372036854775807]")
	private Long domainHandoverContractId;

	/**
	 * ドメイン引継ぎ元RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ドメイン引継ぎ元RJ管理番号", required = false, position = 7, allowableValues = "range[0,255]")
	private String domainHandoverRjManageNumber;

	/**
	 * ホスティングサービス区分
	 */
	@ApiModelProperty(value = "ホスティングサービス区分", required = false, position = 8, allowableValues = "現状のホスティングサーバーを継続利用(\"1\"), 新規ホスティングサーバーにドメイン名だけ引き継ぐ(\"2\")")
	private HostingServiceDiv hostingServiceDiv;

	/**
	 * アカウント名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "アカウント名", required = false, position = 9, allowableValues = "range[0,255]")
	private String accountName;

	/**
	 * アカウント引継ぎ元契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "アカウント引継ぎ元契約ID", required = false, position = 10, allowableValues = "range[0,9223372036854775807]")
	private Long accountHandoverContractId;

	/**
	 * アカウント引継ぎ元RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "アカウント引継ぎ元RJ管理番号", required = false, position = 11, allowableValues = "range[0,255]")
	private String accountHandoverRjManageNumber;

	/**
	 * 接続ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "接続ID", required = false, position = 12, allowableValues = "range[0,255]")
	private String connectId;

	/**
	 * 接続ID引継ぎ元契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "接続ID引継ぎ元契約ID", required = false, position = 13, allowableValues = "range[0,9223372036854775807]")
	private Long connectHandoverContractId;

	/**
	 * 接続ID引継ぎ元RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "接続ID引継ぎ元RJ管理番号", required = false, position = 14, allowableValues = "range[0,255]")
	private String connectHandoverRjManageNumber;
}
