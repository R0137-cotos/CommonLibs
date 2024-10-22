package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * ファイルアップロード準備のレスポンスボディ
 */

@Data
public class PreparationFileUploadResponse {

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
	private PreparationFileUploadResponseHeader header;

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
