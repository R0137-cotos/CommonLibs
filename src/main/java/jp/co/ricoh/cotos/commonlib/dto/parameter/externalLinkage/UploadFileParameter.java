package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * eTransporterアップロードファイルパラメータ
 */
@Data
public class UploadFileParameter {

	@ApiModelProperty(value = "ファイルパス", required = true, position = 1, allowableValues = "range[0,255]")
	private String path;

	@ApiModelProperty(value = "ファイル名", required = false, position = 2, allowableValues = "range[0,255]")
	private String fileName;

}
