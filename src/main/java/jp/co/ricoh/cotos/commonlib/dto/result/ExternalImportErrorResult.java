package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 外部取込時のエラー内容用パラメータ
 */
@Data
public class ExternalImportErrorResult {

	/**
	 * 行番号
	 */
	@ApiModelProperty(value = "行番号", required = false, position = 1)
	private Long lineNo;

	/**
	 * エラーメッセージ
	 */
	@ApiModelProperty(value = "エラーメッセージ", required = true, position = 2)
	private String errorMessage;
}
