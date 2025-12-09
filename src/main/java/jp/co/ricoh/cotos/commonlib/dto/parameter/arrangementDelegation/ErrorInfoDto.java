package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation;

import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ErrorInfoDto {

	/**
	 * 対象API(エラー発生個所システム)
	 */
	@Size(max = 255)
	@Schema(description = "対象API(エラー発生個所システム)", required = false, allowableValues = "range[0,255]")
	private String targetApi;

	/**
	 * エラー内容
	 */
	@Size(max = 4000)
	@Schema(description = "エラー内容", required = false, allowableValues = "range[0,4000]")
	private String message;

	/**
	 * APIリクエスト
	 */
	@Size(max = 4000)
	@Schema(description = "APIリクエスト", required = false, allowableValues = "range[0,4000]")
	private String apiRequest;

}
