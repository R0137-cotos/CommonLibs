package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ISP手配結果登録APIリクエストパラメータ エラー情報リストDTO
 */

@Data
public class IspErrorInfo {

	/**
	 * エラーコード
	 */
	@Schema(description = "エラーコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String errorCode;

	/**
	 * エラー内容
	 */
	@Schema(description = "エラー内容", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String errorContents;

}