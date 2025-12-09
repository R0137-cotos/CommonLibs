package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
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

	@Description(value = "日付計算区分")
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

	@Description(value = "日付計算基準日区分")
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

	@Description(value = "日付計算営業日計算区分")
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
	@Schema(description = "日付計算パターンマスタID(作成時不要)", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 日付計算グループコード
	 */
	@Size(max = 255)
	@Schema(description = "日付計算グループコード", required = false, allowableValues = "range[0,255]")
	private String dateCalcGrpCode;

	/**
	 * 日付計算グループ名称
	 */
	@Size(max = 255)
	@Schema(description = "日付計算グループ名称", required = false, allowableValues = "range[0,255]")
	private String dateCalcGrpName;

	/**
	 * 日付計算パターン名称
	 */
	@Size(max = 255)
	@Schema(description = "日付計算パターン名称", required = false, allowableValues = "range[0,255]")
	private String dateCalcPatternName;

	/**
	 * 日付計算区分
	 */
	@NotNull
	@Column(nullable = false)
	@Schema(description = "日付計算区分", required = true, allowableValues = "月日加算(\"1\")")
	private DateCalcType dateCalcType;

	/**
	 * 日付計算基準日区分
	 */
	@NotNull
	@Column(nullable = false)
	@Schema(description = "日付計算基準日区分", required = true, allowableValues = "サービス終了日(\"1\"), 契約開始日(\"2\"), システム日付(\"3\")")
	private DateCalcStndType dateCalcStndType;

	/**
	 * 日付計算月数
	 */
	@Size(max = 255)
	@Schema(description = "日付計算月数", required = false, allowableValues = "range[0,255]")
	private String dateCalcMonth;

	/**
	 * 日付計算日数
	 */
	@Size(max = 255)
	@Schema(description = "日付計算日数", required = false, allowableValues = "range[0,255]")
	private String dateCalcDay;

	/**
	 * 日付計算営業日計算区分
	 */
	@Schema(description = "日付計算営業日計算区分", required = true, allowableValues = "前営業日(\"1\"), 後営業日(\"2\")")
	private DateCalcBusinessDayType dateCalcBusinessDayType;

	/**
	 * 日付計算設定時間
	 */
	@Size(max = 255)
	@Schema(description = "日付計算設定時間", required = false, allowableValues = "range[0,255]")
	private String dateCalcSetTime;

	/**
	 * 日付計算営業日フラグ
	 */
	@Min(0)
	@Max(9)
	@Schema(description = "日付計算営業日フラグ", required = false, allowableValues = "range[0,9]")
	private Integer dateCalcBusinessDayFlg;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", required = false)
	private String extendsParameter;
}
