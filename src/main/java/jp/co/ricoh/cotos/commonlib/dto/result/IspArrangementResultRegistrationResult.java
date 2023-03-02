package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ISP手配結果登録APIの検索結果用パラメーター
 */
@Data
public class IspArrangementResultRegistrationResult {

	/**
	 * エラー情報リスト
	 */
	@ApiModelProperty(value = "エラー情報リスト", required = false, position = 1)
	private List<IspErrorInfoResult> errorInfoList;

}
