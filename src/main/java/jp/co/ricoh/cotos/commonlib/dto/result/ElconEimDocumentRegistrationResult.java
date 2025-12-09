package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 電子契約EIM文章登録APIの実施結果用パラメーター
 */
@Data
public class ElconEimDocumentRegistrationResult {

	/**
	 * ドキュメントID
	 */
	@Schema(description = "ドキュメントID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String documentId;

	/**
	 * ドキュメントKey
	 */
	@Schema(description = "ドキュメントKey", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String documentKey;

}

