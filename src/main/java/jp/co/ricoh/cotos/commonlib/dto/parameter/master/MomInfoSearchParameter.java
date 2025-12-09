package jp.co.ricoh.cotos.commonlib.dto.parameter.master;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MomInfoSearchParameter {

	/**
	 * 郵便番号
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "郵便番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String postNumber;

}
