package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ArrangementResultInfoDto {

	/**
	 * 手配結果コード
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "手配結果コード", required = true, allowableValues = "range[0,255]")
	private String resultCode;

	/**
	 * エラー情報
	 */
	@Valid
	@Schema(description = "エラー情報", required = false)
	private ErrorInfoDto errorInfo;

}
