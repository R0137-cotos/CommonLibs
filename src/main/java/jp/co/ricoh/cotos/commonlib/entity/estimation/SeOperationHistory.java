package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "se_operation_history")
public class SeOperationHistory extends EntityBase {

	@Description(value = "処理区分")
	public enum ProcessingCategory {

		insert("0"), update("1"), delete("2");

		private final String text;

		private ProcessingCategory(final String text) {
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

	/** ID */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "se_operation_history_seq")
	@SequenceGenerator(name = "se_operation_history_seq", sequenceName = "se_operation_history_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID", required = true, position = 1, allowableValues = "range[0,19]")
	private long id;

	/** 見積ID */
	@ApiModelProperty(value = "見積ID", required = true, position = 2, allowableValues = "range[0,19]")
	private long estimationId;

	/**　ドメイン(RSI, ROC, その他など) */
	@Size(max = 255)
	@ApiModelProperty(value = "ドメイン", required = true, position = 3)
	private String domains;

	/** 処理区分(insert, update, delete)*/
	@Size(max = 255)
	@ApiModelProperty(value = "処理区分", required = true, allowableValues = "insert(\"0\"), update(\"1\"), delete(\"2\")", position = 4)
	private ProcessingCategory processingCategory;

	/** 処理内容 */
	@Size(max = 255)
	@ApiModelProperty(value = "処理内容", required = true, position = 5, allowableValues = "range[0,255]")
	private String processingDetails;

	/** 有効期限From */
	@ApiModelProperty(value = "有効期限From", required = true, position = 6)
	private Timestamp validityPeriodFrom;

	/** 有効期限To */
	@ApiModelProperty(value = "有効期限To", required = true, position = 7)
	private Timestamp validityPeriodTo;

}
