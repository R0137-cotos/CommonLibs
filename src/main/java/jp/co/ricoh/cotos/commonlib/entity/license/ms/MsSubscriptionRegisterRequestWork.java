package jp.co.ricoh.cotos.commonlib.entity.license.ms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MS_サブスクリプション登録リクエストWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ms_subscription_register_request_work")
public class MsSubscriptionRegisterRequestWork extends AbstractMsRequestWork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ms_subscription_register_request_work_seq")
	@SequenceGenerator(name = "ms_subscription_register_request_work_seq", sequenceName = "ms_subscription_register_request_work_seq", allocationSize = 1)
	@ApiModelProperty(value = "MS_サブスクリプション登録リクエストWORK", required = true, position = 1, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * ライセンス情報ID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "ライセンス情報ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long licenseInfoId;

	/**
	 * 処理状態
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "処理状態", required = false, position = 3, allowableValues = "range[0,255]")
	private MsRequestStatus processStatus;

	/**
	 * 顧客テナントID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "顧客テナントID", required = true, position = 4, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * 製品ID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "製品ID", required = true, position = 5, allowableValues = "range[0,255]")
	private String offer_id;

	/**
	 * 数量
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "数量", required = false, position = 6, allowableValues = "range[0,255]")
	private String quantity;

	/**
	 * サブスクリプションID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サブスクリプションID", required = false, position = 7, allowableValues = "range[0,255]")
	private String subscriptionId;

	/**
	 * ライセンスNO
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンスNO", required = true, position = 8, allowableValues = "range[0,255]")
	private String licenseNo;

	/**
	 * プロダクト名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "プロダクト名", required = false, position = 9, allowableValues = "range[0,255]")
	private String productName;

	/**
	 * 契約期間
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約期間", required = false, position = 10, allowableValues = "range[0,255]")
	private String contractTerm;


}
