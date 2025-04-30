package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ファイル操作関連商品マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "file_operation_relation_product_master")
public class FileOperationRelationProductMaster extends EntityBaseMaster {

	@Description(value = "カテゴリ")
	public enum CategoryType {

		ヤマト便管理("1"), ライセンス管理("2"), 外部連携("3"), マスタ管理("4"), LaIT連携("5"), リコーひかり("6"), 機器情報("7"), 振替情報("8");

		private final String text;

		private CategoryType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static CategoryType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ファイル操作関連商品マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_operation_relation_product_master_seq")
	@SequenceGenerator(name = "file_operation_relation_product_master_seq", sequenceName = "file_operation_relation_product_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "ファイル操作関連商品マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * メニュー管理マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "メニュー管理マスタID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long menuManagementMasterId;

	/**
	 * 関連商品名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "関連商品名", required = false, position = 3, allowableValues = "range[0,255]")
	private String relationProductName;

	/**
	 * カテゴリ
	 */
	@ApiModelProperty(value = "カテゴリ", required = false, allowableValues = "ヤマト便管理(\"1\"), ライセンス管理(\"2\"), 外部連携(\"3\"), マスタ管理(\"4\"), LaIT連携(\"5\"), リコーひかり(\"6\"), 機器情報(\"7\"), 振替情報(\"8\")", position = 4)
	private CategoryType categoryType;

	/**
	 * メニュー明細管理マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "メニュー明細管理マスタID", required = true, position = 5, allowableValues = "range[0,9223372036854775807]")
	private long menuDetailsManagementMasterId;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = true)
	@Min(0)
	@ApiModelProperty(value = "ライセンス区分マスタID", required = false, position = 6, allowableValues = "range[0,9223372036854775807]")
	private Long licenseDivMasterId;

	/**
	 * ファイル種別管理マスタ
	 */
	@OneToMany(mappedBy = "fileOperationRelationProductMaster")
	@JsonIgnore
	@ApiModelProperty(value = "ファイル種別管理マスタ", required = false, position = 7)
	private List<FileKindManagementMaster> fileKindManagementMasterList;
}
