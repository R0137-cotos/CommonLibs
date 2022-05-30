package jp.co.ricoh.cotos.commonlib.entity.license.ms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.license.ms.AbstractMsResponseWork.MsResponseMappedStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MS_サブスクリプション登録レスポンスWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ms_subscription_register_response_work")
public class MsSubscriptionRegisterResponseWork extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ms_subscription_register_response_work_seq")
	@SequenceGenerator(name = "ms_subscription_register_response_work_seq", sequenceName = "ms_subscription_register_response_work_seq", allocationSize = 1)
	@ApiModelProperty(value = "MS_サブスクリプション登録レスポンスWORK", required = true, position = 1, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * 処理状態
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "処理状態", required = false, position = 2, allowableValues = "range[0,255]")
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
	private String offer_id;

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
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス取得日", required = false, position = 10)
	private Date licenseAcquisitionDate;

	/**
	 * 自動更新日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "自動更新日", required = false, position = 11)
	private Date autoUpdateDate;
}
