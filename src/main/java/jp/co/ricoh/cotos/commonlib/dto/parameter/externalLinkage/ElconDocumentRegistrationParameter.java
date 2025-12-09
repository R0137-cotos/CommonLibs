package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 電子契約EIM文書登録APIパラメータDto
 */

@Data
public class ElconDocumentRegistrationParameter {

	/**
	 * ファイル名
	 */
	@Parameter(description = "ファイル名")
	@Schema(description = "ファイル名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String fileName;

	/**
	 * 対象pdf
	 */
	@Parameter(description = "対象pdf")
	@Schema(description = "対象pdf", requiredMode = Schema.RequiredMode.REQUIRED)
	private byte[] targetPdf;

	/**
	 * vup契約No
	 */
	@Parameter(description = "vup契約No")
	@Schema(description = "vup契約No", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String vupContractNo;

	/**
	 * vup見積No
	 */
	@Parameter(description = "vup見積No")
	@Schema(description = "vup見積No", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String vupEstimatesNo;

	/**
	 * 案件名
	 */
	@Parameter(description = "案件名")
	@Schema(description = "案件名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String anknMi;

	/**
	 * 案件番号
	 */
	@Parameter(description = "案件番号")
	@Schema(description = "案件番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String anknNumber;

	/**
	 * 販社CD
	 */
	@Parameter(description = "販社CD")
	@Schema(description = "販社CD", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String hanshaCd;

	/**
	 * 担当SA統合ID
	 */
	@Parameter(description = "担当SA統合ID")
	@Schema(description = "担当SA統合ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String saId;

	/**
	 * お客様コード
	 */
	@Parameter(description = "お客様コード")
	@Schema(description = "お客様コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String customerCode;

	/**
	 * 企業名
	 */
	@Parameter(description = "企業名")
	@Schema(description = "企業名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String customerName;

	/**
	 * お客様事業所コード
	 */
	@Parameter(description = "お客様事業所コード")
	@Schema(description = "お客様事業所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String customerOfficeCode;

	/**
	 * IM管理番号
	 */
	@Parameter(description = "IM管理番号")
	@Schema(description = "IM管理番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String imfrSdInsertId;

	/**
	 * IM行番号
	 */
	@Parameter(description = "IM行番号")
	@Schema(description = "IM行番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String imfrSdRowNo;

	/**
	 * 初期費用総計
	 */
	@Parameter(description = "初期費用総計")
	@Schema(description = "初期費用総計", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String totalInitialCosts;

	/**
	 * 月額総計
	 */
	@Parameter(description = "月額総計")
	@Schema(description = "月額総計", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String monthlyTotal;

	/**
	 * 年額総計
	 */
	@Parameter(description = "年額総計")
	@Schema(description = "年額総計", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String annualTotal;

	/**
	 * 正式帳票名
	 */
	@Parameter(description = "正式帳票名")
	@Schema(description = "正式帳票名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String reportName;

	/**
	 * 利用開始希望日入力制御フラグ
	 */
	@Parameter(description = "利用開始希望日入力制御フラグ")
	@Schema(description = "利用開始希望日入力制御フラグ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String startDatePrintFlag;

	/**
	 * 担当者印字フラグ
	 */
	@Parameter(description = "担当者印字フラグ")
	@Schema(description = "担当者印字フラグ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String customerPrintFlag;

	/**
	 * 見積番号
	 */
	@Parameter(description = "見積番号")
	@Schema(description = "見積番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String estimationNumber;

	/**
	 * 見積番号枝番
	 */
	@Parameter(description = "見積番号枝番")
	@Schema(description = "見積番号枝番", requiredMode = Schema.RequiredMode.REQUIRED)
	private String estimationBranchNumber;

	/**
	 * 見積件名
	 */
	@Parameter(description = "見積件名")
	@Schema(description = "見積件名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String estimationTitle;

	/**
	 * 見積ID
	 */
	@Parameter(description = "見積ID")
	@Schema(description = "見積ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long estimationId;
}

