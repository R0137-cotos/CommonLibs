package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import lombok.Data;

/**
 * BPN SACM事前設定情報取得API レスポンスのSACM手配業務のDTO
 */

@Data
public class SacmArrangementWorkDto {

	/**
	 * 手配業務ID
	 */
	private String arrangementWorkId;

	/**
	 * 文書番号
	 */
	private String estimationNumber;

	/**
	 * 契約ID
	 */
	private String rjManageNumber;

	/**
	 * CSV取込日
	 */
	private String csvImportDate;

	/**
	 * コンフィグファイル名
	 */
	private String configFileName;

	/**
	 * LaITコンフィグデータリスト
	 */
	private List<LaitConfigDataDto> laitConfigDataList;

}