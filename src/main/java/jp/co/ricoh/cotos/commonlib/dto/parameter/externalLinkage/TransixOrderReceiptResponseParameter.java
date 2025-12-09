package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Transixのオーダ事前照会APIのレスポンス用パラメータ
 */
@Data
public class TransixOrderReceiptResponseParameter {
	/**
	 * メソッド名
	 */
	@Schema(description = "メソッド名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String methodName;

	/**
	 * 申込結果コード
	 */
	@Schema(description = "申込結果コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String mfResponseCd;

	/**
	 * 摘要
	 */
	@Schema(description = "摘要", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String description;

	/**
	 * 処理ID （不正リクエスト時のみレスポンスあり）
	 */
	@Schema(description = "処理ID （不正リクエスト時）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String processId;

	/**
	 * お客様ID
	 */
	@Schema(description = "お客様ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ngnCommonId;

	/**
	 * 受付日時
	 */
	@Schema(description = "受付日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String mfReceiptTime;

	/**
	 * 照会データ送信日時
	 */
	@Schema(description = "照会データ送信日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nttSendDate;

	/**
	 * 受付番号
	 */
	@Schema(description = "受付番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String receiptNo;

	/**
	 * 処理結果コード
	 */
	@Schema(description = "処理結果コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String resultCode;

	/**
	 * フレッツ品目情報報 (AL品目情報)
	 */
	@Schema(description = "フレッツ品目情報報 (AL品目情報)", required = false, allowableValues = "range[0,255]")
	private String reserved;

	/**
	 * リクエストID（不正リクエスト時のみレスポンスあり）
	 */
	@Schema(description = "リクエストID（不正リクエスト時）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String requestId;

	/**
	 * 申込結果コード（不正リクエスト時のみレスポンスあり）
	 */
	@Schema(description = "申込結果コード（不正リクエスト時）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String responseCd;

	/**
	 * 受付日時（不正リクエスト時のみレスポンスあり）
	 */
	@Schema(description = "受付日時（不正リクエスト時）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String receiptTime;
}
