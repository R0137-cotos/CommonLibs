package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MailInfoDto {

	/**
	 * メールテンプレートマスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "メールテンプレートマスタID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]")
	private long mailTemplateMasterId;

	/**
	 * TOメールアドレスリスト
	 */
	@ApiModelProperty(value = "TOメールアドレスリスト", required = false, position = 2)
	private List<String> toMailAddressList;

	/**
	 * CCメールアドレスリスト
	 */
	@ApiModelProperty(value = "CCメールアドレスリスト", required = false, position = 3)
	private List<String> ccMailAddressList;

	/**
	 * bccメールアドレスリスト
	 */
	@ApiModelProperty(value = "BCCメールアドレスリスト", required = false, position = 4)
	private List<String> bccMailAddressList;
}
