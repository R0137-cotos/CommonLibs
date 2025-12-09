package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ライセンスを検索するためのキー項目クラスを表します。
 */
@Data
public class LicenseSearchParameter {

	/**
	 * ライセンス検索区分
	 */
	public enum LicenseSearchDiv {

		通常検索("1"), 追加検索("2"), 新規検索("3"), 保守延長検索("4"), 開通確認検索("5"), 解約検索("6");

		private final String text;

		private LicenseSearchDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static LicenseSearchDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ライセンス検索区分
	 */
	@Parameter(description = "ライセンス検索区分", required = false)
	@Schema(description = "ライセンス検索区分", required = false, allowableValues = "通常検索(\"1\"), 追加検索(\"2\"), 新規検索(\"3\"), 保守延長検索(\"4\"), 開通確認検索(\"5\"), 解約検索(\"6\")")
	private LicenseSearchDiv licenseSearchDiv;

	/**
	 * ライセンス区分マスタID
	 */
	@Parameter(description = "ライセンス区分マスタID", required = false)
	@Schema(description = "ライセンス区分マスタID", required = false, allowableValues = "range[0,255]")
	private String licenseDivMasterId;

	/**
	 * RJ管理番号
	 */
	@Parameter(description = "RJ管理番号", required = false)
	@Schema(description = "RJ管理番号", required = false, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@Parameter(description = "契約番号", required = false)
	@Schema(description = "契約番号", required = false, allowableValues = "range[0,15]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Parameter(description = "契約番号枝番", required = false)
	@Schema(description = "契約番号枝番", required = false, allowableValues = "range[0,2]")
	private String contractBranchNumber;

	/**
	 * オーガニゼーションID
	 */
	@Parameter(description = "オーガニゼーションID", required = false)
	@Schema(description = "オーガニゼーションID", required = false, allowableValues = "range[0,255]")
	private String organizationId;

	/**
	 * ライセンスキー
	 */
	@Parameter(description = "ライセンスキー", required = false)
	@Schema(description = "ライセンスキー", required = false, allowableValues = "range[0,255]")
	private String licenseKey;

	/**
	 * アカウント
	 */
	@Parameter(description = "アカウント", required = false)
	@Schema(description = "アカウント", required = false, allowableValues = "range[0,255]")
	private String account;

	/**
	 * 工程ステータス
	 */
	@Parameter(description = "工程ステータス", required = false)
	@Schema(description = "工程ステータス<br />" //
			+ "ライセンス検索条件の「工程ステータス」の検索に使用するカラム", //
			required = false, allowableValues = "range[0,255]")
	private String licenseProcessStatus;

	/**
	 * サービス利用希望日（前）
	 */
	@Parameter(description = "サービス利用希望日（前）", required = false)
	@Schema(description = "サービス利用希望日（前）<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false)
	private Date conclusionPreferredDateFrom;

	/**
	 * サービス利用希望日（後）
	 */
	@Parameter(description = "サービス利用希望日（後）", required = false)
	@Schema(description = "サービス利用希望日（後）<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false)
	private Date conclusionPreferredDateTo;

	/**
	 * 契約種別
	 */
	@Parameter(description = "契約種別", required = false)
	@Schema(description = "契約種別<br />" //
			+ "新規、契約変更、情報変更、契約更新などの契約種別を表す。", //
			required = false)
	private String contractType;

	/**
	 * 契約種別詳細
	 */
	@Parameter(description = "契約種別詳細", required = false)
	@Schema(description = "契約種別詳細<br />" //
			+ "選択したライセンスに紐づく契約種別詳細を表す。", //
			required = false)
	private String contractTypeDetails;

	/**
	 * 契約ステータス
	 */
	@Parameter(description = "契約ステータス:カンマ区切りで複数指定可", required = false)
	@Schema(description = "契約ステータス<br />" //
			+ "状態遷移上のワークフローステータスを表す。", //
			required = false)
	private String workflowStatus;

	/**
	 * 契約状態
	 */
	@Parameter(description = "契約状態:カンマ区切りで複数指定可", required = false)
	@Schema(description = "契約状態<br />" //
			+ "状態遷移上のライフサイクル状態を表す。", //
			required = false)
	private String lifecycleStatus;

	/**
	 * キャンセル状態
	 */
	@Parameter(description = "キャンセル状態", required = false)
	@Schema(description = "キャンセル状態", required = false)
	private String cancelStatus;

	/**
	 * 解約予定日（前）
	 */
	@Parameter(description = "解約予定日（前）", required = false)
	@Schema(description = "解約予定日（前）<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false)
	private Date cancelScheduledDateFrom;

	/**
	 * 解約予定日（後）
	 */
	@Parameter(description = "解約予定日（後）", required = false)
	@Schema(description = "解約予定日（後）<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false)
	private Date cancelScheduledDateTo;

	/**
	 * 工程ステータス
	 */
	@Parameter(description = "工程ステータス", required = false)
	@Schema(description = "工程ステータス<br />" //
			+ "ライセンス検索条件の「工程実施日時」の検索に使用するカラム", //
			required = false, allowableValues = "range[0,255]")
	private String licenseProcessStatusForOperatedDateTime;

	/**
	 * 工程実施日時(年月日)
	 */
	@Parameter(description = "工程実施日時", required = false)
	@Schema(description = "工程実施日時<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false)
	private Date processOperatedDateTime;

	/**
	 * 工程実施日時(時)
	 */
	@Parameter(description = "工程実施日時", required = false)
	@Schema(description = "工程実施日時<br />" //
			+ "日付フォーマット:HH:00", //
			required = false)
	private String processOperatedTime;

	/**
	 * 情報区分
	 */
	@Parameter(description = "情報区分", required = false)
	@Schema(description = "情報区分<br />" //
			+ "アカウント単位での情報を表す。", //
			required = false)
	private String infoDiv;

	// =========================== 以下、ライセンス詳細検索条件
	/**
	 * 受付状況フラグ
	 */
	@Parameter(description = "受付状況フラグ", required = false)
	@Schema(description = "受付状況フラグ", required = false)
	private Integer receptionStatusFlg;

	/**
	 * CSV出力日時
	 */
	@Parameter(description = "CSV出力日時", required = false)
	@Schema(description = "CSV出力日時<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false)
	private Date csvOutputAt;

	/**
	 * RMA契約番号
	 */
	@Parameter(description = "RMA契約番号", required = false)
	@Schema(description = "RMA契約番号", required = false, allowableValues = "range[0,255]")
	private String rmaContractNumber;

	/**
	 * 割当区分
	 */
	@Parameter(description = "割当区分", required = false)
	@Schema(description = "割当区分", required = false)
	private Integer allocationDiv;

	/**
	 * 送信結果区分
	 */
	@Parameter(description = "送信結果区分", required = false)
	@Schema(description = "送信結果区分", required = false)
	private Integer mailSendResultDiv;

	/**
	 * 申込日
	 */
	@Parameter(description = "申込日", required = false)
	@Schema(description = "申込日<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false)
	private Date applicationDateAt;

	/**
	 * Welcomeメール送信状況
	 */
	@Parameter(description = "Welcomeメール送信状況", required = false)
	@Schema(description = "Welcomeメール送信状況", required = false)
	private Integer wellcomeMailSendResultDiv;

	/**
	 * 導入代行
	 */
	@Parameter(description = "導入代行", required = false)
	@Schema(description = "導入代行", required = false)
	private Integer intActingDiv;

	/**
	 * MerakiスマートサービスオーガニゼーションID
	 */
	@Parameter(description = "MerakiスマートサービスオーガニゼーションID", required = false)
	@Schema(description = "MerakiスマートサービスオーガニゼーションID", required = false)
	private String mssLinkageOrganizationId;

	/**
	 * FFM発注問合せ番号
	 */
	@Parameter(description = "FFM発注問合せ番号", required = false)
	@Schema(description = "FFM発注問合せ番号", required = false)
	private String contactNo;

	/**
	 * サブドメイン
	 */
	@Parameter(description = "サブドメイン", required = false)
	@Schema(description = "サブドメイン", required = false)
	private String subDomainName;

	/**
	 * ベンダー区分
	 */
	@Parameter(description = "ベンダー区分", required = false)
	@Schema(description = "ベンダー区分", required = false)
	private String vendorDiv;

	/**
	 * 端末電話番号
	 */
	@Parameter(description = "端末電話番号", required = false)
	@Schema(description = "端末電話番号", required = false)
	private String phoneNumber;
}
