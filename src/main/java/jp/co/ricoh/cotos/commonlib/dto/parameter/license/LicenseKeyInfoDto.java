package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class LicenseKeyInfoDto extends DtoBase {
	@Valid
	/**
	 * ライセンスキー情報ID
	 */
	@Id
	@Min(0)
	@ApiModelProperty(value = "ライセンスキー情報ID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * インストールURL
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "インストールURL", required = false, position = 2, allowableValues = "range[0,255]")
	private String installUrl;
}
