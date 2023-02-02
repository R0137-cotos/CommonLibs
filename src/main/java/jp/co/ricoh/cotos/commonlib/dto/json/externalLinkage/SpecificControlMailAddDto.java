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
	private String no;

	/**
	 * 削除
	 */
	private String delete;

	/**
	 * 申請状態
	 */
	private String requestStatus;

	/**
	 * 申請前メールアドレス
	 */
	private String preMail;

	/**
	 * 第一希望メールアドレス
	 */
	private String firstHope;

	/**
	 * 第一希望（再入力）
	 */
	private String firstHopeReEnter;

	/**
	 * 第一希望（フリガナ）
	 */
	private String firstHopePhonetic;

	/**
	 * 第二希望メールアドレス
	 */
	private String secondHope;

	/**
	 * 第二希望（再入力）
	 */
	private String secondHopeReEnter;

	/**
	 * 第二希望（フリガナ）
	 */
	private String secondHopePhonetic;

	/**
	 * パスワード
	 */
	private String password;

	/**
	 * パスワード（再入力）
	 */
	private String passwordReEnter;

	/**
	 * キャンセル流用文書番号
	 */
	private String ispDiversionContractNumber;

	/**
	 * キャンセル流用
	 */
	private String diversion;
}
