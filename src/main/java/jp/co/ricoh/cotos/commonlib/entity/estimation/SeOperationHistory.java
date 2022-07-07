package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SE対応履歴
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "se_operation_history")
public class SeOperationHistory extends EntityBase {

	@Description(value = "処理区分")
	public enum OperationType {

		insert("1"), update("2"), delete("3");

		private final String text;

		private OperationType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OperationType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "se_operation_history_seq")
	@SequenceGenerator(name = "se_operation_history_seq", sequenceName = "se_operation_history_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 見積ID
	 */
	@Column(nullable = false)
	@NotNull
	@ApiModelProperty(value = "見積ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long estimationId;

	/**
	 * 処理区分
	 */
	@Column(nullable = false)
	@NotNull
	@ApiModelProperty(value = "処理区分", required = true, allowableValues = "insert(\"1\"), update(\"2\"), delete(\"3\")", position = 3)
	private OperationType operationType;

	/**
	 * 処理内容
	 */
	@Column(nullable = false)
	@NotNull
	@ApiModelProperty(value = "処理内容", required = true, position = 4)
	private String operationDescription;

}
