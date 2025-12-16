package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MailInfoDto {

	/**
	 * メールテンプレートマスタID
	 */
	@Min(0)
	@Schema(description = "メールテンプレートマスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long mailTemplateMasterId;

	/**
	 * メールタイプ区分
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "メールタイプ区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String mailTypeDiv;

	/**
	 * TOメールアドレスリスト
	 */
	@Schema(description = "TOメールアドレスリスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<String> toMailAddressList;

	/**
	 * CCメールアドレスリスト
	 */
	@Schema(description = "CCメールアドレスリスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<String> ccMailAddressList;

	/**
	 * BCCメールアドレスリスト
	 */
	@Schema(description = "BCCメールアドレスリスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<String> bccMailAddressList;
}
