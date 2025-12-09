package jp.co.ricoh.cotos.commonlib.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cotos.roc")
public class RocProperties {

	/**
	 * ROC連携指示タイプ区分
	 */
	String linkageInstructionsTypeDiv;

	/**
	 * サイボウズ連携API手配業務タイプ区分
	 */
	String linkageIToCybozuTypeDiv;

	/**
	 * メール連携タイプ区分
	 */
	String mailToVendorTypeDiv;

	/**
	 * ベンダー回答入力タイプ区分
	 */
	String inputVendorAnswerTypeDiv;

	/**
	 * 補足情報入力タイプ区分
	 */
	String inputComplementTypeDiv;

	/**
	 * 拡張項目：商流変更のプロパティ名
	 */
	String changeCommercialFlowPropaty;

	/**
	 * 拡張項目：商流変更=true時のバリュー
	 */
	String changeCommercialFlowTrue;

	/**
	 * 汎用マスタ：サイボウズAPI連携品種情報 カラム名
	 */
	String cybozuLinkageItem;

	/**
	 * 契約添付ファイル種類_新規
	 */
	String contractAttachedFileKindNew;

	/**
	 * 契約添付ファイル種類_契約変更
	 */
	String contractAttachedFileKindChange;

	/**
	 * 契約添付ファイル種類_情報変更
	 */
	String contractAttachedFileKindInfoChange;

	/**
	 * 契約添付ファイル種類_解約
	 */
	String contractAttachedFileKindCancellation;

	/**
	 * 一時設定担当者MoM社員ID
	 */
	String temporaryWorkerMomEmpId;
}
