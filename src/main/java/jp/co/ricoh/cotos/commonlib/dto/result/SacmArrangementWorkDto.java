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
	@Schema(description = "手配業務ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String arrangementWorkId;

	/**
	 * 文書番号
	 */
	@Schema(description = "文書番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String contractNumber;

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String rjManageNumber;

	/**
	 * CSV取込日
	 */
	@Schema(description = "CSV取込日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String csvImportDate;

	/**
	 * コンフィグファイル名
	 */
	@Schema(description = "コンフィグファイル名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String configFileName;

	/**
	 * LaITコンフィグデータリスト
	 */
	@Schema(description = "LaITコンフィグデータリスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<LaitConfigData> laitConfigDataList;

}