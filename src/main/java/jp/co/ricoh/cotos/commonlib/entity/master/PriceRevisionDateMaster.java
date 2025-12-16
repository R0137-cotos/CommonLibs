package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "価格改定日マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品マスタID
	 */
	@Column(nullable = false)
	@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long productMasterId;

	/**
	 * 価格改定日
	 */
	@Column(nullable = false)
	@Schema(description = "価格改定日", requiredMode = Schema.RequiredMode.REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date priceRevisionDate;

	/**
	 * 価格改定処理グループID
	 */
	@Schema(description = "価格改定処理グループID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long priceRevisionProcessGrpId;

	/**
	 * BTCOCR024バッチ対象フラグ
	 */
	@Max(9)
	@Schema(description = "BTCOCR024バッチ対象フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer btcocr024batchFlg;

	/**
	 * 差額処理区分（差額マイナス）
	 */
	@Schema(description = "差額処理区分（差額マイナス）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "差額考慮不要(\"1\"), 差額率(\"2\"), 差額(\"3\"), 更新不要(\"4\")", example = "1")
	private DifferenceProcDiv differenceProcDivMinus;

	/**
	 * 差額処理区分（差額プラス）
	 */
	@Schema(description = "差額処理区分（差額プラス）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "差額考慮不要(\"1\"), 差額率(\"2\"), 差額(\"3\"), 更新不要(\"4\")", example = "1")
	private DifferenceProcDiv differenceProcDivPlus;

	/**
	 * 差額計算区分（差額マイナス）
	 */
	@Schema(description = "差額計算区分（差額マイナス）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "四捨五入(\"1\"), 切り上げ(\"2\"), 切り捨て(\"3\")", example = "1")
	private DifferenceCalcDiv differenceCalcDivMinus;

	/**
	 * 差額計算区分（差額プラス）
	 */
	@Schema(description = "差額計算区分（差額プラス）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "四捨五入(\"1\"), 切り上げ(\"2\"), 切り捨て(\"3\")", example = "1")
	private DifferenceCalcDiv differenceCalcDivPlus;

	/**
	 * 契約ライフサイクル状態
	 */
	@Schema(description = "契約ライフサイクル状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractLifecycleStatus;

	/**
	 * 追加条件式
	 */
	@Schema(description = "追加条件式", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String extendsQuery;
}
