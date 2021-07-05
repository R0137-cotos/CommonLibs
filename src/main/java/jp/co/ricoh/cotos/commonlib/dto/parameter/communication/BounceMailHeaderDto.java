package jp.co.ricoh.cotos.commonlib.dto.parameter.communication;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * バウンスメールのヘッダー情報を表します。
 */
@Data
@NoArgsConstructor
public class BounceMailHeaderDto {

	/**
	 * TO
	 */
	@JsonProperty("To")
	@Size(max = 255)
	@ApiModelProperty(value = "TO（メール送信時設定不要）", required = false, position = 1, allowableValues = "range[0,255]")
	private String to;

	/**
	 * CC
	 */
	@JsonProperty("Cc")
	@Size(max = 255)
	@ApiModelProperty(value = "CC（メール送信時設定不要）", required = false, position = 2, allowableValues = "range[0,255]")
	private String cc;

	/**
	 * 送信元メール送信日時
	 */
	@JsonProperty("Date")
	@ApiModelProperty(value = "送信元メール送信日時（メール送信時設定不要）", required = false, position = 3)
	@Temporal(TemporalType.TIMESTAMP)
	private Date sentAt;

	/**
	 * 契約ID
	 */
	@JsonProperty("ContractId")
	@Size(max = 255)
	@ApiModelProperty(value = "契約ID", required = false, position = 4, allowableValues = "range[0,255]")
	private String contractId;

	/**
	 * 文書番号
	 */
	@JsonProperty("DocNumber")
	@Size(max = 255)
	@ApiModelProperty(value = "文書番号", required = false, position = 5, allowableValues = "range[0,255]")
	private String docNumber;

	/**
	 * 契約番号
	 */
	@JsonProperty("ContractNumber")
	@Size(max = 255)
	@ApiModelProperty(value = "契約番号", required = false, position = 6, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@JsonProperty("ContractBranchNumber")
	@Max(99)
	@Min(0)
	@ApiModelProperty(value = "契約番号枝番", required = false, position = 7)
	private Integer contractBranchNumber;

	/**
	 * メールテンプレートマスタID
	 */
	@JsonProperty("MailTemplateMasterId")
	@Min(0)
	@ApiModelProperty(value = "メールテンプレートマスタID", required = false, position = 8)
	private Long mailTemplateMasterId;

	/**
	 * N_X_CONTRACT_ID
	 */
	@JsonProperty("X-contractId")
	@Size(max = 255)
	@ApiModelProperty(value = "N_X_CONTRACT_ID（メール送信時設定不要）", required = false, position = 9, allowableValues = "range[0,255]")
	private String nXContractId;

	/**
	 * N_X_MAILER
	 */
	@JsonProperty("X-Mailer")
	@Size(max = 255)
	@ApiModelProperty(value = "N_X_MAILER（メール送信時設定不要）", required = false, position = 10, allowableValues = "range[0,255]")
	private String nXMailer;

	/**
	 * N_X_NPSERVICENO
	 */
	@JsonProperty("X-NPServiceNo")
	@Min(0)
	@ApiModelProperty(value = "N_X_NPSERVICENO（メール送信時設定不要）", required = false, position = 11)
	private Long nXNpserviceno;

	/**
	 * N_X_NSERVICELINENO
	 */
	@JsonProperty("X-NServiceLineNo")
	@Min(0)
	@ApiModelProperty(value = "N_X_NSERVICELINENO（メール送信時設定不要）", required = false, position = 12)
	private Long nXNservicelineno;

	/**
	 * N_X_NDOMAINLINENO
	 */
	@JsonProperty("X-NDomainLineNo")
	@Min(0)
	@ApiModelProperty(value = "N_X_NDOMAINLINENO（メール送信時設定不要）", required = false, position = 13)
	private Long nXNdomainlineno;

	/**
	 * N_X_NGUIDETARGETTYPE
	 */
	@JsonProperty("X-NGuideTargetType")
	@Size(max = 255)
	@ApiModelProperty(value = "N_X_NGUIDETARGETTYPE（メール送信時設定不要）", required = false, position = 14, allowableValues = "range[0,255]")
	private String nXNguidetargettype;

	/**
	 * N_X_JIZENFLG
	 */
	@JsonProperty("X-jizenFlag")
	@Size(max = 255)
	@ApiModelProperty(value = "N_X_JIZENFLG（メール送信時設定不要）", required = false, position = 15, allowableValues = "range[0,255]")
	private String nXJizenflg;

	/**
	 * 通知メール制御マスタID
	 */
	@JsonProperty("MailControlMasterId")
	@Min(0)
	@ApiModelProperty(value = "通知メール制御マスタID", required = false, position = 16)
	private Long mailControlMasterId;
}
