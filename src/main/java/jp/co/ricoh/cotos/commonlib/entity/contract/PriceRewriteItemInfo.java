package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.math.BigDecimal;
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
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Size;

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
 * 価格書換品種情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(PriceRewriteItemInfo.class)
@Data
@Table(name = "price_rewrite_item_info")
public class PriceRewriteItemInfo extends EntityBase {

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

	@Description(value = "旧品種非表示フラグ")
	public enum OldItemHiddenFlag {
		表示("0"), 非表示("1");

		private final String text;

		private OldItemHiddenFlag(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OldItemHiddenFlag fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}
	
	@Description(value = "書換リスト出力フラグ")
	public enum RewriteListOutputFlag {
		未出力("0"), 出力済み("1");

		private final String text;

		private RewriteListOutputFlag(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static RewriteListOutputFlag fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_rewrite_item_info_seq")
	@SequenceGenerator(name = "price_rewrite_item_info_seq", sequenceName = "price_rewrite_item_info_seq", allocationSize = 1)
	@ApiModelProperty(value = "価格書換品種情報", required = true, position = 1, readOnly = true)
	private long id;

	/**
	 * ファイル取込管理ID
	 */
	@ManyToOne(optional = false)
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
	 * 価格変更率
	 */
	@Column
	@DecimalMax("9999999999999999999.999")
	@ApiModelProperty(value = "価格変更率", required = false, position = 4, allowableValues = "range[0.00,99999999999999999.999]")
	private BigDecimal priceReviceRate;

	/**
	 * 原価（RJ仕入価格）
	 */
	@Column
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "原価（RJ仕入価格）", required = false, position = 5)
	private BigDecimal cost;

	/**
	 * Ｒ原価
	 */
	@Column
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "Ｒ原価", required = false, position = 6)
	private BigDecimal rCost;

	/**
	 * 母店仕切
	 */
	@Column
	@DecimalMax("9999999999999999999.999")
	@ApiModelProperty(value = "母店仕切", required = false, position = 7)
	private BigDecimal rjDividingPrice;

	/**
	 * 接点店仕切
	 */
	@Column
	@DecimalMax("9999999999999999999.999")
	@ApiModelProperty(value = "接点店仕切", required = false, position = 8)
	private BigDecimal motherStorePrice;

	/**
	 * 変更後品種コード
	 */
	@Column
	@Size(max = 255)
	@ApiModelProperty(value = "変更後品種コード", required = false, position = 9, allowableValues = "range[0,255]")
	private String afterChangeRicohItemCode;

	/**
	 * 変更後メーカー商品コード
	 */
	@Column
	@Size(max = 255)
	@ApiModelProperty(value = "変更後メーカー商品コード", required = false, position = 10, allowableValues = "range[0,255]")
	private String afterChangeMakerItemCode;

	/**
	 * 対象外契約日
	 */
	@Column
	@ApiModelProperty(value = "対象外契約日", required = false, position = 11)
	@Temporal(TemporalType.DATE)
	private Date noTargetContractDate;

	/**
	 * 状態
	 */
	@Column
	@ApiModelProperty(value = "状態", required = false, allowableValues = "未反映(\"0\"), 反映済み(\"1\"), 反映エラー(\"2\"), 反映不要(\"3\")", example = "1", position = 12)
	private Status status;

	/**
	 * 反映日時
	 */
	@Column
	@ApiModelProperty(value = "反映日時", required = false, position = 13)
	@Temporal(TemporalType.TIMESTAMP)
	private Date reflectionAt;

	/**
	 * 旧品種非表示フラグ
	 */
	@Column
	@ApiModelProperty(value = "旧品種非表示フラグ", required = false, allowableValues = "表示(\"0\"), 非表示(\"1\")", example = "1", position = 14)
	private OldItemHiddenFlag oldItemHiddenFlag;
	
	/**
	 * 書換リスト出力フラグ
	 */
	@Column
	@ApiModelProperty(value = "書換リスト出力フラグ", required = false, allowableValues = "未出力(\"0\"), 出力済み(\"1\")", example = "1", position = 15)
	private RewriteListOutputFlag rewriteListOutputFlag;

}
