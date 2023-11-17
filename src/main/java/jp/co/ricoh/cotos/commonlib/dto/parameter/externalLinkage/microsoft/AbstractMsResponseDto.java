package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import lombok.Data;

@Data
public class AbstractMsResponseDto {

	/**
	 * HTTPステータス
	 */
	private String httpStatus;

	/**
	 * エラーメッセージ
	 */
	private String errorMessage;

}
