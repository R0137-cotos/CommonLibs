package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ISP手配結果登録APIの検索結果用パラメーター
 */
@Data
public class IspArrangementResultRegistrationResult {

	/**
	 * エラー情報リスト
	 */
	@Schema(description = "エラー情報リスト", required = false)
	private List<IspErrorInfoResult> errorInfoList;

}
