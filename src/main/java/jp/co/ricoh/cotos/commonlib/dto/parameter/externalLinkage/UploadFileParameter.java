package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * eTransporterアップロードファイルパラメータ
 */
@Data
public class UploadFileParameter {

	@Schema(description = "ファイルパス", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String path;

	@Schema(description = "ファイル名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String fileName;

}
