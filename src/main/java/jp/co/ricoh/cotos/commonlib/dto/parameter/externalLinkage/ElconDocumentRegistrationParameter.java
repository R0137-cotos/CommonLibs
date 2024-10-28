package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * 電子契約EIM文書登録APIパラメータDto
 */

@Data
public class ElconDocumentRegistrationParameter {

	/**
	 * ファイル名
	 */
	@NotNull
	@ApiParam(value = "ファイル名", required = true)
	@ApiModelProperty(value = "ファイル名", required = true, allowableValues = "range[0,255]")
	private String fileName;

	/**
	 * 対象pdf
	 */
	@NotNull
	@ApiParam(value = "対象pdf", required = true)
	@ApiModelProperty(value = "対象pdf", required = true)
	private byte[] targetPdf;

	/**
	 * vup契約No
	 */
	@NotNull
	@ApiParam(value = "vup契約No", required = true)
	@ApiModelProperty(value = "vup契約No", required = true, allowableValues = "range[0,255]")
	private String vupContractNo;

	/**
	 * vup見積No
	 */
	@NotNull
	@ApiParam(value = "vup見積No", required = true)
	@ApiModelProperty(value = "vup見積No", required = true, allowableValues = "range[0,255]")
	private String vupEstimatesNo;

	/**
	 * 案件名
	 */
	@NotNull
	@ApiParam(value = "案件名", required = true)
	@ApiModelProperty(value = "案件名", required = true, allowableValues = "range[0,255]")
	private String anknMi;

	/**
	 * 販社CD
	 */
	@NotNull
	@ApiParam(value = "販社CD", required = true)
	@ApiModelProperty(value = "販社CD", required = true, allowableValues = "range[0,255]")
	private String hanshaCd;

	/**
	 * 担当SA統合ID
	 */
	@NotNull
	@ApiParam(value = "担当SA統合ID", required = true)
	@ApiModelProperty(value = "担当SA統合ID", required = true, allowableValues = "range[0,255]")
	private String saId;

	/**
	 * お客様コード
	 */
	@ApiParam(value = "お客様コード", required = false)
	@ApiModelProperty(value = "お客様コード", required = false, allowableValues = "range[0,255]")
	private String customerCode;

	/**
	 * 企業名
	 */
	@NotNull
	@ApiParam(value = "企業名", required = true)
	@ApiModelProperty(value = "企業名", required = true, allowableValues = "range[0,255]")
	private String customerName;

	/**
	 * お客様事業所コード
	 */
	@ApiParam(value = "お客様事業所コード", required = false)
	@ApiModelProperty(value = "お客様事業所コード", required = false, allowableValues = "range[0,255]")
	private String customerOfficeCode;

	/**
	 * IM管理番号
	 */
	@NotNull
	@ApiParam(value = "IM管理番号", required = true)
	@ApiModelProperty(value = "IM管理番号", required = true, allowableValues = "range[0,255]")
	private String imfrSdInsertId;

	/**
	 * IM行番号
	 */
	@NotNull
	@ApiParam(value = "IM行番号", required = true)
	@ApiModelProperty(value = "IM行番号", required = true, allowableValues = "range[0,255]")
	private String imfrSdRowNo;

	/**
	 * 初期費用総計
	 */
	@ApiParam(value = "初期費用総計", required = false)
	@ApiModelProperty(value = "初期費用総計", required = false)
	private String totalInitialCosts;

	/**
	 * 月額総計
	 */
	@ApiParam(value = "月額総計", required = false)
	@ApiModelProperty(value = "月額総計", required = false)
	private String monthlyTotal;

	/**
	 * 年額総計
	 */
	@ApiParam(value = "年額総計", required = false)
	@ApiModelProperty(value = "年額総計", required = false)
	private String annualTotal;

	/**
	 * 正式帳票名
	 */
	@ApiParam(value = "正式帳票名", required = false)
	@ApiModelProperty(value = "正式帳票名", required = false, allowableValues = "range[0,255]")
	private String reportName;

	/**
	 * 利用開始希望日入力制御フラグ
	 */
	@NotNull
	@ApiParam(value = "利用開始希望日入力制御フラグ", required = true)
	@ApiModelProperty(value = "利用開始希望日入力制御フラグ", required = true, allowableValues = "range[0,255]")
	private String startDatePrintFlag;

	/**
	 * 担当者印字フラグ
	 */
	@NotNull
	@ApiParam(value = "担当者印字フラグ", required = true)
	@ApiModelProperty(value = "担当者印字フラグ", required = true, allowableValues = "range[0,255]")
	private String customerPrintFlag;

}

