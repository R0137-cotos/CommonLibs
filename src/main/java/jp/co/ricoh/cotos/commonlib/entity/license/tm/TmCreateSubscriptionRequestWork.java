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

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "サブスクリプション作成リクエストWORK ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 会社ID
	 */
	@Size(max = 255)
	@Schema(description = "会社ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * サービスプランID
	 */
	@Size(max = 255)
	@Schema(description = "サービスプランID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String servicePlanId;

	/**
	 * ユニット数
	 */
	@Size(max = 255)
	@Schema(description = "ユニット数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String unitsPerLicense;

	/**
	 * ライセンス開始日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "ライセンス開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private Date licenseStartDate;

	/**
	 * トレンドマイクロサブスクリプション作成レスポンスWORK
	 */
	@Valid
	@OneToOne(mappedBy = "requestWork")
	@Schema(description = "トレンドマイクロサブスクリプション作成レスポンスWORK", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private TmCreateSubscriptionResponseWork responseWork;
}
