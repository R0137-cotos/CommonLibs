package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
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
	@ApiParam(value = "ライセンス検索区分", required = false)
	@ApiModelProperty(value = "ライセンス検索区分", required = false, allowableValues = "通常検索(\"1\"), 追加検索(\"2\"), 新規検索(\"3\"), 保守延長検索(\"4\"), 開通確認検索(\"5\"), 解約検索(\"6\")", position = 1)
	private LicenseSearchDiv licenseSearchDiv;

	/**
	 * ライセンス区分マスタID
	 */
	@ApiParam(value = "ライセンス区分マスタID", required = false)
	@ApiModelProperty(value = "ライセンス区分マスタID", required = false, allowableValues = "range[0,255]", position = 2)
	private String licenseDivMasterId;

	/**
	 * RJ管理番号
	 */
	@ApiParam(value = "RJ管理番号", required = false)
	@ApiModelProperty(value = "RJ管理番号", required = false, allowableValues = "range[0,255]", position = 3)
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@ApiParam(value = "契約番号", required = false)
	@ApiModelProperty(value = "契約番号", required = false, allowableValues = "range[0,15]", position = 4)
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@ApiParam(value = "契約番号枝番", required = false)
	@ApiModelProperty(value = "契約番号枝番", required = false, allowableValues = "range[0,2]", position = 5)
	private String contractBranchNumber;

	/**
	 * オーガニゼーションID
	 */
	@ApiParam(value = "オーガニゼーションID", required = false)
	@ApiModelProperty(value = "オーガニゼーションID", required = false, allowableValues = "range[0,255]", position = 6)
	private String organizationId;

	/**
	 * ライセンスキー
	 */
	@ApiParam(value = "ライセンスキー", required = false)
	@ApiModelProperty(value = "ライセンスキー", required = false, allowableValues = "range[0,255]", position = 7)
	private String licenseKey;

	/**
	 * アカウント
	 */
	@ApiParam(value = "アカウント", required = false)
	@ApiModelProperty(value = "アカウント", required = false, allowableValues = "range[0,255]", position = 8)
	private String account;

	/**
	 * 工程ステータス
	 */
	@ApiParam(value = "工程ステータス", required = false)
	@ApiModelProperty(value = "工程ステータス<br />" //
			+ "ライセンス検索条件の「工程ステータス」の検索に使用するカラム", //
			required = false, allowableValues = "range[0,255]", position = 9)
	private String licenseProcessStatus;

	/**
	 * サービス利用希望日（前）
	 */
	@ApiParam(value = "サービス利用希望日（前）", required = false)
	@ApiModelProperty(value = "サービス利用希望日（前）<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false, position = 10)
	private Date conclusionPreferredDateFrom;

	/**
	 * サービス利用希望日（後）
	 */
	@ApiParam(value = "サービス利用希望日（後）", required = false)
	@ApiModelProperty(value = "サービス利用希望日（後）<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false, position = 11)
	private Date conclusionPreferredDateTo;

	/**
	 * 契約種別
	 */
	@ApiParam(value = "契約種別", required = false)
	@ApiModelProperty(value = "契約種別<br />" //
			+ "新規、契約変更、情報変更、契約更新などの契約種別を表す。", //
			required = false, position = 12)
	private String contractType;

	/**
	 * 契約種別詳細
	 */
	@ApiParam(value = "契約種別詳細", required = false)
	@ApiModelProperty(value = "契約種別詳細<br />" //
			+ "選択したライセンスに紐づく契約種別詳細を表す。", //
			required = false, position = 13)
	private String contractTypeDetails;

	/**
	 * 契約ステータス
	 */
	@ApiParam(value = "契約ステータス:カンマ区切りで複数指定可", required = false)
	@ApiModelProperty(value = "契約ステータス<br />" //
			+ "状態遷移上のワークフローステータスを表す。", //
			required = false, position = 14)
	private String workflowStatus;

	/**
	 * 契約状態
	 */
	@ApiParam(value = "契約状態:カンマ区切りで複数指定可", required = false)
	@ApiModelProperty(value = "契約状態<br />" //
			+ "状態遷移上のライフサイクル状態を表す。", //
			required = false, position = 15)
	private String lifecycleStatus;

	/**
	 * キャンセル状態
	 */
	@ApiParam(value = "キャンセル状態", required = false)
	@ApiModelProperty(value = "キャンセル状態", required = false, position = 16)
	private String cancelStatus;

	/**
	 * 解約予定日（前）
	 */
	@ApiParam(value = "解約予定日（前）", required = false)
	@ApiModelProperty(value = "解約予定日（前）<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false, position = 17)
	private Date cancelScheduledDateFrom;

	/**
	 * 解約予定日（後）
	 */
	@ApiParam(value = "解約予定日（後）", required = false)
	@ApiModelProperty(value = "解約予定日（後）<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false, position = 18)
	private Date cancelScheduledDateTo;

	/**
	 * 工程ステータス
	 */
	@ApiParam(value = "工程ステータス", required = false)
	@ApiModelProperty(value = "工程ステータス<br />" //
			+ "ライセンス検索条件の「工程実施日時」の検索に使用するカラム", //
			required = false, allowableValues = "range[0,255]", position = 19)
	private String licenseProcessStatusForOperatedDateTime;

	/**
	 * 工程実施日時
	 */
	@ApiParam(value = "工程実施日時", required = false)
	@ApiModelProperty(value = "工程実施日時<br />" //
			+ "日付フォーマット:yyyy/MM/dd HH", //
			required = false, position = 20)
	private Date processOperatedDateTime;

	/**
	 * 情報区分
	 */
	@ApiParam(value = "情報区分", required = false)
	@ApiModelProperty(value = "情報区分<br />" //
			+ "アカウント単位での情報を表す。", //
			required = false, position = 21)
	private String infoDiv;

	// =========================== 以下、ライセンス詳細検索条件
	/**
	 * 受付状況フラグ
	 */
	@ApiParam(value = "受付状況フラグ", required = false)
	@ApiModelProperty(value = "受付状況フラグ", required = false, position = 22)
	private Integer receptionStatusFlg;

	/**
	 * CSV出力日時
	 */
	@ApiParam(value = "CSV出力日時", required = false)
	@ApiModelProperty(value = "CSV出力日時<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false, position = 23)
	private Date csvOutputAt;

	/**
	 * RMA契約番号
	 */
	@ApiParam(value = "RMA契約番号", required = false)
	@ApiModelProperty(value = "RMA契約番号", required = false, allowableValues = "range[0,255]", position = 24)
	private String rmaContractNumber;

	/**
	 * 割当区分
	 */
	@ApiParam(value = "割当区分", required = false)
	@ApiModelProperty(value = "割当区分", required = false, position = 25)
	private Integer allocationDiv;

	/**
	 * 送信結果区分
	 */
	@ApiParam(value = "送信結果区分", required = false)
	@ApiModelProperty(value = "送信結果区分", required = false, position = 26)
	private Integer mailSendResultDiv;

	/**
	 * 申込日
	 */
	@ApiParam(value = "申込日", required = false)
	@ApiModelProperty(value = "申込日<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false, position = 27)
	private Date applicationDateAt;

	/**
	 * Welcomeメール送信状況
	 */
	@ApiParam(value = "Welcomeメール送信状況", required = false)
	@ApiModelProperty(value = "Welcomeメール送信状況", required = false, position = 28)
	private Integer wellcomeMailSendResultDiv;

	/**
	 * 導入代行
	 */
	@ApiParam(value = "導入代行", required = false)
	@ApiModelProperty(value = "導入代行", required = false, position = 29)
	private Integer intActingDiv;

	/**
	 * MerakiスマートサービスオーガニゼーションID
	 */
	@ApiParam(value = "MerakiスマートサービスオーガニゼーションID", required = false)
	@ApiModelProperty(value = "MerakiスマートサービスオーガニゼーションID", required = false, position = 30)
	private String mssLinkageOrganizationId;

}
