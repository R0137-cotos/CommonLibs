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

	@ApiModelProperty(value = "メール送信有無", required = true, position = 5)
	private boolean sendMail;

	@ApiModelProperty(value = "ダウンロード公開期限", required = true, position = 6, allowableValues = "range[-1,99]")
	private int days;

	@ApiModelProperty(value = "ダウンロード回数制限", required = true, position = 7, allowableValues = "range[-1,99]")
	private int count;

	@ApiModelProperty(value = "ファイルパスワード設定", required = false, position = 8, allowableValues = "true:ファイルをZIP化する,false:ZIP化しない")
	private boolean isEnabledFilePassword;

	@ApiModelProperty(value = "ファイルパスワード自動生成設定", required = false, position = 9)
	private boolean isAutoFilePassword;

	@ApiModelProperty(value = "ファイルパスワード", required = false, position = 10, allowableValues = "range[0,40]")
	private String filePassword;

	@ApiModelProperty(value = "ダウンロードパスワード設定", required = false, position = 11)
	private boolean isEnabledDownloadPassword;

	@ApiModelProperty(value = "ダウンロードパスワード自動生成設定", required = false, position = 12)
	private boolean isAutoDownloadPassword;

	@ApiModelProperty(value = "ダウンロードパスワード", required = false, position = 13, allowableValues = "range[0,40]")
	private String downloadPassword;

	@ApiModelProperty(value = "アップロードファイル", required = true, position = 14)
	private List<UploadFile> fileList;

	@ApiModelProperty(value = "API呼び出しリトライ有無", required = false, position = 15)
	private boolean retry;

	@Data
	public class UploadFile {

		private String path;

		private String fileName;
	}
}
