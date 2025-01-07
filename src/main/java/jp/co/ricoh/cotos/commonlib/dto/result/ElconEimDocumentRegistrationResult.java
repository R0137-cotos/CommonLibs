package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 電子契約EIM文章登録APIの実施結果用パラメーター
 */
@Data
public class ElconEimDocumentRegistrationResult {

	/**
	 * ドキュメントID
	 */
	@ApiModelProperty(value = "ドキュメントID", required = false, position = 1, allowableValues = "range[0,255]")
	private String documentId;

	/**
	 * ドキュメントKey
	 */
	@ApiModelProperty(value = "ドキュメントKey", required = false, position = 2, allowableValues = "range[0,255]")
	private String documentKey;

}

