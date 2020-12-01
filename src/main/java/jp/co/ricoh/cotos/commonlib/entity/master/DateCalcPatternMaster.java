package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日付計算パターンマスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "date_calc_pattern_master")
public class DateCalcPatternMaster extends EntityBaseMaster {

	public enum DateCalcType {

		月日加算("1"), その他("9");

		private final String text;

		private DateCalcType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static DateCalcType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	public enum DateCalcStndType {

		サービス終了日("1"), 契約開始日("2"), システム日付("3");

		private final String text;

		private DateCalcStndType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static DateCalcStndType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	public enum DateCalcBusinessDayType {

		前営業日("1"), 後営業日("2");

		private final String text;

		private DateCalcBusinessDayType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static DateCalcBusinessDayType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 日付計算パターンマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "date_calc_pattern_master_seq")
	@SequenceGenerator(name = "date_calc_pattern_master_seq", sequenceName = "date_calc_pattern_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "日付計算パターンマスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 日付計算グループコード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "日付計算グループコード", required = false, position = 2, allowableValues = "range[0,255]")
	private String dateCalcGrpCode;

	/**
	 * 日付計算グループ名称
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "日付計算グループ名称", required = false, position = 3, allowableValues = "range[0,255]")
	private String dateCalcGrpName;

	/**
	 * 日付計算パターン名称
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "日付計算パターン名称", required = false, position = 4, allowableValues = "range[0,255]")
	private String dateCalcPatternName;

	/**
	 * 日付計算区分
	 */
	@NotNull
	@Column(nullable = false)
	@ApiModelProperty(value = "日付計算区分", required = true, allowableValues = "月日加算(\"1\")", position = 5)
	private DateCalcType dateCalcType;

	/**
	 * 日付計算基準日区分
	 */
	@NotNull
	@Column(nullable = false)
	@ApiModelProperty(value = "日付計算基準日区分", required = true, allowableValues = "サービス終了日(\"1\"), 契約開始日(\"2\"), システム日付(\"3\")", position = 6)
	private DateCalcStndType dateCalcStndType;

	/**
	 * 日付計算月数
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "日付計算月数", required = false, position = 7, allowableValues = "range[0,255]")
	private String dateCalcMonth;

	/**
	 * 日付計算日数
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "日付計算日数", required = false, position = 8, allowableValues = "range[0,255]")
	private String dateCalcDay;

	/**
	 * 日付計算営業日計算区分
	 */
	@ApiModelProperty(value = "日付計算営業日計算区分", required = true, allowableValues = "前営業日(\"1\"), 後営業日(\"2\")", position = 9)
	private DateCalcBusinessDayType dateCalcBusinessDayType;

	/**
	 * 日付計算設定時間
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "日付計算設定時間", required = false, position = 10, allowableValues = "range[0,255]")
	private String dateCalcSetTime;

	/**
	 * 日付計算営業日フラグ
	 */
	@Min(0)
	@Max(9)
	@ApiModelProperty(value = "日付計算営業日フラグ", required = false, position = 11, allowableValues = "range[0,9]")
	private Integer dateCalcBusinessDayFlg;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 12)
	private String extendsParameter;
}
