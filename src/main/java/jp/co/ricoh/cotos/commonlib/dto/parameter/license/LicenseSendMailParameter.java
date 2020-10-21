package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class LicenseSendMailParameter {

	/**
	 * メール区分
	 */
	@ApiParam(value = "メール区分", required = true)
	@ApiModelProperty(value = "メール区分", required = true, position = 1)
	private int mailDiv;

	/**
	 * メール再送信区分
	 */
	@ApiParam(value = "メール再送信区分", required = true)
	@ApiModelProperty(value = "メール再送信区分", required = true, position = 2)
	private int mailResendDiv;
}
