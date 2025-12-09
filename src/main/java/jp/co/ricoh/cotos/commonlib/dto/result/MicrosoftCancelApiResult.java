package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "コード", required = false, allowableValues = "range[0,255]")
	private String code;

	/**
	 * メッセージ
	 */
	@Schema(description = "メッセージ", required = false, allowableValues = "range[0,1024]")
	private String apiMessage;
}
