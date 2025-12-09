package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * O365契約ID・アカウント再発行用パラメータ
 */
@Data
public class O365AccountReissueParameter {

	/**
	 * メールアドレス
	 */
	@Schema(description = "メールアドレス", required = true, allowableValues = "range[0,255]")
	private String mailAddress;
}
