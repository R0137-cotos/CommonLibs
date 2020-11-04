package jp.co.ricoh.cotos.commonlib.entity.license.cas.tm;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロサブスクリプション更新レスポンスWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tm_update_subscription_response_work")
public class TmUpdateSubscriptionResponseWork extends AbstractTmResponseWork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tm_update_subscription_response_work_seq")
	@SequenceGenerator(name = "tm_update_subscription_response_work_seq", sequenceName = "tm_update_subscription_response_work_seq", allocationSize = 1)
	@ApiModelProperty(value = "トレンドマイクロサブスクリプション更新レスポンスWORK", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * トレンドマイクロサブスクリプション更新リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "request_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "トレンドマイクロサブスクリプション更新リクエストWORK", required = true, position = 2)
	private TmUpdateSubscriptionRequestWork requestWork;

	/**
	 * サブスクリプションID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サブスクリプションID", required = false, position = 6, allowableValues = "range[0,255]")
	private String subscriptionId;
	/**
	 * 製品名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "製品名", required = false, position = 7, allowableValues = "range[0,255]")
	private String productName;

	/**
	 * ログイン画面URL
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ログイン画面URL", required = false, position = 8, allowableValues = "range[0,255]")
	private String serviceUrl;

	/**
	 * 製品ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "製品ID", required = false, position = 9, allowableValues = "range[0,255]")
	private String productId;

	/**
	 * 製品版/体験版
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "製品版/体験版", required = false, position = 10, allowableValues = "range[0,255]")
	private String versionlicenceVersion;

	/**
	 * アクティベーションコード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "アクティベーションコード", required = false, position = 11, allowableValues = "range[0,255]")
	private String acCode;

	/**
	 * シート数
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "シート数", required = false, position = 12, allowableValues = "range[0,255]")
	private String units;

	/**
	 * ライセンス開始日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "Asia/Tokyo")
	@ApiModelProperty(value = "ライセンス開始日", required = false, position = 13, allowableValues = "range[0,255]")
	private Date licenseStartDate;

	/**
	 * ライセンス終了日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "Asia/Tokyo")
	@ApiModelProperty(value = "会社ID", required = false, position = 14, allowableValues = "range[0,255]")
	private Date licenseExpirationDate;

	/**
	 * 課金開始日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "Asia/Tokyo")
	@ApiModelProperty(value = "会社ID", required = false, position = 15, allowableValues = "range[0,255]")
	private Date startChargeDate;
}
