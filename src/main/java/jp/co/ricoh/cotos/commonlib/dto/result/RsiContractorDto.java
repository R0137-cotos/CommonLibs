package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * RSI 契約管理APIのお客様テナント情報一覧取得API レスポンスのお客様テナント契約者情報のDTO
 */

@Data
public class RsiContractorDto {

	/**
	 * 姓
	 */
	@ApiModelProperty(value = "姓", required = false, position = 1)
	private String familyName;

	/**
	 * 名
	 */
	@ApiModelProperty(value = "名", required = false, position = 2)
	private String givenName;

	/**
	 * メールアドレス
	 */
	@ApiModelProperty(value = "メールアドレス", required = false, position = 3)
	private String mailAddress;

}
