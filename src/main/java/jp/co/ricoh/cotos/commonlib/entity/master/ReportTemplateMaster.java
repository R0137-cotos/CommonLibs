package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 帳票テンプレート管理マスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "report_template_master")
public class ReportTemplateMaster extends EntityBaseMaster {

	/**
	 * 主要ドメインのライフサイクル状態をマージしたステータス
	 */
	@Description(value = "ライフサイクル状態(帳票テンプレート)")
	public enum LifecycleStatus {

		共通("0"), 作成中("1"), 作成完了("2"), 受注("3"), 失注("4"), 破棄("5"), キャンセル手続き中("6"), 予定日待ち("7"), 締結中("8"), 解約手続き中("9"), 解約予定日待ち("10"), 解約("11"), 旧契約("12"), 締結待ち("13");

		private final String text;

		private LifecycleStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static LifecycleStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 主要ドメインのワークフロー状態をマージしたステータス
	 */
	@Description(value = "ワークフロー状態(帳票テンプレート)")
	public enum WorkflowStatus {

		共通("0"), 作成中("1"), 業務依頼中("2"), 業務処理完了("3"), 承認依頼中("4"), 承認済("5"), 顧客提示済("6"), キャンセル申請中("7"), 売上可能("8"), 解約申請中("9"), 手配中("10"), 手配完了("11"), 受付待ち("12"), 作業中("13"), 作業完了報告("14"), 作業完了("15"), エラー("16");

		private final String text;

		private WorkflowStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static WorkflowStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 出力形式
	 */
	@Description(value = "出力形式")
	public enum OutputType {

		PDF("1"), Excel("2");

		private final String text;

		private OutputType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OutputType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 対象種別
	 */
	@Description(value = "対象種別")
	public enum TargetType {

		新規("1"), 契約変更("2"), 情報変更("3"), 解約("4"), 契約更新("5");

		private final String text;

		private TargetType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static TargetType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * テンプレートID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_template_master_seq")
	@SequenceGenerator(name = "report_template_master_seq", sequenceName = "report_template_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "テンプレートID", required = true, position = 1, allowableValues = "range[0,9999999999999999999999999999]")
	private long id;

	/**
	 * テンプレート名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "テンプレート名", required = false, position = 2, allowableValues = "range[0,255]")
	private String templateName;

	/**
	 * テンプレート区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "テンプレート区分", required = false, position = 3, allowableValues = "range[0,255]")
	private String templateType;

	/**
	 * 出力形式
	 */
	@ApiModelProperty(value = "出力形式", required = false, allowableValues = "PDF(\"1\"), Excel(\"2\")", position = 4)
	private OutputType outputType;

	/**
	 * テンプレートパス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "テンプレートパス", required = false, position = 5, allowableValues = "range[0,255]")
	private String templatePath;

	/**
	 * サービスカテゴリ
	 */
	@ApiModelProperty(value = "サービスカテゴリ", required = false, allowableValues = "見積(\"1\"), 契約(\"2\"),  手配(\"3\")", position = 6)
	private ServiceCategory serviceCategory;

	/**
	 * 対象種別
	 */
	@ApiModelProperty(value = "対象種別", required = false, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 解約(\"4\"), 契約更新(\"5\")", position = 6)
	private TargetType targetType;

	/**
	 * 商流区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "商流区分", required = false, position = 7, allowableValues = "range[0,255]")
	private String commercialFlowDiv;

	/**
	 * 商品マスタID
	 */
	@ApiModelProperty(value = "商品マスタID", required = false, position = 8, allowableValues = "range[0,9999999999999999999]")
	private Long productMasterId;

	/**
	 * ライフサイクル状態
	 */
	@ApiModelProperty(value = "ライフサイクル状態", required = false, allowableValues = "共通(\"0\"), 作成中(\"1\"), 作成完了(\"2\"), 受注(\"3\"), 失注(\"4\"), 破棄(\"5\"), キャンセル手続き中(\"6\"), 予定日待ち(\"7\"), 締結中(\"8\"), 解約手続き中(\"9\"), 解約予定日待ち(\"10\"), 解約(\"11\"), 旧契約(\"12\"), 締結待ち(\"13\")", example = "1", position = 9)
	private LifecycleStatus lifecycleStatus;

	/**
	 * ワークフロー状態
	 */
	@ApiModelProperty(value = "ワークフロー状態", required = false, allowableValues = "共通(\"0\"), 作成中(\"1\"), 業務依頼中(\"2\"), 業務処理完了(\"3\"), 承認依頼中(\"4\"), 承認済(\"5\"), 顧客提示済(\"6\"), キャンセル申請中(\"7\"), 売上可能(\"8\"), 解約申請中(\"9\"), 手配中(\"10\"), 手配完了(\"11\"), 受付待ち(\"12\"), 作業中(\"13\"), 作業完了報告(\"14\"), 作業完了(\"15\"), エラー(\"16\")", example = "1", position = 10)
	private WorkflowStatus workflowStatus;

	/**
	 * 拡張子
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "拡張子", required = false, position = 11, allowableValues = "range[0,255]")
	private String extension;

	/**
	 * CreateForm帳票コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "CreateForm帳票コード", required = false, position = 12, allowableValues = "range[0,255]")
	private String createFormReportCode;

	/**
	 * スタイルファイル名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "スタイルファイル名", required = false, position = 13, allowableValues = "range[0,255]")
	private String styleFileName;

	/**
	 * 総ページ数
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "ジョブユニットID", required = false, position = 14, allowableValues = "range[0,99999]")
	private Integer totalPageNumber;

	/**
	 * 帳票ページ管理マスタ
	 */
	@OneToMany(mappedBy = "reportTemplateMaster")
	@ApiModelProperty(value = "帳票ページ管理マスタ", required = false, position = 15)
	private List<ReportPageMaster> reportPageMasterList;

	/**
	 * 電子契約連携対象フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "電子契約連携対象フラグ", required = false, position = 16, allowableValues = "range[0,9]")
	private Integer electronicContractLinkageFlg;

	/**
	 * 利用開始希望日ありフラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "利用開始希望日ありフラグ", required = false, position = 17, allowableValues = "range[0,9]")
	private Integer desiredStartDateExistFlg;
}
