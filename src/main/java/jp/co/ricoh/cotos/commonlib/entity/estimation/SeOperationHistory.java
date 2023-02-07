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
	public enum ProcessDiv {
		insert("0"), update("1"), delete("2");
		
		private final String text;
		
		private ProcessDiv(final String text) {
			this.text = text;
		}
		
		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}
		
		@JsonCreator
		public static ProcessDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}
	
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "se_operation_history_seq")
	@SequenceGenerator(name = "se_operation_history_seq", sequenceName = "se_operation_history_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
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
	@ApiModelProperty(value = "処理区分", position = 3, allowableValues = "insert(\"0\"), update(\"1\"), delete(\"2\")")
	private ProcessDiv processDiv;
	
	/**
	 * 処理内容
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "処理内容", position = 4)
	private String processContent;
}