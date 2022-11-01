package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * eTransporterファイルアップロードAPI用パラメータ
 */
@Data
public class EtransporterFileUploadParameter {

	@ApiModelProperty(value = "ユーザID", required = true, position = 1, allowableValues = "range[0,255]")
	private String userId;

	@ApiModelProperty(value = "送信先メールアドレス", required = true, position = 2, allowableValues = "range[0,255]")
	private String mailTo;

	@ApiModelProperty(value = "メール件名", required = false, position = 3, allowableValues = "range[0,255]")
	private String mailSubjct;

	@ApiModelProperty(value = "メールメッセージ", required = false, position = 4, allowableValues = "range[0,20000]")
	private String mailMessage;

	@ApiModelProperty(value = "アップロードファイル", required = true, position = 5)
	private List<UploadFileParameter> fileList;

	@ApiModelProperty(value = "API呼び出しリトライ有無", required = false, position = 6)
	private boolean retry;

	@ApiModelProperty(value = "メール送信有無", required = false, position = 7)
	private boolean sendmail;

	@ApiModelProperty(value = "送信先メールアドレス(CC)", required = false, position = 8, allowableValues = "range[0,255]")
	private String mailCc;

}
