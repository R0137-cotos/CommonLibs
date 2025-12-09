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

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "トレンドマイクロサブスクリプション更新リクエストWORK ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 会社ID
	 */
	@Size(max = 255)
	@Schema(description = "会社ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * サブスクリプションID
	 */
	@Size(max = 255)
	@Schema(description = "サブスクリプションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String subscriptionId;

	/**
	 * ユニット数
	 */
	@Size(max = 255)
	@Schema(description = "ユニット数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String unitsPerLicense;

	/**
	 * トレンドマイクロサブスクリプション更新レスポンスWORK
	 */
	@Valid
	@OneToOne(mappedBy = "requestWork")
	@Schema(description = "トレンドマイクロサブスクリプション更新レスポンスWORK", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private TmUpdateSubscriptionResponseWork responseWork;
}
