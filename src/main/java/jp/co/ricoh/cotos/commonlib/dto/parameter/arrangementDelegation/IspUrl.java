package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation;

import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ISP手配結果登録APIリクエストパラメータ ISPURLリストDTO
 */

@Data
public class IspUrl {

	/**
	 * 作業区分
	 */
	@NotNull
	@Schema(description = "作業区分", requiredMode = Schema.RequiredMode.REQUIRED)
	private String workingDiv;

	/**
	 * ISPURL
	 */
	@NotNull
	@Schema(description = "ISPURL", requiredMode = Schema.RequiredMode.REQUIRED)
	private String ispUrl;

}