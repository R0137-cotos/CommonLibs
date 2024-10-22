package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 文書取得のpropertiesレスポンス
 */

@Data
public class DocumentGetResponseProperties {

	/**
	 * statusCode
	 */
	@JsonProperty("StatusCode")
	private String statusCode;

	/**
	 * statusName
	 */
	@JsonProperty("StatusName")
	private String statusName;

}
