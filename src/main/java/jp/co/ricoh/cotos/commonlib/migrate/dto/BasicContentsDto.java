package jp.co.ricoh.cotos.commonlib.migrate.dto;

import lombok.Data;

@Data
public class BasicContentsDto {

	/**
	 * 対象システム
	 */
	private String targetSys;

	/**
	 * bplats契約情報キー１
	 */
	private String bplatsContractInfoKey1;

	/**
	 * bplats契約情報キー２
	 */
	private String bplatsContractInfoKey2;

	/**
	 * bplats契約情報キー３
	 */
	private String bplatsContractInfoKey3;

	/**
	 * bplats契約情報キー４
	 */
	private String bplatsContractInfoKey4;

	/**
	 * bplats契約情報キー５
	 */
	private String bplatsContractInfoKey5;

	/**
	 * bplats注文番号
	 */
	private String bplatsRequestNo;

	/**
	 * メーカー管理番号
	 */
	private String makerManageNo;

	/**
	 * 契約名称サービス名
	 */
	private String contractNm;

	/**
	 * サービス開始日
	 */
	private String serviceStartDate;

	/**
	 * サービス終了日
	 */
	private String serviceEndDate;

	/**
	 * 解約予定日
	 */
	private String cancelExpectDate;

	/**
	 * 契約ステータス
	 */
	private String contractStatus;

	/**
	 * 契約作成日
	 */
	private String contractCreateDate;

	/**
	 * 更新期間
	 */
	private String updateInterval;

	/**
	 * 課金開始日
	 */
	private String paymentStartDate;

	/**
	 * 商流区分
	 */
	private String commercialDistribute;

	/**
	 * NetRicoh会員ID
	 */
	private String netricohAccount;

}
