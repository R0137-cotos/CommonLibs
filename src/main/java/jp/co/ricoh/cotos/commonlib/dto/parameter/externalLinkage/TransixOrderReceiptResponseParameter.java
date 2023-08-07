package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Transixのオーダ事前照会APIのレスポンス用パラメータ
 */
@Data
public class TransixOrderReceiptResponseParameter {
	/**
	 * メソッド名
	 */
	@ApiModelProperty(value = "メソッド名", required = false, position = 1, allowableValues = "range[0,255]")
	private String methodName;

	/**
	 * 申込結果コード
	 */
	@ApiModelProperty(value = "申込結果コード", required = false, position = 2, allowableValues = "range[0,255]")
	private String mfResponseCd;

	/**
	 * 摘要
	 */
	@ApiModelProperty(value = "摘要", required = false, position = 4, allowableValues = "range[0,255]")
	private String description;

	/**
	 * 処理ID （不正リクエスト時のみレスポンスあり）
	 */
	@ApiModelProperty(value = "処理ID （不正リクエスト時）", required = false, position = 5, allowableValues = "range[0,255]")
	private String processId;

	/**
	 * お客様ID
	 */
	@ApiModelProperty(value = "お客様ID", required = false, position = 6, allowableValues = "range[0,255]")
	private String ngnCommonId;

	/**
	 * 受付日時
	 */
	@ApiModelProperty(value = "受付日時", required = false, position = 7, allowableValues = "range[0,255]")
	private String mfReceiptTime;

	/**
	 * 照会データ送信日時
	 */
	@ApiModelProperty(value = "照会データ送信日時", required = false, position = 8, allowableValues = "range[0,255]")
	private String nttSendDate;

	/**
	 * 受付番号
	 */
	@ApiModelProperty(value = "受付番号", required = false, position = 9, allowableValues = "range[0,255]")
	private String receiptNo;

	/**
	 * 処理結果コード
	 */
	@ApiModelProperty(value = "処理結果コード", required = false, position = 10, allowableValues = "range[0,255]")
	private String resultCode;

	/**
	 * フレッツ品目情報報 (AL品目情報)
	 */
	@ApiModelProperty(value = "フレッツ品目情報報 (AL品目情報)", required = false, position = 11, allowableValues = "range[0,255]")
	private String reserved;

	/**
	 * リクエストID（不正リクエスト時のみレスポンスあり）
	 */
	@ApiModelProperty(value = "リクエストID（不正リクエスト時）", required = false, position = 12, allowableValues = "range[0,255]")
	private String requestId;

	/**
	 * 申込結果コード（不正リクエスト時のみレスポンスあり）
	 */
	@ApiModelProperty(value = "申込結果コード（不正リクエスト時）", required = false, position = 13, allowableValues = "range[0,255]")
	private String responseCd;

	/**
	 * 受付日時（不正リクエスト時のみレスポンスあり）
	 */
	@ApiModelProperty(value = "受付日時（不正リクエスト時）", required = false, position = 14, allowableValues = "range[0,255]")
	private String receiptTime;
}
