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

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 処理状態
	 */
	@Schema(description = "処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 処理済(\"1\"), 処理対象外(\"9\"), 処理エラー(\"E\")", example = "0")
	private OsoProcessingStatus processingStatus;

	/**
	 * 処理日時
	 */
	@Schema(description = "処理日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.TIMESTAMP)
	private Date processingAt;

	/**
	 * メッセージ
	 */
	@Column(name = "message")
	@Size(max = 4000)
	@Schema(description = "メッセージ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,4000]")
	private String osoMessage;

	/**
	 * RJ管理番号
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long contractId;

	/**
	 * 契約明細ID
	 */
	@Column(nullable = false)
	@Schema(description = "契約明細ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long contractDetailId;

	/**
	 * 品種コード
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "品種コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String itemCode;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@Schema(description = "品種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * 使用年月
	 */
	@Schema(description = "使用年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date usageDate;

	/**
	 * 超過使用量
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "超過使用量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer overuseQuantity;

	/**
	 * 通知メール送信状態
	 */
	@Schema(description = "通知メール送信状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 処理済(\"1\"), 処理対象外(\"9\"), 処理エラー(\"E\")", example = "0")
	private NotificationMailSendStatus notificationMailSendStatus;

	/**
	 * OSO連携可能フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "OSO連携可能フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer osoLinkableFlg;
}
