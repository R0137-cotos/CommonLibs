package jp.co.ricoh.cotos.commonlib.dto.parameter.communication;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.communication.ContactTo.SendType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ContactToDto extends DtoBase {

	/**
	 * 送信タイプ
	 */
	@Schema(description = "送信タイプ", required = false, allowableValues = "TO(\"1\"), CC(\"2\")", example = "1")
	private SendType sendType;

	/**
	 * 宛先MoM社員ID
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "宛先MoM社員ID", required = true, allowableValues = "range[0,255]")
	private String contactToEmpId;

	/**
	 * 宛先メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "宛先メールアドレス ", required = false, allowableValues = "range[0,255]")
	private String contactToEmail;

	/**
	 * 宛先氏名
	 */
	@Size(max = 255)
	@Schema(description = "宛先氏名", required = false, allowableValues = "range[0,255]")
	private String contactToEmpName;

}
