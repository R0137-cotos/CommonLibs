package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * MicrosoftキャンセルAPIの戻り値を返却するためのDtoです。<br/>
 * このクラスを使用してDBへの保存を行うことは出来ません。
 */
@Data
public class MicrosoftCancelApiResult {

	/**
	 * コード
	 */
	@ApiModelProperty(value = "コード", required = false, position = 1, allowableValues = "range[0,255]")
	private String code;

	/**
	 * メッセージ
	 */
	@ApiModelProperty(value = "メッセージ", required = false, position = 2, allowableValues = "range[0,1024]")
	private String apiMessage;
}
