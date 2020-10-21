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
	@ApiModelProperty(value = "メール区分", required = true, allowableValues = "事前完了メール(\"1\"), Welcameメール(\"2\")", position = 1)
	private int mailDiv;
}
