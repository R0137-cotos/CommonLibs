package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ISP手配結果登録 エラー情報リストの検索結果用パラメーター
 */
@Data
public class IspErrorInfoResult {

	/**
	 * エラーID
	 */
	@Schema(description = "エラーID", required = false)
	private String errorId;

	/**
	 * エラーメッセージ
	 */
	@Schema(description = "エラーメッセージ", required = false)
	private String errorMessage;

}
