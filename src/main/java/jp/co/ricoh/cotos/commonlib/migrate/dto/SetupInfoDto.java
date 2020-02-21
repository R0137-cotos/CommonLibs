package jp.co.ricoh.cotos.commonlib.migrate.dto;

import lombok.Data;

@Data
public class SetupInfoDto {

	/**
	 * 会社名
	 */
	private String setupCorpNm;

	/**
	 * 漢字（姓＋名）
	 */
	private String setupNameKanji;

	/**
	 * カナ（姓＋名）
	 */
	private String setupNameKana;

	/**
	 * メールアドレス
	 */
	private String setupMail;

	/**
	 * 郵便番号
	 */
	private String setupPostCd;

	/**
	 * 事業所名
	 */
	private String setupOfficeNm;

	/**
	 * 住所
	 */
	private String setupAddr;

	/**
	 * 電話番号
	 */
	private String setupTel;
}
