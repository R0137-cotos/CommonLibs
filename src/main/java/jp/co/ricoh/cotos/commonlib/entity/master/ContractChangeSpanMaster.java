package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.WorkflowStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約変更期間管理マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "contract_change_span_master")
public class ContractChangeSpanMaster extends EntityBaseMaster {

	@Description(value = "チェックパターン区分")
	public enum CheckPatternType {

		期間固定("1"), 期間可変("2");

		private final String text;

		private CheckPatternType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static CheckPatternType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "契約変更期間チェック対象日区分 開始、終了")
	public enum ContractChangeSpanTargetDateType {

		契約承認依頼日("1"), サービス利用希望日("2"), 解約希望日("3"), システム日付("4");

		private final String text;

		private ContractChangeSpanTargetDateType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ContractChangeSpanTargetDateType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 契約変更期間管理マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_change_span_master_seq")
	@SequenceGenerator(name = "contract_change_span_master_seq", sequenceName = "contract_change_span_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "契約変更期間管理マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 対象ドメイン
	 */
	@ApiModelProperty(value = "対象ドメイン", required = false, allowableValues = "共通(\"0\"), 見積(\"1\"), 契約(\"2\"), 手配(\"3\"), 電力_見積(\"101\"), 電力_契約(\"102\")", position = 2)
	private ServiceCategory serviceCategory;

	/**
	 * 商品マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "商品マスタID", required = false, position = 3, allowableValues = "range[0,9223372036854775807]")
	private Long productMasterId;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "品種マスタID", required = false, position = 4, allowableValues = "range[0,9223372036854775807]")
	private Long itemMasterId;

	/**
	 * 契約種別
	 */
	@ApiModelProperty(value = "契約種別", required = false, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")", position = 5)
	private ContractType contractType;

	/**
	 * 契約種別詳細
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約種別詳細", required = true, position = 6, allowableValues = "range[0,255]")
	private String contractTypeDetail;

	/**
	 * ライフサイクル状態
	 */
	@ApiModelProperty(value = "ライフサイクル状態", required = false, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\")", position = 7)
	private LifecycleStatus lifecycleStatus;

	/**
	 * ワークフロー状態
	 */
	@ApiModelProperty(value = "ワークフロー状態", required = false, allowableValues = "作成中(\"1\"), 承認依頼中(\"2\"), 承認済(\"3\"), 業務依頼中(\"4\"), 業務処理完了(\"5\"), キャンセル申請中(\"6\"), 売上可能(\"7\"), 解約申請中(\"8\")", position = 8)
	private WorkflowStatus workflowStatus;

	/**
	 * チェックパターン区分
	 */
	@NotNull
	@Column(nullable = false)
	@ApiModelProperty(value = "チェックパターン区分", required = true, allowableValues = "期間固定(\"1\"), 期間可変(\"2\")", position = 9)
	private CheckPatternType checkPatternType;

	/**
	 * 契約変更期間日付計算パターンマスタ 開始
	 */
	@ManyToOne
	@JoinColumn(name = "from_contract_change_span_calc_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "契約変更期間日付計算パターンマスタ 開始", required = false, position = 10)
	private DateCalcPatternMaster fromContractChangeDateCalcPatternMaster;

	/**
	 * 契約変更期間チェック対象日区分 開始
	 */
	@ApiModelProperty(value = "契約変更期間チェック対象日区分 開始", required = false, allowableValues = "契約承認依頼日(\"1\"), サービス利用希望日(\"2\"), 解約希望日(\"3\"), システム日付(\"4\")", position = 11)
	private ContractChangeSpanTargetDateType fromContractChangeSpanTargetDateType;

	/**
	 * 契約変更期間日付計算パターンマスタ 終了
	 */
	@ManyToOne
	@JoinColumn(name = "to_contract_change_span_calc_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "契約変更期間日付計算パターンマスタ 終了", required = false, position = 12)
	private DateCalcPatternMaster toContractChangeDateCalcPatternMaster;

	/**
	 * 契約変更期間チェック対象日区分 終了
	 */
	@ApiModelProperty(value = "契約変更期間チェック対象日区分 終了", required = false, allowableValues = "契約承認依頼日(\"1\"), サービス利用希望日(\"2\"), 解約希望日(\"3\"), システム日付(\"4\")", position = 13)
	private ContractChangeSpanTargetDateType toContractChangeSpanTargetDateType;

	/**
	 * チェック対象トランザクションテーブル名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "チェック対象トランザクションテーブル名", required = false, position = 14, allowableValues = "range[0,255]")
	private String checkTargetTransactionTableName;

	/**
	 * 追加条件式
	 */
	@ApiModelProperty(value = "追加条件式", required = false, position = 15)
	private String extendsQuery;

	/**
	 * エラーキー
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "エラーキー", required = false, position = 16, allowableValues = "range[0,255]")
	private String errorKey;

	/**
	 * エラーメッセージ置換文字列
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "エラーメッセージ置換文字列", required = false, position = 16, allowableValues = "range[0,255]")
	private String errorMessageReplaceString;

	/**
	 * エラーフィールド
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "エラーフィールド", required = false, position = 18, allowableValues = "range[0,255]")
	private String errorField;
}
