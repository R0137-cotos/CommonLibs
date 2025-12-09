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
	@Parameter(description = "ファイル名", required = true)
	@Schema(description = "ファイル名", required = true, allowableValues = "range[0,255]")
	private String fileName;

	/**
	 * 対象pdf
	 */
	@Parameter(description = "対象pdf", required = true)
	@Schema(description = "対象pdf", required = true)
	private byte[] targetPdf;

	/**
	 * vup契約No
	 */
	@Parameter(description = "vup契約No", required = true)
	@Schema(description = "vup契約No", required = true, allowableValues = "range[0,255]")
	private String vupContractNo;

	/**
	 * vup見積No
	 */
	@Parameter(description = "vup見積No", required = true)
	@Schema(description = "vup見積No", required = true, allowableValues = "range[0,255]")
	private String vupEstimatesNo;

	/**
	 * 案件名
	 */
	@Parameter(description = "案件名", required = true)
	@Schema(description = "案件名", required = true, allowableValues = "range[0,255]")
	private String anknMi;

	/**
	 * 案件番号
	 */
	@Parameter(description = "案件番号", required = false)
	@Schema(description = "案件番号", required = false, allowableValues = "range[0,255]")
	private String anknNumber;

	/**
	 * 販社CD
	 */
	@Parameter(description = "販社CD", required = true)
	@Schema(description = "販社CD", required = true, allowableValues = "range[0,255]")
	private String hanshaCd;

	/**
	 * 担当SA統合ID
	 */
	@Parameter(description = "担当SA統合ID", required = true)
	@Schema(description = "担当SA統合ID", required = true, allowableValues = "range[0,255]")
	private String saId;

	/**
	 * お客様コード
	 */
	@Parameter(description = "お客様コード", required = false)
	@Schema(description = "お客様コード", required = false, allowableValues = "range[0,255]")
	private String customerCode;

	/**
	 * 企業名
	 */
	@Parameter(description = "企業名", required = true)
	@Schema(description = "企業名", required = true, allowableValues = "range[0,255]")
	private String customerName;

	/**
	 * お客様事業所コード
	 */
	@Parameter(description = "お客様事業所コード", required = false)
	@Schema(description = "お客様事業所コード", required = false, allowableValues = "range[0,255]")
	private String customerOfficeCode;

	/**
	 * IM管理番号
	 */
	@Parameter(description = "IM管理番号", required = true)
	@Schema(description = "IM管理番号", required = true, allowableValues = "range[0,255]")
	private String imfrSdInsertId;

	/**
	 * IM行番号
	 */
	@Parameter(description = "IM行番号", required = true)
	@Schema(description = "IM行番号", required = true, allowableValues = "range[0,255]")
	private String imfrSdRowNo;

	/**
	 * 初期費用総計
	 */
	@Parameter(description = "初期費用総計", required = false)
	@Schema(description = "初期費用総計", required = false)
	private String totalInitialCosts;

	/**
	 * 月額総計
	 */
	@Parameter(description = "月額総計", required = false)
	@Schema(description = "月額総計", required = false)
	private String monthlyTotal;

	/**
	 * 年額総計
	 */
	@Parameter(description = "年額総計", required = false)
	@Schema(description = "年額総計", required = false)
	private String annualTotal;

	/**
	 * 正式帳票名
	 */
	@Parameter(description = "正式帳票名", required = false)
	@Schema(description = "正式帳票名", required = false, allowableValues = "range[0,255]")
	private String reportName;

	/**
	 * 利用開始希望日入力制御フラグ
	 */
	@Parameter(description = "利用開始希望日入力制御フラグ", required = true)
	@Schema(description = "利用開始希望日入力制御フラグ", required = true, allowableValues = "range[0,255]")
	private String startDatePrintFlag;

	/**
	 * 担当者印字フラグ
	 */
	@Parameter(description = "担当者印字フラグ", required = true)
	@Schema(description = "担当者印字フラグ", required = true, allowableValues = "range[0,255]")
	private String customerPrintFlag;

	/**
	 * 見積番号
	 */
	@Parameter(description = "見積番号", required = true)
	@Schema(description = "見積番号", required = true, allowableValues = "range[0,255]")
	private String estimationNumber;

	/**
	 * 見積番号枝番
	 */
	@Parameter(description = "見積番号枝番", required = true)
	@Schema(description = "見積番号枝番", required = true)
	private String estimationBranchNumber;

	/**
	 * 見積件名
	 */
	@Parameter(description = "見積件名", required = false)
	@Schema(description = "見積件名", required = false)
	private String estimationTitle;

	/**
	 * 見積ID
	 */
	@Parameter(description = "見積ID", required = false)
	@Schema(description = "見積ID", required = false)
	private Long estimationId;
}

