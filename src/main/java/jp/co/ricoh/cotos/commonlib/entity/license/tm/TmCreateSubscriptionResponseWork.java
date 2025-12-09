package jp.co.ricoh.cotos.commonlib.entity.license.tm;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロサブスクリプション作成レスポンスWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tm_create_subscription_response_work")
public class TmCreateSubscriptionResponseWork extends AbstractTmResponseWork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tm_create_subscription_response_work_seq")
	@SequenceGenerator(name = "tm_create_subscription_response_work_seq", sequenceName = "tm_create_subscription_response_work_seq", allocationSize = 1)
	@Schema(description = "トレンドマイクロサブスクリプション作成レスポンスWORK ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * トレンドマイクロサブスクリプション作成リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "request_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "トレンドマイクロサブスクリプション作成リクエストWORK", requiredMode = Schema.RequiredMode.REQUIRED)
	private TmCreateSubscriptionRequestWork requestWork;

	/**
	 * サブスクリプションID
	 */
	@Size(max = 255)
	@Schema(description = "サブスクリプションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String subscriptionId;
	/**
	 * 製品名
	 */
	@Size(max = 255)
	@Schema(description = "製品名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String productName;

	/**
	 * ログイン画面URL
	 */
	@Size(max = 255)
	@Schema(description = "ログイン画面URL", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String serviceUrl;

	/**
	 * 製品ID
	 */
	@Size(max = 255)
	@Schema(description = "製品ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String productId;

	/**
	 * 製品版/体験版
	 */
	@Size(max = 255)
	@Schema(description = "製品版/体験版", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String versionlicenceVersion;

	/**
	 * アクティベーションコード
	 */
	@Size(max = 255)
	@Schema(description = "アクティベーションコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String acCode;

	/**
	 * シート数
	 */
	@Size(max = 255)
	@Schema(description = "シート数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String units;

	/**
	 * ライセンス開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ライセンス開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private Date licenseStartDate;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ライセンス終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private Date licenseExpirationDate;

	/**
	 * 課金開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "課金開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private Date startChargeDate;
}
