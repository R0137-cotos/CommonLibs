package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests;

import lombok.Data;

/**
 * 文書登録（COTOS申込書）のリクエストボディ
 */

@Data
public class PostCotosDocumentRequest {

	/**
	 * system
	 */
	private PostCotosDocumentRequestSystem system;

	/**
	 * properties
	 */
	private PostCotosDocumentRequestProperties properties;

	/**
	 * vup契約No
	 */
	private String vupContractNo;

	/**
	 * vup見積No
	 */
	private String vupEstimatesNo;

	/**
	 * 案件名
	 */
	private String anknMi;

	/**
	 * QR書誌情報
	 */
	private PostCotosDocumentRequestBibliography bibliography;

	/**
	 * 販社CD
	 */
	private String hanshaCd;

	/**
	 * 担当SA統合ID
	 */
	private String saId;

	/**
	 * お客様コード
	 */
	private String customerCode;

	/**
	 * 企業名
	 */
	private String customerName;

	/**
	 * お客様事業所コード
	 */
	private String customerOfficeCode;

	/**
	 * IM管理番号
	 */
	private String imfrSdInsertId;

	/**
	 * IM行番号
	 */
	private String imfrSdRowNo;

	/**
	 * 初期費用総計
	 */
	private String totalInitialCosts;

	/**
	 * 月額総計
	 */
	private String monthlyTotal;

	/**
	 * 年額総計
	 */
	private String annualTotal;

	/**
	 * 正式帳票名
	 */
	private String reportName;

	/**
	 * 利用開始希望日入力制御フラグ
	 */
	private String startDatePrintFlag;

	/**
	 * 担当者印字フラグ
	 */
	private String customerPrintFlag;

	/**
	 * 削除フラグ
	 */
	private String deleteFlag;
}
