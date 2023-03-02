package jp.co.ricoh.cotos.commonlib.dto.result;

import javax.persistence.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ISP手配結果登録 エラー情報リストの検索結果用パラメーター
 */
@Entity
@Data
public class IspErrorInfoResult {

	/**
	 * エラーID
	 */
	@ApiModelProperty(value = "エラーID", required = false, position = 1)
	private String errorId;

	/**
	 * エラーメッセージ
	 */
	@ApiModelProperty(value = "エラーメッセージ", required = false, position = 2)
	private String errorMessage;

}
