package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "作業区分", required = true, position = 1)
	private String workingDiv;

	/**
	 * ISPURL
	 */
	@NotNull
	@ApiModelProperty(value = "ISPURL", required = true, position = 2)
	private String ispUrl;

}