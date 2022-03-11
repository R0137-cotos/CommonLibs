package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約変更期間管理マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "approval_alert_management_master")
public class ApprovalAlertManagementMaster extends EntityBaseMaster {

	@Description(value = "アラート対象日区分")
	public enum AlertTargetDateType {

		サービス利用希望日("1"), 解約希望日("2");

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

	/**
	 * 契約変更期間管理マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "approval_alert_management_master")
	@SequenceGenerator(name = "approval_alert_management_master_seq", sequenceName = "approval_alert_management_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "承認アラート管理マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * アラート対象日区分
	 */
	@ApiModelProperty(value = "アラート対象日区分",  position = 2, required = true, allowableValues = "サービス利用希望日(\"1\"), 解約希望日(\"2\")")
	private AlertTargetDateType alertTargetDateType;

	/**
	 * アラート基準日
	 */
	@ApiModelProperty(value = "アラート基準日", required = true, position = 3, allowableValues = "range[0,99]")
	private int alertTargetDate;

	/**
	 * アラートメッセージ
	 */
	@ApiModelProperty(value = "アラートメッセージ", required = true, position = 4)
	private String alertMessage;
}
