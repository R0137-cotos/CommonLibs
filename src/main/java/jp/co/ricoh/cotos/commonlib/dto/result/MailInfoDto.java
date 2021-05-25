package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	 * メールタイプ区分
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "メールタイプ区分", required = true, position = 2, allowableValues = "range[0,255]")
	private String mailTypeDiv;

	/**
	 * TOメールアドレスリスト
	 */
	@ApiModelProperty(value = "TOメールアドレスリスト", required = false, position = 3)
	private List<String> toMailAddressList;

	/**
	 * CCメールアドレスリスト
	 */
	@ApiModelProperty(value = "CCメールアドレスリスト", required = false, position = 4)
	private List<String> ccMailAddressList;

	/**
	 * BCCメールアドレスリスト
	 */
	@ApiModelProperty(value = "BCCメールアドレスリスト", required = false, position = 5)
	private List<String> bccMailAddressList;
}
