package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class LicenseAccountDto extends DtoBase {
	@Valid
	/**
	 * ユーザーID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ユーザーID", required = false, position = 1, allowableValues = "range[0,255]")
	private String userId;

	/**
	 * 初期パスワード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "初期パスワード", required = false, position = 2, allowableValues = "range[0,255]")
	private String initialPassword;

	/**
	 * ライセンスキー情報
	 */
	@ApiModelProperty(value = "ライセンスキー情報", required = false, position = 3)
	private List<LicenseKeyInfoDto> licenseKeyInfoList;
}
