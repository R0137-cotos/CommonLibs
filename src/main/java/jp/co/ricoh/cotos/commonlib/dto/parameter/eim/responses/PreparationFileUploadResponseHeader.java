package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * ファイルアップロード準備のheaderレスポンス
 */
@Data
public class PreparationFileUploadResponseHeader {

	/**
	 * x_ms_blob_content_disposition
	 */
	@JsonProperty("x-ms-blob-content-disposition")
	private String x_ms_blob_content_disposition;

	/**
	 * x_ms_blob_content_type
	 */
	@JsonProperty("x-ms-blob-content-type")
	private String x_ms_blob_content_type;

	/**
	 * x_ms_blob_type
	 */
	@JsonProperty("x-ms-blob-type")
	private String x_ms_blob_type;
}
