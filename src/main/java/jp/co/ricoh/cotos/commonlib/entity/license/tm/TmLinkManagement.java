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

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "トレンドマイクロ連携管理ID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 手配業務ID
	 */
	@Min(0)
	@ApiModelProperty(value = "手配業務ID", required = false, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long arrangementWorkId;

	/**
	 * トレンドマイクロ顧客情報作成リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "tm_create_customer_request_work_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "トレンドマイクロ顧客情報作成リクエストWORK", required = false, position = 3)
	private TmCreateCustomerRequestWork tmCreateCustomerRequestWork;

	/**
	 * トレンドマイクロサブスクリプション作成リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "tm_create_subscription_request_work_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "トレンドマイクロサブスクリプション作成リクエストWORK", required = false, position = 4)
	private TmCreateSubscriptionRequestWork tmCreateSubscriptionRequestWork;

	/**
	 * トレンドマイクロサブスクリプション更新リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "tm_update_subscription_request_work_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "トレンドマイクロサブスクリプション更新リクエストWORK", required = false, position = 5)
	private TmUpdateSubscriptionRequestWork tmUpdateSubscriptionRequestWork;

	/**
	 * トレンドマイクロ会社情報更新リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "tm_update_customer_request_work_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "トレンドマイクロ会社情報更新リクエストWORK", required = false, position = 6)
	private TmUpdateCustomerRequestWork tmUpdateCustomerRequestWork;

	/**
	 * ユーザーアカウント更新リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "tm_update_user_request_work_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "ユーザーアカウント更新リクエストWORK", required = false, position = 7)
	private TmUpdateUserRequestWork tmUpdateUserRequestWork;

	/**
	 * トレンドマイクロサブスクリプション解約リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "tm_suspend_subscription_request_work_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "トレンドマイクロサブスクリプション解約リクエストWORK", required = false, position = 8)
	private TmSuspendSubscriptionRequestWork tmSuspendSubscriptionRequestWork;

	/**
	 * MVBアカウント
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "MVBアカウント", required = false, position = 9, allowableValues = "range[0,255]")
	private String mvbAccount;

	/**
	 * 契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = false, position = 10, allowableValues = "range[0,255]")
	private long contractId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 11, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * MoM企業ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "MoM企業ID", required = false, position = 12, allowableValues = "range[0,255]")
	private String momCompanyId;

	/**
	 * MoM企事部ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "MoM企事部ID", required = false, position = 13, allowableValues = "range[0,255]")
	private String momCustId;

	/**
	 * 企業名（カナ）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "企業名（カナ）", required = false, position = 14, allowableValues = "range[0,255]")
	private String companyNameKana;

	/**
	 * ライセンス情報ID
	 */
	@Min(0)
	@ApiModelProperty(value = "ライセンス情報ID", required = false, position = 15, allowableValues = "range[0,9223372036854775807]")
	private long licenseInfoId;

	/**
	 * トレンドマイクロサブスクリプション乗換リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "tm_transition_subscription_request_work_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "トレンドマイクロサブスクリプション乗換リクエストWORK", required = false, position = 16)
	private TmTransitionSubscriptionRequestWork tmTransitionSubscriptionRequestWork;
}
