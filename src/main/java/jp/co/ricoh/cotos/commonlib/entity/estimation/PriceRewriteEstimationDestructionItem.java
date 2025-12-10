package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "価格書換見積破棄対象品種", requiredMode = Schema.RequiredMode.REQUIRED, readOnly = true)
	private long id;

	/**
	 * ファイル取込管理ID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "file_import_management_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "ファイル取込管理", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private FileImportManagement fileImportManagement;

	/**
	 * リコー品種コード
	 */
	@Column
	@Size(max = 255)
	@Schema(description = "リコー品種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 削除対象日
	 */
	@Column
	@Schema(description = "削除対象日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date deleteTargetDate;

	/**
	 * ステータス
	 */
	@Column
	@Schema(description = "ステータス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未反映(\"0\"), 反映済み(\"1\"), 反映エラー(\"2\"), 反映不要(\"3\")", example = "1")
	private Status status;

	/**
	 * 反映日時
	 */
	@Column
	@Schema(description = "反映日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.TIMESTAMP)
	private Date reflectionAt;
}
