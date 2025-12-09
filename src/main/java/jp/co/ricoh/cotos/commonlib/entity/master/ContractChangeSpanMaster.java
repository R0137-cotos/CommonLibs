package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "契約変更期間管理マスタID(作成時不要)", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 対象ドメイン
	 */
	@Schema(description = "対象ドメイン", required = false, allowableValues = "共通(\"0\"), 見積(\"1\"), 契約(\"2\"), 手配(\"3\"), 電力_見積(\"101\"), 電力_契約(\"102\")")
	private ServiceCategory serviceCategory;

	/**
	 * 商品マスタID
	 */
	@Min(0)
	@Schema(description = "商品マスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long productMasterId;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@Schema(description = "品種マスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long itemMasterId;

	/**
	 * 契約種別
	 */
	@Schema(description = "契約種別", required = false, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")")
	private ContractType contractType;

	/**
	 * 契約種別詳細
	 */
	@Size(max = 255)
	@Schema(description = "契約種別詳細", required = true, allowableValues = "range[0,255]")
	private String contractTypeDetail;

	/**
	 * ライフサイクル状態
	 */
	@Schema(description = "ライフサイクル状態", required = false, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\")")
	private LifecycleStatus lifecycleStatus;

	/**
	 * ワークフロー状態
	 */
	@Schema(description = "ワークフロー状態", required = false, allowableValues = "作成中(\"1\"), 承認依頼中(\"2\"), 承認済(\"3\"), 業務依頼中(\"4\"), 業務処理完了(\"5\"), キャンセル申請中(\"6\"), 売上可能(\"7\"), 解約申請中(\"8\")")
	private WorkflowStatus workflowStatus;

	/**
	 * チェックパターン区分
	 */
	@NotNull
	@Column(nullable = false)
	@Schema(description = "チェックパターン区分", required = true, allowableValues = "期間固定(\"1\"), 期間可変(\"2\")")
	private CheckPatternType checkPatternType;

	/**
	 * 契約変更期間日付計算パターンマスタ 開始
	 */
	@ManyToOne
	@JoinColumn(name = "from_contract_change_span_calc_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約変更期間日付計算パターンマスタ 開始", required = false)
	private DateCalcPatternMaster fromContractChangeDateCalcPatternMaster;

	/**
	 * 契約変更期間チェック対象日区分 開始
	 */
	@Schema(description = "契約変更期間チェック対象日区分 開始", required = false, allowableValues = "契約承認依頼日(\"1\"), サービス利用希望日(\"2\"), 解約希望日(\"3\"), システム日付(\"4\")")
	private ContractChangeSpanTargetDateType fromContractChangeSpanTargetDateType;

	/**
	 * 契約変更期間日付計算パターンマスタ 終了
	 */
	@ManyToOne
	@JoinColumn(name = "to_contract_change_span_calc_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約変更期間日付計算パターンマスタ 終了", required = false)
	private DateCalcPatternMaster toContractChangeDateCalcPatternMaster;

	/**
	 * 契約変更期間チェック対象日区分 終了
	 */
	@Schema(description = "契約変更期間チェック対象日区分 終了", required = false, allowableValues = "契約承認依頼日(\"1\"), サービス利用希望日(\"2\"), 解約希望日(\"3\"), システム日付(\"4\")")
	private ContractChangeSpanTargetDateType toContractChangeSpanTargetDateType;

	/**
	 * チェック対象トランザクションテーブル名
	 */
	@Size(max = 255)
	@Schema(description = "チェック対象トランザクションテーブル名", required = false, allowableValues = "range[0,255]")
	private String checkTargetTransactionTableName;

	/**
	 * 追加条件式
	 */
	@Schema(description = "追加条件式", required = false)
	private String extendsQuery;

	/**
	 * エラーキー
	 */
	@Size(max = 255)
	@Schema(description = "エラーキー", required = false, allowableValues = "range[0,255]")
	private String errorKey;

	/**
	 * エラーメッセージ置換文字列
	 */
	@Size(max = 255)
	@Schema(description = "エラーメッセージ置換文字列", required = false, allowableValues = "range[0,255]")
	private String errorMessageReplaceString;

	/**
	 * エラーフィールド
	 */
	@Size(max = 255)
	@Schema(description = "エラーフィールド", required = false, allowableValues = "range[0,255]")
	private String errorField;

	/**
	 * 承認アラート管理マスタID
	 */
	@Min(0)
	@Schema(description = "承認アラート管理マスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long approvalAlertManagementMasterId;

}
