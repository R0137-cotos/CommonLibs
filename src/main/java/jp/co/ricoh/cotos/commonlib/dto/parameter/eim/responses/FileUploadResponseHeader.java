package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * ファイルアップロードのレスポンスボディ内のヘッダークラス
 */
@Data
public class FileUploadResponseHeader {

	/**
	 * 
	 */
	private String x_ms_blob_content_disposition;

	/**
	 * 
	 */
	private String x_ms_blob_content_type;

	/**
	 * 
	 */
	private String x_ms_blob_type;
}
