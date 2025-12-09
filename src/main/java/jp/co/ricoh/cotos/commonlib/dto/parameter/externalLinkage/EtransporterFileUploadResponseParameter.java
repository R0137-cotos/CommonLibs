package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * eTransporterファイルアップロードAPIのレスポンス取得用パラメータ
 */
@Data
public class EtransporterFileUploadResponseParameter {

	@Schema(description = "ダウンロードパスワード", required = true, allowableValues = "range[0,255]")
	private String downloadPassword;

	@Schema(description = "ダウンロードURL", required = true, allowableValues = "range[0,255]")
	private String downloadUrl;

}
