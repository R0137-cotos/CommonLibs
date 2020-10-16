package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * ライセンスを検索するためのキー項目クラスを表します。
 */
@Data
public class LicenseSearchParameter {

	/**
	 * ライセンス
	 */
	@ApiParam(value = "ライセンス", required = false)
	@ApiModelProperty(value = "ライセンス", required = false, allowableValues = "range[0,255]", position = 1)
	private String license;

	/**
	 * RJ管理番号
	 */
	@ApiParam(value = "RJ管理番号", required = false)
	@ApiModelProperty(value = "RJ管理番号", required = false, allowableValues = "range[0,255]", position = 2)
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@ApiParam(value = "契約番号", required = false)
	@ApiModelProperty(value = "契約番号", required = false, allowableValues = "range[0,15]", position = 3)
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@ApiParam(value = "契約番号枝番", required = false)
	@ApiModelProperty(value = "契約番号枝番", required = false, allowableValues = "range[0,2]", position = 4)
	private String contractBranchNumber;

	/**
	 * オーガニゼーションID
	 */
	@ApiParam(value = "オーガニゼーションID", required = false)
	@ApiModelProperty(value = "オーガニゼーションID", required = false, allowableValues = "range[0,255]", position = 5)
	private String organizationId;

	/**
	 * ライセンスキー
	 */
	@ApiParam(value = "ライセンスキー", required = false)
	@ApiModelProperty(value = "ライセンスキー", required = false, allowableValues = "range[0,255]", position = 6)
	private String licenseKsy;

	/**
	 * 工程ステータス
	 */
	@ApiParam(value = "工程ステータス", required = false)
	@ApiModelProperty(value = "工程ステータス", required = false, allowableValues = "range[0,255]", position = 7)
	private String licenseProcessStatus;

	/**
	 * サービス利用希望日（前）
	 */
	@ApiParam(value = "サービス利用希望日（前）", required = false)
	@ApiModelProperty(value = "サービス利用希望日（前）<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false, position = 8)
	private Date conclusionPreferredDateFrom;

	/**
	 * サービス利用希望日（後）
	 */
	@ApiParam(value = "サービス利用希望日（後）", required = false)
	@ApiModelProperty(value = "サービス利用希望日（後）<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false, position = 9)
	private Date conclusionPreferredDateTo;

	/**
	 * 契約種別
	 */
	@ApiParam(value = "契約種別", required = false)
	@ApiModelProperty(value = "契約種別<br />" //
			+ "新規、契約変更、情報変更、契約更新などの契約種別を表す。", //
			required = false, position = 10)
	private String contractType;

	/**
	 * 契約種別詳細
	 */
	@ApiParam(value = "契約種別詳細", required = false)
	@ApiModelProperty(value = "契約種別詳細<br />" //
			+ "選択したライセンスに紐づく契約種別詳細を表す。", //
			required = false, position = 11)
	private String contractTypeDetails;

	/**
	 * 契約ステータス
	 */
	@ApiParam(value = "契約ステータス:カンマ区切りで複数指定可", required = false)
	@ApiModelProperty(value = "契約ステータス<br />" //
			+ "状態遷移上のワークフローステータスを表す。", //
			required = false, position = 12)
	private String workflowStatus;

	/**
	 * 契約状態
	 */
	@ApiParam(value = "契約状態:カンマ区切りで複数指定可", required = false)
	@ApiModelProperty(value = "契約状態<br />" //
			+ "状態遷移上のライフサイクル状態を表す。", //
			required = false, position = 13)
	private String lifecycleStatus;

	/**
	 * キャンセル状態
	 */
	@ApiParam(value = "キャンセル状態", required = false)
	@ApiModelProperty(value = "キャンセル状態", required = false, position = 14)
	private String cancelStatus;

	/**
	 * 解約予定日（前）
	 */
	@ApiParam(value = "解約予定日（前）", required = false)
	@ApiModelProperty(value = "解約予定日（前）<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false, position = 15)
	private Date cancelScheduledDateFrom;

	/**
	 * 解約予定日（後）
	 */
	@ApiParam(value = "解約予定日（後）", required = false)
	@ApiModelProperty(value = "解約予定日（後）<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false, position = 16)
	private Date cancelScheduledDateTo;

	// =========================== 以下、ライセンス詳細検索条件
	/**
	 * 受付状況フラグ
	 */
	@ApiParam(value = "受付状況フラグ", required = false)
	@ApiModelProperty(value = "受付状況フラグ", required = false, position = 17)
	private Integer receptionStatusFlg;

	/**
	 * SS長作業完了承認日時
	 */
	@ApiParam(value = "SS長作業完了承認日時", required = false)
	@ApiModelProperty(value = "SS長作業完了承認日時", required = false, position = 18)
	private Integer ssWorkCompletedApprovalAt;

	/**
	 * CSV出力日時
	 */
	@ApiParam(value = "CSV出力日時", required = false)
	@ApiModelProperty(value = "CSV出力日時<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false, position = 19)
	private Date csvOutputAt;

	/**
	 * 利用期間開始日
	 */
	@ApiParam(value = "利用期間開始日", required = false)
	@ApiModelProperty(value = "利用期間開始日<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false, position = 20)
	private Date usePeriodStart;

	/**
	 * 利用期間終了日
	 */
	@ApiParam(value = "利用期間終了日", required = false)
	@ApiModelProperty(value = "利用期間終了日<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false, position = 21)
	private Date usePeriodEnd;

	/**
	 * RMA契約番号
	 */
	@ApiParam(value = "RMA契約番号", required = false)
	@ApiModelProperty(value = "RMA契約番号", required = false, allowableValues = "range[0,255]", position = 22)
	private String rmaContractNumber;

	/**
	 * 割当区分
	 */
	@ApiParam(value = "割当区分", required = false)
	@ApiModelProperty(value = "割当区分", required = false, position = 23)
	private Integer allocationDiv;
}
