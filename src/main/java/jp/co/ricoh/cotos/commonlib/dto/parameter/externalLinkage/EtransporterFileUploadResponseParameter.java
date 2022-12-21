package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * eTransporterファイルアップロードAPIのレスポンス取得用パラメータ
 */
@Data
public class EtransporterFileUploadResponseParameter {

	@ApiModelProperty(value = "ダウンロードパスワード", required = true, position = 1, allowableValues = "range[0,255]")
	private String downloadPassword;

	@ApiModelProperty(value = "ダウンロードURL", required = true, position = 2, allowableValues = "range[0,255]")
	private String downloadUrl;

}
