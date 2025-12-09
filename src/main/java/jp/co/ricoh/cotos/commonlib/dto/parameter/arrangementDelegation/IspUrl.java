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
	@Schema(description = "作業区分", required = true)
	private String workingDiv;

	/**
	 * ISPURL
	 */
	@NotNull
	@Schema(description = "ISPURL", required = true)
	private String ispUrl;

}