package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * ファイルダウンロードのレスポンスボディ
 */

@Data
public class FileDownloadResponse {

	/**
	 * url
	 */
	private String url;

	/**
	 * type
	 */
	private String type;

	/**
	 * id
	 */
	private String id;
}
