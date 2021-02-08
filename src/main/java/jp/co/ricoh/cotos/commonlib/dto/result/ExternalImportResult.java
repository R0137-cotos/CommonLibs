package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 外部取込結果用パラメータ
 */
@Data
public class ExternalImportResult {

	/**
	 * 取込エラー情報リスト
	 */
	@ApiModelProperty(value = "取込エラー情報リスト", required = false, position = 1)
	private List<ExternalImportErrorResult> externalImportErrorResultList;

	/**
	 * CSV情報
	 */
	@ApiModelProperty(value = "CSV情報", required = false, position = 2)
	private byte[] csvData;
}
