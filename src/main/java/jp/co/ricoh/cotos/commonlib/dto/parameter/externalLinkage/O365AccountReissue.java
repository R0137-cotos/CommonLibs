package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * O365アカウント再発行用パラメータ
 */
@Data
public class O365AccountReissue {

	/**
	 * メールアドレス
	 */
	@ApiModelProperty(value = "メールアドレス", required = true, allowableValues = "range[0,255]", position = 1)
	private String mailAddress;
}
