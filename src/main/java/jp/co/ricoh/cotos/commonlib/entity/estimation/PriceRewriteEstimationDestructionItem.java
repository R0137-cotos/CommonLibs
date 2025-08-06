package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.common.FileImportManagement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 価格書換見積破棄対象品種を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(PriceRewriteEstimationDestructionItem.class)
@Data
@Table(name = "price_rewrite_estimation_destruction_item")
public class PriceRewriteEstimationDestructionItem extends EntityBase {

	@Description(value = "ステータス")
	public enum Status {
		未反映("0"), 反映済み("1"), 反映エラー("2"), 反映不要("3");

		private final String text;

		private Status(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static Status fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_rewrite_estimation_destruction_item_seq")
	@SequenceGenerator(name = "price_rewrite_estimation_destruction_item_seq", sequenceName = "price_rewrite_estimation_destruction_item_seq", allocationSize = 1)
	@ApiModelProperty(value = "価格書換見積破棄対象品種", required = true, position = 1, readOnly = true)
	private long id;

	/**
	 * ファイル取込管理ID
	 */
	@OneToOne(optional = false)
	@JoinColumn(name = "file_import_management_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "ファイル取込管理", required = false, position = 2)
	private FileImportManagement fileImportManagement;

	/**
	 * リコー品種コード
	 */
	@Column
	@Size(max = 255)
	@ApiModelProperty(value = "リコー品種コード", required = false, position = 3, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 削除対象日
	 */
	@Column
	@ApiModelProperty(value = "削除対象日", required = false, position = 4)
	@Temporal(TemporalType.DATE)
	private Date deleteTargetDate;

	/**
	 * ステータス
	 */
	@Column
	@ApiModelProperty(value = "ステータス", required = false, allowableValues = "未反映(\"0\"), 反映済み(\"1\"), 反映エラー(\"2\"), 反映不要(\"3\")", example = "1", position = 5)
	private Status status;

	/**
	 * 反映日時
	 */
	@Column
	@ApiModelProperty(value = "反映日時", required = false, position = 6)
	@Temporal(TemporalType.TIMESTAMP)
	private Date reflectionAt;
}
