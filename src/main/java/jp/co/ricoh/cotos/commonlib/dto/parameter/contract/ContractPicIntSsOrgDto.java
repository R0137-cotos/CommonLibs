package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ContractPicIntSsOrgDto extends DtoBase {

	/**
	 * MoM組織ID
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "MoM組織ID", required = false, allowableValues = "range[0,255]")
	private String momOrgId;

	/**
	 * 課所名
	 */
	@Size(max = 255)
	@Schema(description = "課所名", required = false, allowableValues = "range[0,255]")
	private String serviceOrgName;
}
