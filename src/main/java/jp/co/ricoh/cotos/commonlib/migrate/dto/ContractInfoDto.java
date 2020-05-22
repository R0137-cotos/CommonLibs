package jp.co.ricoh.cotos.commonlib.migrate.dto;

import lombok.Data;

@Data
public class ContractInfoDto {

	/**
	 * オプション識別フラグ
	 */
	private String optionFlg;

	/**
	 * 親オプション識別フラグ
	 */
	private String parentOptionProductCd;

	/**
	 * サービス開始日
	 */
	private String serviceStartDate;

	/**
	 * サービス終了日
	 */
	private String serviceEndDate;

	/**
	 * サービスコード
	 */
	private String serviceCd;

	/**
	 * サービス名
	 */
	private String serviceNm;

	/**
	 * サービス提供会社名
	 */
	private String serviceCorpNm;

	/**
	 * 商品コード
	 */
	private String productCd;

	/**
	 * 商品名
	 */
	private String productNm;

	/**
	 * 提供方法
	 */
	private String provideMethod;

	/**
	 * 現在の数量
	 */
	private String presentAmt;

	/**
	 * 減数予定数
	 */
	private String expectMinusAmt;

	/**
	 * 変更前数量
	 */
	private String amtBeforeChange;

	/**
	 * 売価単価
	 */
	private String unitPrice;
}
