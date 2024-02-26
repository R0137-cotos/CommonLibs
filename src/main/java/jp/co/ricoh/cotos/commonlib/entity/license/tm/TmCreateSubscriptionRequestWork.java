package jp.co.ricoh.cotos.commonlib.entity.license.tm;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロサブスクリプション作成リクエストWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tm_create_subscription_request_work")
public class TmCreateSubscriptionRequestWork extends AbstractTmRequestWork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tm_create_subscription_request_work_seq")
	@SequenceGenerator(name = "tm_create_subscription_request_work_seq", sequenceName = "tm_create_subscription_request_work_seq", allocationSize = 1)
	@ApiModelProperty(value = "サブスクリプション作成リクエストWORK ID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 会社ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "会社ID", required = false, position = 7, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * サービスプランID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サービスプランID", required = false, position = 8, allowableValues = "range[0,255]")
	private String servicePlanId;

	/**
	 * ユニット数
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ユニット数", required = false, position = 9, allowableValues = "range[0,255]")
	private String unitsPerLicense;

	/**
	 * ライセンス開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス開始日", required = false, position = 10, allowableValues = "range[0,255]")
	private Date licenseStartDate;

	/**
	 * トレンドマイクロサブスクリプション作成レスポンスWORK
	 */
	@Valid
	@OneToOne(mappedBy = "requestWork")
	@ApiModelProperty(value = "トレンドマイクロサブスクリプション作成レスポンスWORK", required = false, position = 11)
	private TmCreateSubscriptionResponseWork responseWork;
}
