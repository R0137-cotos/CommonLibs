package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 外部取込結果用パラメータ
 */
@Data
public class ExternalImportResult {

	/**
	 * 取込エラー情報リスト
	 */
	@Schema(description = "取込エラー情報リスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ExternalImportErrorResult> externalImportErrorResultList;

	/**
	 * CSV情報
	 */
	@Schema(description = "CSV情報", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private byte[] csvData;
}
