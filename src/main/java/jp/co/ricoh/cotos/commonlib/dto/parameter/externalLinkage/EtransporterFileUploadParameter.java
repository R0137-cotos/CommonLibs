package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * eTransporterファイルアップロードAPI用パラメータ
 */
@Data
public class EtransporterFileUploadParameter {

	@Schema(description = "ユーザID", required = true, allowableValues = "range[0,255]")
	private String userId;

	@Schema(description = "送信先メールアドレス", required = true, allowableValues = "range[0,255]")
	private String mailTo;

	@Schema(description = "メール件名", required = false, allowableValues = "range[0,255]")
	private String mailSubjct;

	@Schema(description = "メールメッセージ", required = false, allowableValues = "range[0,20000]")
	private String mailMessage;

	@Schema(description = "アップロードファイル", required = true)
	private List<UploadFileParameter> fileList;

	@Schema(description = "API呼び出しリトライ有無", required = false)
	private boolean retry;

	@Schema(description = "メール送信有無", required = false)
	private boolean sendmail;

}
