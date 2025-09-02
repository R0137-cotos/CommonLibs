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

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "MS_サブスクリプション登録レスポンスWORK", required = true, position = 1, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * 処理状態
	 */
	@ApiModelProperty(value = "処理状態", required = false, position = 2, allowableValues = "未反映(\"0\"), 反映済(\"1\"), 反映不要(\"2\")")
	private MsResponseMappedStatus processStatus;

	/**
	 * 顧客テナントID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "顧客テナントID", required = true, position = 3, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * 製品ID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "製品ID", required = true, position = 4, allowableValues = "range[0,255]")
	private String offerId;

	/**
	 * 数量
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "数量", required = false, position = 5, allowableValues = "range[0,255]")
	private String quantity;

	/**
	 * サブスクリプションID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サブスクリプションID", required = false, position = 6, allowableValues = "range[0,255]")
	private String subscriptionId;

	/**
	 * ライセンスNO
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンスNO", required = true, position = 7, allowableValues = "range[0,255]")
	private String licenseNo;

	/**
	 * プロダクト名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "プロダクト名", required = false, position = 8, allowableValues = "range[0,255]")
	private String productName;

	/**
	 * 契約期間
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約期間", required = false, position = 9, allowableValues = "range[0,255]")
	private String contractTerm;

	/**
	 * ライセンス取得日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "ライセンス取得日", required = false, position = 10)
	private Date licenseAcquisitionDate;

	/**
	 * 自動更新日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "自動更新日", required = false, position = 11)
	private Date autoUpdateDate;

	/**
	 * 移行元サブスクリプションID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "移行元サブスクリプションID", required = false, position = 12, allowableValues = "range[0,255]")
	private String fromSubscriptionId;

	/**
	 * 調整後の終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "調整後の終了日", required = false, position = 13)
	private Date customTermEndDate;

	/**
	 * 支払周期区分
	 */
	@ApiModelProperty(value = "支払周期区分", required = false, position = 14, allowableValues = "月額(\"1\"), 年額(\"2\")")
	private PaymentCycleType paymentCycleType;

	/**
	 * ライセンス区分
	 */
	@ApiModelProperty(value = "ライセンス区分", required = false, position = 15, allowableValues = "ベース(\"1\"), アドオン(\"2\")")
	private LicenseType licenseType;
}
