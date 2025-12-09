package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * BPN SACM事前設定情報取得API レスポンスのSACM手配業務のDTO
 */

@Data
public class SacmArrangementWorkDto {

	/**
	 * 手配業務ID
	 */
	@Schema(description = "手配業務ID", required = false)
	private String arrangementWorkId;

	/**
	 * 文書番号
	 */
	@Schema(description = "文書番号", required = false)
	private String contractNumber;

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", required = false)
	private String rjManageNumber;

	/**
	 * CSV取込日
	 */
	@Schema(description = "CSV取込日", required = false)
	private String csvImportDate;

	/**
	 * コンフィグファイル名
	 */
	@Schema(description = "コンフィグファイル名", required = false)
	private String configFileName;

	/**
	 * LaITコンフィグデータリスト
	 */
	@Schema(description = "LaITコンフィグデータリスト", required = false)
	private List<LaitConfigData> laitConfigDataList;

}