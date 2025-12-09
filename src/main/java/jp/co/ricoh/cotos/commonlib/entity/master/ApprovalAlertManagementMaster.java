package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 承認アラート管理マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "approval_alert_management_master")
public class ApprovalAlertManagementMaster extends EntityBaseMaster {

	@Description(value = "アラート対象日区分")
	public enum AlertTargetDateType {

		サービス利用希望日("1"), 解約予定日("2");

		private final String text;

		private AlertTargetDateType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static AlertTargetDateType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "アラート基準日区分")
	public enum AlertBaseDateType {

		当月("0"), 前月("1");

		private final String text;

		private AlertBaseDateType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static AlertBaseDateType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 承認アラート管理マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "approval_alert_management_master_seq")
	@SequenceGenerator(name = "approval_alert_management_master_seq", sequenceName = "approval_alert_management_master_seq", allocationSize = 1)
	@Schema(description = "承認アラート管理マスタID(作成時不要)", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * アラート対象日区分
	 */
	@Schema(description = "アラート対象日区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "サービス利用希望日(\"1\"), 解約予定日(\"2\")")
	private AlertTargetDateType alertTargetDateType;

	/**
	 * アラート基準日
	 */
	@Schema(description = "アラート基準日", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,99]")
	private int alertBaseDate;

	/**
	 * アラートメッセージ
	 */
	@Schema(description = "アラートメッセージ", requiredMode = Schema.RequiredMode.REQUIRED)
	private String alertMessage;

	/**
	 * アラート基準日区分
	 */
	@Schema(description = "アラート基準日区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "当月(\"0\"), 前月(\"1\")")
	private AlertBaseDateType alertBaseDateType;
}
