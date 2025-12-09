package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 外部取込時のエラー内容用パラメータ
 */
@Data
public class ExternalImportErrorResult {

	/**
	 * 行番号
	 */
	@Schema(description = "行番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long lineNo;

	/**
	 * エラーメッセージ
	 */
	@Schema(description = "エラーメッセージ", requiredMode = Schema.RequiredMode.REQUIRED)
	private String errorMessage;
}
