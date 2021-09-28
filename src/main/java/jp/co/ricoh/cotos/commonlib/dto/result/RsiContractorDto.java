package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * RSI 契約管理APIのお客様テナント情報一覧取得API レスポンスのお客様テナント契約者情報のDTO
 */

@Data
public class RsiContractorDto {

	/**
	 * メールアドレス
	 */
	@ApiModelProperty(value = "メールアドレス", required = false, position = 1)
	private String mailAddress;

}
