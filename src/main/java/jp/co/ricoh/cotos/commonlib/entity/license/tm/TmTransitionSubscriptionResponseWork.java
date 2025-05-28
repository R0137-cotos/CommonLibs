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

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロサブスクリプション乗換レスポンスWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tm_transition_subscription_response_work")
public class TmTransitionSubscriptionResponseWork extends AbstractTmResponseWork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tm_transition_subscription_response_work_seq")
	@SequenceGenerator(name = "tm_transition_subscription_response_work_seq", sequenceName = "tm_transition_subscription_response_work_seq", allocationSize = 1)
	@ApiModelProperty(value = "トレンドマイクロサブスクリプション乗換レスポンスWORK ID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * トレンドマイクロサブスクリプション乗換リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "request_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "トレンドマイクロサブスクリプション乗換リクエストWORK", required = true, position = 2)
	private TmTransitionSubscriptionRequestWork requestWork;

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
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス開始日", required = false, position = 13)
	private Date licenseStartDate;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス終了日", required = false, position = 14)
	private Date licenseExpirationDate;

	/**
	 * 課金開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "課金開始日", required = false, position = 15)
	private Date startChargeDate;
}
