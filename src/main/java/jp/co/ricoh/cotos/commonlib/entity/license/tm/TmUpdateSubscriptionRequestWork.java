package jp.co.ricoh.cotos.commonlib.entity.license.tm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロサブスクリプション更新リクエストWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tm_update_subscription_request_work")
public class TmUpdateSubscriptionRequestWork extends AbstractTmRequestWork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tm_update_subscription_request_work_seq")
	@SequenceGenerator(name = "tm_update_subscription_request_work_seq", sequenceName = "tm_update_subscription_request_work_seq", allocationSize = 1)
	@ApiModelProperty(value = "トレンドマイクロサブスクリプション更新リクエストWORK ID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 会社ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "会社ID", required = false, position = 7, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * サブスクリプションID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サブスクリプションID", required = false, position = 8, allowableValues = "range[0,255]")
	private String subscriptionId;

	/**
	 * ユニット数
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ユニット数", required = false, position = 9, allowableValues = "range[0,255]")
	private String unitsPerLicense;

	/**
	 * トレンドマイクロサブスクリプション更新レスポンスWORK
	 */
	@Valid
	@OneToOne(mappedBy = "requestWork")
	@ApiModelProperty(value = "トレンドマイクロサブスクリプション更新レスポンスWORK", required = false, position = 10)
	private TmUpdateSubscriptionResponseWork responseWork;
}
