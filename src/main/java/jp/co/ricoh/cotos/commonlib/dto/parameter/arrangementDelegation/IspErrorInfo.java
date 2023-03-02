package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation;

import javax.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ISP手配結果登録APIリクエストパラメータ エラー情報リストDTO
 */

@Data
public class IspErrorInfo {

	/**
	 * エラーコード
	 */
	@Valid
	@ApiModelProperty(value = "エラーコード", required = false, position = 1)
	private String errorCode;

	/**
	 * エラー内容
	 */
	@Valid
	@ApiModelProperty(value = "エラー内容", required = false, position = 2)
	private String errorContents;

}