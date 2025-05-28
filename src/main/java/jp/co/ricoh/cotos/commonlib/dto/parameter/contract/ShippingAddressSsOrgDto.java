package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ShippingAddressSsOrgDto extends DtoBase {

	/**
	 * MoM組織ID
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "MoM組織ID", required = true, position = 2, allowableValues = "range[0,255]")
	private String momOrgId;

	/**
	 * 課所名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "課所名", required = false, position = 3, allowableValues = "range[0,255]")
	private String serviceOrgName;

}
