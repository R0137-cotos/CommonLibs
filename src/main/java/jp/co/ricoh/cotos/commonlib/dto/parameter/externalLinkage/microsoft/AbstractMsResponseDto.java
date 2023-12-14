package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class AbstractMsResponseDto {

	/**
	 * HTTPステータス
	 */
	private HttpStatus httpStatus;

	/**
	 * エラーメッセージ
	 */
	private String errorMessage;

}
