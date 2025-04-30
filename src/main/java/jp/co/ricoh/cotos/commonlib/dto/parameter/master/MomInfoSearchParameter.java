package jp.co.ricoh.cotos.commonlib.dto.parameter.master;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MomInfoSearchParameter {

	/**
	 * 郵便番号
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "郵便番号", required = true, position = 1, allowableValues = "range[0,255]")
	private String postNumber;

}
