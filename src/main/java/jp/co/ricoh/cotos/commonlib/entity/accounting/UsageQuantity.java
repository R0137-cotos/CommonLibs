package jp.co.ricoh.cotos.commonlib.entity.accounting;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoProcessingStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "usage_quantity")
public class UsageQuantity extends EntityBase {

	@Description(value = "通知メール送信状態")
	public enum NotificationMailSendStatus {

		未処理("0"), 処理済("1"), 処理対象外("9"), 処理エラー("E");

		private final String text;

		private NotificationMailSendStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static NotificationMailSendStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usage_quantity_seq")
	@SequenceGenerator(name = "usage_quantity_seq", sequenceName = "usage_quantity_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 処理状態
	 */
	@ApiModelProperty(value = "処理状態", required = false, allowableValues = "未処理(\"0\"), 処理済(\"1\"), 処理対象外(\"9\"), 処理エラー(\"E\")", example = "0", position = 2)
	private OsoProcessingStatus processingStatus;

	/**
	 * 処理日時
	 */
	@ApiModelProperty(value = "処理日時", required = false, position = 3)
	@Temporal(TemporalType.TIMESTAMP)
	private Date processingAt;

	/**
	 * メッセージ
	 */
	@Column(name = "message")
	@Size(max = 4000)
	@ApiModelProperty(value = "メッセージ", required = false, position = 4, allowableValues = "range[0,4000]")
	private String osoMessage;

	/**
	 * RJ管理番号
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = true, position = 5, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "契約ID", required = true, position = 6, allowableValues = "range[0,9999999999999999999]")
	private long contractId;

	/**
	 * 契約明細ID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "契約明細ID", required = true, position = 7, allowableValues = "range[0,9999999999999999999]")
	private long contractDetailId;

	/**
	 * 品種コード
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "品種コード", required = true, position = 8, allowableValues = "range[0,255]")
	private String itemCode;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "品種コード", required = false, position = 9, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * 使用年月
	 */
	@ApiModelProperty(value = "使用年月", required = false, position = 10)
	@Temporal(TemporalType.DATE)
	private Date usageDate;

	/**
	 * 超過使用量
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "超過使用量", required = false, position = 11, allowableValues = "range[0,99999]")
	private Integer overuseQuantity;

	/**
	 * 通知メール送信状態
	 */
	@ApiModelProperty(value = "通知メール送信状態", required = false, allowableValues = "未処理(\"0\"), 処理済(\"1\"), 処理対象外(\"9\"), 処理エラー(\"E\")", example = "0", position = 12)
	private NotificationMailSendStatus notificationMailSendStatus;

	/**
	 * OSO連携可能フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "OSO連携可能フラグ", required = false, position = 13, allowableValues = "range[0,9]")
	private Integer osoLinkableFlg;
}
