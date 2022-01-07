package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class LicenseKeyInfoDto extends DtoBase {

	/**
	 * ライセンスキー情報ID
	 */
	@Min(0)
	@ApiModelProperty(value = "ライセンスキー情報ID", required = false, position = 1, allowableValues = "range[0,9223372036854775807]")
	private Long id;

	/**
	 * インストールURL
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "インストールURL", required = false, position = 2, allowableValues = "range[0,255]")
	private String installUrl;
}
