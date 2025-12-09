package jp.co.ricoh.cotos.commonlib.entity.license.tm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロ連携管理エンティティ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tm_link_management")
public class TmLinkManagement extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tm_link_management_seq")
	@SequenceGenerator(name = "tm_link_management_seq", sequenceName = "tm_link_management_seq", allocationSize = 1)
	@Schema(description = "トレンドマイクロ連携管理ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 手配業務ID
	 */
	@Min(0)
	@Schema(description = "手配業務ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long arrangementWorkId;

	/**
	 * トレンドマイクロ顧客情報作成リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "tm_create_customer_request_work_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "トレンドマイクロ顧客情報作成リクエストWORK", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private TmCreateCustomerRequestWork tmCreateCustomerRequestWork;

	/**
	 * トレンドマイクロサブスクリプション作成リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "tm_create_subscription_request_work_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "トレンドマイクロサブスクリプション作成リクエストWORK", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private TmCreateSubscriptionRequestWork tmCreateSubscriptionRequestWork;

	/**
	 * トレンドマイクロサブスクリプション更新リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "tm_update_subscription_request_work_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "トレンドマイクロサブスクリプション更新リクエストWORK", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private TmUpdateSubscriptionRequestWork tmUpdateSubscriptionRequestWork;

	/**
	 * トレンドマイクロ会社情報更新リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "tm_update_customer_request_work_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "トレンドマイクロ会社情報更新リクエストWORK", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private TmUpdateCustomerRequestWork tmUpdateCustomerRequestWork;

	/**
	 * ユーザーアカウント更新リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "tm_update_user_request_work_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "ユーザーアカウント更新リクエストWORK", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private TmUpdateUserRequestWork tmUpdateUserRequestWork;

	/**
	 * トレンドマイクロサブスクリプション解約リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "tm_suspend_subscription_request_work_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "トレンドマイクロサブスクリプション解約リクエストWORK", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private TmSuspendSubscriptionRequestWork tmSuspendSubscriptionRequestWork;

	/**
	 * MVBアカウント
	 */
	@Size(max = 255)
	@Schema(description = "MVBアカウント", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String mvbAccount;

	/**
	 * 契約ID
	 */
	@Min(0)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private long contractId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * MoM企業ID
	 */
	@Size(max = 255)
	@Schema(description = "MoM企業ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String momCompanyId;

	/**
	 * MoM企事部ID
	 */
	@Size(max = 255)
	@Schema(description = "MoM企事部ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String momCustId;

	/**
	 * 企業名（カナ）
	 */
	@Size(max = 255)
	@Schema(description = "企業名（カナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyNameKana;

	/**
	 * ライセンス情報ID
	 */
	@Min(0)
	@Schema(description = "ライセンス情報ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long licenseInfoId;

	/**
	 * トレンドマイクロサブスクリプション乗換リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "tm_transition_subscription_request_work_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "トレンドマイクロサブスクリプション乗換リクエストWORK", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private TmTransitionSubscriptionRequestWork tmTransitionSubscriptionRequestWork;
}
