package jp.co.ricoh.cotos.commonlib.migrate.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ContractorInfoDto {

	/**ID*/
	private long id;

	/**基本情報*/
	@ManyToOne(optional = false)
	@JoinColumn(name = "basic_contents_id", referencedColumnName = "id")
	@JsonIgnore
	private BasicContentsDto basicContents;

	/**
	 * 企業ID
	 */
	private String corporationId;

	/**
	 * 企事部ID
	 */
	private String corpId;

	/**
	 * 得意先コード
	 */
	private String customerCd;

	/**
	 * 契約者情報会社名
	 */
	private String contractorCorpNm;

	/**
	 * 契約者情報漢字（姓＋名）
	 */
	private String contractorNameKanji;

	/**
	 * 契約者情報カナ（姓＋名）
	 */
	private String contractorNameKana;

	/**
	 * 契約者情報メールアドレス
	 */
	private String contractorMail;

	/**
	 * 契約者情報郵便番号
	 */
	private String contractorPostCd;

	/**
	 * 契約者情報事業所名
	 */
	private String contractorOfficeNm;

	/**
	 * 契約者情報住所
	 */
	private String contractorAddr;

	/**
	 * 契約者情報電話番号
	 */
	private String contractorTel;
}
