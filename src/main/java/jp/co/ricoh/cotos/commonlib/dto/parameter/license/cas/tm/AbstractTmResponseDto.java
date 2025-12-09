package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * トレンドマイクロレスポンスDTOインターフェース
 */
@Data
public abstract class AbstractTmResponseDto {

	@JsonProperty("error_subject")
	private String errorSubject;

	@JsonProperty("error_message")
	private String errorMessage;

	private String httpStatus;

}
