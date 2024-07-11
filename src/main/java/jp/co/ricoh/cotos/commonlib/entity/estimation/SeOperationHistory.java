package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
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
 * SE対応履歴を表すEntityです。
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "se_operation_history")
public class SeOperationHistory extends EntityBase {

	@Description(value = "処理区分")
	public enum ProcessDiv {

		insert("1"), update("2"), delete("3");

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
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "se_operation_history.seq")
	@SequenceGenerator(name = "se_operation_history.seq", sequenceName = "se_operation_history.seq", allocationSize = 1)
	@ApiModelProperty(value = "id(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 見積ID
	 */
	@NotNull
	@Digits(integer = 19, fraction = 0)
	@ApiModelProperty(value = "見積ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long estimationId;

	/**
	 * ドメイン
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "ドメイン", required = true, position = 3, allowableValues = "range[0,255]")
	private String domain;

	/**
	 * 処理区分
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "処理区分", required = true, position = 4, allowableValues = "insert(\"1\"), update(\"2\"), delete(\"3\")")
	private ProcessDiv processDiv;

	/**
	 * 処理内容
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "処理内容", required = true, position = 5, allowableValues = "range[0,255]")
	private String prosessContent;

	/**
	 * 有効期限From
	 */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "有効期限From", required = true, position = 6, allowableValues = "range[0,9223372036854775807]")
	private Date expirationDateFrom;

	/**
	 * 有効期限To
	 */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "有効期限To", required = true, position = 7, allowableValues = "range[0,9223372036854775807]")
	private Date expirationDateTo;

}
