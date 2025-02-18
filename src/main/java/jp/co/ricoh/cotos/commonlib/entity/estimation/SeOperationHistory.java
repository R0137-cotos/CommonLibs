package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SE対応履歴を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "se_operation_history")
public class SeOperationHistory extends EntityBase {

	@Description(value = "ドメイン")
	public enum Domain {

		RSI("1"), ROC("2"), OTHER("3");

		private final String text;

		private Domain (final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static Domain fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "処理区分")
	public enum ProcessingCategory {

		insert("1"), update("2"), delete("3");

		private final String text;

		private ProcessingCategory (final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ProcessingCategory fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "se_operation_history_seq")
	@Column(nullable = false)
	@NotNull
	@SequenceGenerator(name = "se_operation_history_seq", sequenceName = "se_operation_history_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID(作成時不要)", required = true, position = 1, readOnly = true)
	private long id;

	/**
	 * 見積ID
	 */
	@Min(0)
	@Column(nullable = false)
	@NotNull
	@ApiModelProperty(value = "見積ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long estimationId;

	/**
	 * ドメイン
	 */
	@Column(nullable = false)
	@NotNull
	@ApiModelProperty(value = "ドメイン", required = true, allowableValues = "RSI(\"1\"), ROC(\"2\"), OTHER(\"3\")", example = "1", position = 3, readOnly = true)
	private Domain domain;

	/**
	 * 処理区分
	 */
	@Column(nullable = false)
	@NotNull
	@ApiModelProperty(value = "処理区分", required = true, allowableValues = "insert(\"1\"), update(\"2\"), delete(\"3\")", example = "1", position = 4, readOnly = true)
	private ProcessingCategory processingCategory;

	/**
	 * 処理内容
	 */
	@Size(max = 255)
	@Column(nullable = false)
	@NotNull
	@ApiModelProperty(value = "処理内容", required = true, position = 5, allowableValues = "range[0,255]")
	private String processingDetails;

	/**
	 * 有効期限From
	 */
	@Column(nullable = false)
	@NotNull
	@ApiModelProperty(value = "有効期限From", required = true, position = 6)
	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationFrom;

    /**
	 * 有効期限To
	 */
	@Column(nullable = false)
	@NotNull
	@ApiModelProperty(value = "有効期限To", required = true, position = 7)
	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationTo;

}