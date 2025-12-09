package jp.co.ricoh.cotos.commonlib.dto.parameter.communication;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
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
	@Schema(description = "TO（メール送信時設定不要）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String to;

	/**
	 * CC
	 */
	@JsonProperty("Cc")
	@Size(max = 255)
	@Schema(description = "CC（メール送信時設定不要）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cc;

	/**
	 * 送信元メール送信日時
	 */
	@JsonProperty("Date")
	@Schema(description = "送信元メール送信日時（メール送信時設定不要）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.TIMESTAMP)
	private Date sentAt;

	/**
	 * 契約ID
	 * 【以下を設定】
	 * 見積：見積.RJ管理番号
	 * 契約：契約.RJ管理番号
	 * 手配：契約.RJ管理番号
	 */
	@JsonProperty("ContractId")
	@Size(max = 255)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractId;

	/**
	 * 文書番号
	 * 【以下を設定】
	 * 見積：見積.見積番号
	 * 契約：契約.契約番号
	 * 手配：契約.契約番号
	 */
	@JsonProperty("DocNumber")
	@Size(max = 255)
	@Schema(description = "文書番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String docNumber;

	/**
	 * 契約番号
	 *  * 【以下を設定】
	 * 見積：見積.恒久契約識別番号
	 * 契約：契約.恒久契約識別番号
	 * 手配：契約.恒久契約識別番号
	 */
	@JsonProperty("ContractNumber")
	@Size(max = 255)
	@Schema(description = "契約番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 *  * 【以下を設定】
	 * 見積：見積.見積番号枝番
	 * 契約：契約.契約番号枝番
	 * 手配：契約.契約番号枝番
	 */
	@JsonProperty("ContractBranchNumber")
	@Max(99)
	@Min(0)
	@Schema(description = "契約番号枝番", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer contractBranchNumber;

	/**
	 * メールテンプレートマスタID
	 */
	@JsonProperty("MailTemplateMasterId")
	@Min(0)
	@Schema(description = "メールテンプレートマスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long mailTemplateMasterId;

	/**
	 * N_X_CONTRACT_ID
	 */
	@JsonProperty("X-contractId")
	@Size(max = 255)
	@Schema(description = "N_X_CONTRACT_ID（メール送信時設定不要）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nXContractId;

	/**
	 * N_X_MAILER
	 */
	@JsonProperty("X-Mailer")
	@Size(max = 255)
	@Schema(description = "N_X_MAILER（メール送信時設定不要）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nXMailer;

	/**
	 * N_X_NPSERVICENO
	 */
	@JsonProperty("X-NPServiceNo")
	@Min(0)
	@Schema(description = "N_X_NPSERVICENO（メール送信時設定不要）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long nXNpserviceno;

	/**
	 * N_X_NSERVICELINENO
	 */
	@JsonProperty("X-NServiceLineNo")
	@Min(0)
	@Schema(description = "N_X_NSERVICELINENO（メール送信時設定不要）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long nXNservicelineno;

	/**
	 * N_X_NDOMAINLINENO
	 */
	@JsonProperty("X-NDomainLineNo")
	@Min(0)
	@Schema(description = "N_X_NDOMAINLINENO（メール送信時設定不要）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long nXNdomainlineno;

	/**
	 * N_X_NGUIDETARGETTYPE
	 */
	@JsonProperty("X-NGuideTargetType")
	@Size(max = 255)
	@Schema(description = "N_X_NGUIDETARGETTYPE（メール送信時設定不要）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nXNguidetargettype;

	/**
	 * N_X_JIZENFLG
	 */
	@JsonProperty("X-jizenFlag")
	@Size(max = 255)
	@Schema(description = "N_X_JIZENFLG（メール送信時設定不要）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nXJizenflg;

	/**
	 * 通知メール制御マスタID
	 */
	@JsonProperty("MailControlMasterId")
	@Min(0)
	@Schema(description = "通知メール制御マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long mailControlMasterId;

	/**
	 * サービスカテゴリ
	 */
	@JsonProperty("ServiceCategory")
	@Schema(description = "サービスカテゴリ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "見積(\"1\"), 契約(\"2\"), 手配(\"3\")")
	private ServiceCategory serviceCategory;

	/**
	 * 対象文書キー
	 */
	@JsonProperty("TargetDocKey")
	@Size(max = 255)
	@Schema(description = "対象文書キー<br />コミュニケーションの対象情報を一意に特定するためのキー情報を格納<br />" //
			+ "見積⇒見積ID<br />" //
			+ "契約⇒契約ID<br />" //
			+ "手配⇒手配業務ID", required = false, allowableValues = "range[0,255]") //
	private String targetDocKey;
}
