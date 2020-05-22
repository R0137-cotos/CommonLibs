package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * ファイルアップロードのレスポンスボディ
 */

@Data
public class FileUploadResponse {

	/**
	 * url
	 */
	private String url;

	/**
	 * id
	 */
	private String id;

	/**
	 * header
	 */
	private FileUploadResponseHeader header;

	/**
	 * type
	 */
	private String type;

	/**
	 * baseUrl
	 */
	private String baseUrl;

	/**
	 * container
	 */
	private String container;

	/**
	 * blob
	 */
	private String blob;

	/**
	 * sas
	 */
	private String sas;
}
