package jp.co.ricoh.cotos.commonlib.dto.parameter.reports;

import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 帳票一覧パラメーター
 */
@Data
public class ReportListParameter {

	/**
	 * 出力形式
	 */
	@Schema(description = "出力形式", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "PDF(\"1\"), Excel(\"2\")", example = "1")
	private String outputType;

	/**
	 * サービスカテゴリ
	 */
	@Schema(description = "サービスカテゴリ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "見積(\"1\"), 契約(\"2\"), 手配(\3\")", example = "1")
	private String serviceCategory;

	/**
	 * 対象文書ID
	 */
	@Size(max = 255)
	@Schema(description = "対象文書ID" //
			+ "見積：見積ID<br />" //
			+ "契約：契約ID<br />" //
			+ "手配：手配業務ID<br />", required = false, allowableValues = "range[0,255]")
	private String targetDocId;

	/**
	 * 対象種別
	 */
	@Schema(description = "対象種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 解約(\"4\"), 契約更新(\"5\")", example = "1")
	private String targetType;

	/**
	 * 商流区分
	 */
	@Size(max = 255)
	@Schema(description = "商流区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String commercialFlowDiv;

	/**
	 * 商品マスタID
	 */
	@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long productMasterId;

	/**
	 * ライフサイクル状態
	 */
	@Schema(description = "ライフサイクル状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), 受注(\"3\"), 失注(\"4\"), 破棄(\"5\"), キャンセル手続き中(\"6\"), 予定日待ち(\"7\"), 締結中(\"8\"), 解約手続き中(\"9\"), 解約予定日待ち(\"10\"), 解約(\"11\"), 旧契約(\"12\"), 締結待ち(\"13\")", example = "1")
	private String lifecycleStatus;

	/**
	 * ワークフロー状態
	 */
	@Schema(description = "ワークフロー状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "作成中(\"1\"), 業務依頼中(\"2\"), 業務処理完了(\"3\"), 承認依頼中(\"4\"), 承認済(\"5\"), 顧客提示済(\"6\"), キャンセル申請中(\"7\"), 売上可能(\"8\"), 解約申請中(\"9\"), 手配中(\"10\"), 手配完了(\"11\"), 受付待ち(\"12\"), 作業中(\"13\"), 作業完了報告(\"14\"), 作業完了(\"15\"), エラー(\"16\")", example = "1")
	private String workflowStatus;
	
	/**
	 * 電子契約連携対象帳票取得区分
	 */
	@Schema(description = "電子契約連携対象帳票取得区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "電子契約連携対象以外(\"0\"), 電子契約連携対象のみ(\"1\"), 全て(\"2\")", example = "0")
	private String electronicContractLinkageReportDiv;
}