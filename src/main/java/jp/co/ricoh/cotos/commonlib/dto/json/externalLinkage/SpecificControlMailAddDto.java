package jp.co.ricoh.cotos.commonlib.dto.json.externalLinkage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商材固有項目用拡張項目DTO（メールアドレス追加詳細）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecificControlMailAddDto {

	/**
	 * No
	 */
	private String mailAddNoList;

	/**
	 * 削除
	 */
	private String mailAddDeleteList;

	/**
	 * 申請状態
	 */
	private String mailAddRequestStatusList;

	/**
	 * 申請前メールアドレス
	 */
	private String mailAddPreMailList;

	/**
	 * 第一希望メールアドレス
	 */
	private String mailAddFirstHopeList;

	/**
	 * 第一希望（再入力）
	 */
	private String mailAddFirstHopeReEnterList;

	/**
	 * 第一希望（フリガナ）
	 */
	private String mailAddFirstHopePhoneticList;

	/**
	 * 第二希望メールアドレス
	 */
	private String mailAddSecondHopeList;

	/**
	 * 第二希望（再入力）
	 */
	private String mailAddSecondHopeReEnterList;

	/**
	 * 第二希望（フリガナ）
	 */
	private String mailAddSecondHopePhoneticList;

	/**
	 * パスワード
	 */
	private String mailAddPasswordList;

	/**
	 * パスワード（再入力）
	 */
	private String mailAddPasswordReEnterList;

	/**
	 * キャンセル流用文書番号
	 */
	private String mailAddDiversionContractNumberList;

	/**
	 * キャンセル流用
	 */
	private String mailAddDiversionList;

	/**
	 * 旧メールアドレス併用希望
	 */
	private String mailAddConbinedUseList;

	/**
	 * 変更希望日
	 */
	private String mailAddChangeHopeDateList;

	/**
	 * 変更希望時間帯
	 */
	private String mailAddChangeHopeTimeList;
}
