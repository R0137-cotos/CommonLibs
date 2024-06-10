package jp.co.ricoh.cotos.commonlib.entity.master;

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
import javax.validation.constraints.Max;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 価格改定日マスタを表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "price_revision_date_master")
public class PriceRevisionDateMaster extends EntityBaseMaster {

	@Description(value = "差額処理区分")
	public enum DifferenceProcDiv {

		差額考慮不要("1"), 差額率("2"), 差額("3"), 更新不要("4");

		private final String text;

		private DifferenceProcDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static DifferenceProcDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "差額計算区分")
	public enum DifferenceCalcDiv {

		四捨五入("1"), 切り上げ("2"), 切り捨て("3");

		private final String text;

		private DifferenceCalcDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static DifferenceCalcDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 価格改定日マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_revision_date_master_seq")
	@SequenceGenerator(name = "price_revision_date_master_seq", sequenceName = "price_revision_date_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "価格改定日マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品マスタID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "商品マスタID", required = true, position = 2, allowableValues = "range[0,9999999999999999999]")
	private Long productMasterId;

	/**
	 * 価格改定日
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "価格改定日", required = true, position = 3)
	@Temporal(TemporalType.DATE)
	private Date priceRevisionDate;

	/**
	 * BTCOCR024バッチ対象フラグ
	 */
	@Max(9)
	@ApiModelProperty(value = "BTCOCR024バッチ対象フラグ", required = false, position = 4, allowableValues = "range[0,9]")
	private Integer btcocr024batchFlg;

	/**
	 * 差額処理区分（差額マイナス）
	 */
	@ApiModelProperty(value = "差額処理区分（差額マイナス）", required = false, allowableValues = "差額考慮不要(\"1\"), 差額率(\"2\"), 差額(\"3\"), 更新不要(\"4\")", example = "1", position = 5)
	private DifferenceProcDiv differenceProcDivMinus;

	/**
	 * 差額処理区分（差額プラス）
	 */
	@ApiModelProperty(value = "差額処理区分（差額プラス）", required = false, allowableValues = "差額考慮不要(\"1\"), 差額率(\"2\"), 差額(\"3\"), 更新不要(\"4\")", example = "1", position = 6)
	private DifferenceProcDiv differenceProcDivPlus;

	/**
	 * 差額計算区分（差額マイナス）
	 */
	@ApiModelProperty(value = "差額計算区分（差額マイナス）", required = false, allowableValues = "四捨五入(\"1\"), 切り上げ(\"2\"), 切り捨て(\"3\")", example = "1", position = 7)
	private DifferenceCalcDiv differenceCalcDivMinus;

	/**
	 * 差額計算区分（差額プラス）
	 */
	@ApiModelProperty(value = "差額計算区分（差額プラス）", required = false, allowableValues = "四捨五入(\"1\"), 切り上げ(\"2\"), 切り捨て(\"3\")", example = "1", position = 8)
	private DifferenceCalcDiv differenceCalcDivPlus;

	/**
	 * 契約ライフサイクル状態
	 */
	@ApiModelProperty(value = "契約ライフサイクル状態", required = false, position = 9, allowableValues = "range[0,255]")
	private String contractLifecycleStatus;

	/**
	 * 追加条件式
	 */
	@ApiModelProperty(value = "追加条件式", required = false, position = 10)
	private String extendsQuery;
}
