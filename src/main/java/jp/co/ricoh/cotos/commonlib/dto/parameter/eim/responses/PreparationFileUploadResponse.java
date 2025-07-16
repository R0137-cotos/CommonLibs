package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * ファイルアップロード準備のレスポンス
 */

@Data
public class PreparationFileUploadResponse {

	/**
	 * type
	 */
	private String type;

	/**
	 * url
	 */
	private String url;

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

	/**
	 * header
	 */
	private PreparationFileUploadResponseHeader header;

	/**
	 * id
	 */
	private String id;

}
