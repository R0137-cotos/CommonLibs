package jp.co.ricoh.cotos.commonlib.entity.license.tm;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

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
	@Temporal(TemporalType.TIMESTAMP)
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
