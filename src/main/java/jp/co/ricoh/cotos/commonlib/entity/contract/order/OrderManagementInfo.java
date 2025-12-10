package jp.co.ricoh.cotos.commonlib.entity.contract.order;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
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

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注文管理情報
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "order_management_info")
public class OrderManagementInfo extends EntityBase {

	@Description(value = "契約取込状況")
	public enum CaptureStatus {

		未取込("0"), 取込済("1"), 取込対象外("9"), 処理不可("E"), 異常取込("W"), 取込情報("I");

		private final String text;

		private CaptureStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static CaptureStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "注文受付メール送信状況")
	public enum OrderReceptionMailSendStatus {

		未送信("0"), 送信済み("1");

		private final String text;

		private OrderReceptionMailSendStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OrderReceptionMailSendStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}

	}

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_management_info_seq")
	@SequenceGenerator(name = "order_management_info_seq", sequenceName = "order_management_info_seq", allocationSize = 1)
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long id;

	/**
	 * 注文基本情報ID
	 */
	@OneToOne(optional = false)
	@JoinColumn(name = "order_basic_info_id", referencedColumnName = "id")
	@JsonIgnore
	private OrderBasicInfo orderBasicInfo;

	/**
	 * 契約取込状況
	 */
	@Column
	@Schema(description = "契約取込状況", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "未取込(\"0\"), 取込済(\"1\"), 取込対象外(\"9\"), 処理不可(\"E\"), 異常取込(\"W\"), 取込情報(\"I\")")
	private CaptureStatus contractCaptureStatus;

	/**
	 * 契約取込日時
	 */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "契約取込日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date contractCaptureAt;

	/**
	 * 処理不可事由
	 */
	@Column
	@Schema(description = "処理不可事由", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String unprocessedReason;

	/**
	 * RJ管理番号
	 */
	@Column
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String rjManageNumber;

	/**
	 * 契約ID
	 */
	@Column
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private long contractId;

	/**
	 * 見積ID
	 */
	@Column
	@Schema(description = "見積ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private long estimationId;

	/**
	 * 注文受付メールの送信状況
	 */
	@Column
	@Schema(description = "注文受付メールの送信状況", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未送信(\"0\"), 送信済み(\"1\")")
	private OrderReceptionMailSendStatus orderReceptionMailSendStatus;

	/**
	 * 注文受付メールの送信日時
	 */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "注文受付メール送信日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date orderReceptionMailSendAt;
}
