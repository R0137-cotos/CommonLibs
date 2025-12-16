package jp.co.ricoh.cotos.commonlib.entity.license.ms;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.PaymentCycleType;
import jp.co.ricoh.cotos.commonlib.entity.master.LicenseServiceMaster.LicenseType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MS_サブスクリプション登録レスポンスWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ms_subscription_register_response_work")
public class MsSubscriptionRegisterResponseWork extends AbstractMsResponseWork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ms_subscription_register_response_work_seq")
	@SequenceGenerator(name = "ms_subscription_register_response_work_seq", sequenceName = "ms_subscription_register_response_work_seq", allocationSize = 1)
	@Schema(description = "MS_サブスクリプション登録レスポンスWORK", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * 処理状態
	 */
	@Schema(description = "処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未反映(\"0\"), 反映済(\"1\"), 反映不要(\"2\")")
	private MsResponseMappedStatus processStatus;

	/**
	 * 顧客テナントID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "顧客テナントID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * 製品ID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "製品ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String offerId;

	/**
	 * 数量
	 */
	@Size(max = 255)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String quantity;

	/**
	 * サブスクリプションID
	 */
	@Size(max = 255)
	@Schema(description = "サブスクリプションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String subscriptionId;

	/**
	 * ライセンスNO
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "ライセンスNO", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String licenseNo;

	/**
	 * プロダクト名
	 */
	@Size(max = 255)
	@Schema(description = "プロダクト名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String productName;

	/**
	 * 契約期間
	 */
	@Size(max = 255)
	@Schema(description = "契約期間", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractTerm;

	/**
	 * ライセンス取得日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ライセンス取得日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date licenseAcquisitionDate;

	/**
	 * 自動更新日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "自動更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date autoUpdateDate;

	/**
	 * 移行元サブスクリプションID
	 */
	@Size(max = 255)
	@Schema(description = "移行元サブスクリプションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String fromSubscriptionId;

	/**
	 * 調整後の終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "調整後の終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date customTermEndDate;

	/**
	 * 支払周期区分
	 */
	@Schema(description = "支払周期区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "月額(\"1\"), 年額(\"2\")")
	private PaymentCycleType paymentCycleType;

	/**
	 * ライセンス区分
	 */
	@Schema(description = "ライセンス区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "ベース(\"1\"), アドオン(\"2\")")
	private LicenseType licenseType;
}
